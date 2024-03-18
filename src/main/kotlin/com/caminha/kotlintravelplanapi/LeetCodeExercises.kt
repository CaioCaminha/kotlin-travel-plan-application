package com.caminha.kotlintravelplanapi

class Solution {
    fun customSortString1(order: String, s: String) {
        val orderMapping = order.withIndex().associateBy { it.index }

    }

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

    fun customSortStringLeetCodeSuggestion(order: String, s: String): String {
        val result = StringBuilder()
        val mp = HashMap<Char, Int>()
        for (char in s) {
            mp[char] = mp.getOrDefault(char, 0) + 1
        }
        for (char in order) {
            if (mp.containsKey(char)) {
                result.append(char.toString().repeat(mp[char] ?: 0))
                mp.remove(char)
            }
        }
        for ((char, count) in mp) {
            result.append(char.toString().repeat(count))
        }
        return result.toString()
    }

}