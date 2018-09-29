package com.doublea.stack

private val matchingParenMap = mapOf(')' to '(', ']' to '[', '}' to '{')

fun hasMatchingParens(input: String): Boolean {
    val stack = Stack<Char>()
    input.forEach { c ->
        when (c) {
            in matchingParenMap.values -> {
                stack.push(c)
            }
            in matchingParenMap.keys -> {
                if (stack.isEmpty()) return false
                val p = stack.pop()
                if (matchingParenMap[c] != p) return false
            }
        }
    }
    return stack.isEmpty()
}