package com.caminha.kotlintravelplanapi

class Solution {
    fun customSortString(order: String, s: String) {
        val orderMapping = order.withIndex().associateBy { it.index }


        fun customSortString(order: String, s: String): String {
            val orderMappingString = order.toList().associateWith { order.indexOf(it) }

            return s.toCharArray().sortedBy {
                if (orderMappingString.containsKey(it)) {
                    orderMappingString[it]
                } else {
                    s.toCharArray().size
                }
            }.joinToString("")
        }


        val myCustomComparator = Comparator<Map<Int, String>> { a, b ->
            when {
                (a == null && b == null) -> 0
                (a == null) -> -1
                else -> 1
            }
        }


    }
}