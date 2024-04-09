package com.caminha.kotlintravelplanapi.application.configuration.api

import com.caminha.kotlintravelplanapi.utils.logInfo
import com.caminha.kotlintravelplanapi.utils.logWarn
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.slf4j.MDCContext
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.*
import org.springframework.web.util.UriBuilderFactory
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.ProxyProvider

object WebClientBuilder {

    private const val maxMemory = 4 * 1024 // 4194304 Buffer max size
    private const val byteSize = 1024

    fun build(
        proxyHost: String,
        proxyPort: String,
        api: String,
        filters: List<ExchangeFilterFunction> = emptyList(),
        compress: Boolean = false,
        customUriEncoding: UriBuilderFactory? = null,
    ): WebClient {
        return WebClient.builder()
            .baseUrl(api)
            .let {
                if(proxyHost.isNotBlank()){
                    val httpClient = HttpClient.create()
                        .proxy { proxy : ProxyProvider.TypeSpec ->
                            proxy.type(
                                ProxyProvider.Proxy.HTTP,
                            ).host(proxyHost)
                                .port(proxyPort.toInt())
                        }
                        .compress(compress)
                    val connector = ReactorClientHttpConnector(httpClient)
                    it.clientConnector(connector)
                } else {
                    it
                }
            }
            .let {
                if(customUriEncoding != null) {
                    it.uriBuilderFactory(customUriEncoding)
                } else {
                    it
                }
            }
            .codecs {
                // by default webclient codecs can buffer only 256KB of data
                //if you receive a response bigger than that you need to call .maxInMemorySize
                // to avoid DataBufferLimitException
                it.defaultCodecs().maxInMemorySize(maxMemory * byteSize)
            }
            .filters {
                it.add(logResponse())
                it.add(setTraceHeaders())
                it.addAll(filters)
            }
            .build()

    }

    //TODO(Implement logRequest to obtain information about webclient requests)
//    private fun logRequest() = WebClientExchangeFilters.filter { clientRequest, clientResponse ->
//        with(clientRequest) {
//
//        }
//    }

    private fun logResponse() = WebClientExchangeFilters.filter { clientRequest, clientResponse ->
        with(clientResponse) {
            val statusCode = statusCode()
            val msg = "Receiving response status: $statusCode url: [${clientRequest.method()}] ${clientRequest.url()}"
            val body = bodyToMono<String>().awaitSingleOrNull()

            if (statusCode.isError) {
                logWarn(msg = "$msg body: $body")
            } else {
              logInfo(msg)
            }

            body?.let { clientResponse.mutate().body(body).build() } ?: clientResponse
        }
    }


    private fun setTraceHeaders(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor {
            mono {
                logInfo(msg = "Setting  trace headers")
                MDCContext().contextMap?.run {
                    ClientRequest.from(it)
                        .header("X-B3-TraceId", get("traceId"))
                        .header("X-B3-SpanId", get("spanId"))
                        .header("X-B3-ParentSpanId", get("spanId"))
                        .build()
                } ?: it
            }
        }
    }



    object WebClientExchangeFilters {
        fun filter(processor: suspend (ClientRequest, ClientResponse) -> ClientResponse) =
            ExchangeFilterFunction { request, next ->
                Mono.deferContextual {
                    next.exchange(request).flatMap {
                        mono { processor(request, it) }
                    }.contextWrite(it)
                }
            }
    }


}