package com.doublea.heap

import java.lang.StringBuilder
import java.util.stream.Stream

class MinHeap<T : Comparable<T>> : BinaryHeap<T>() {
    override fun compareFunction(first: T, second: T): Boolean {
        return first < second
    }

    fun findMaxElement(): T? {
        if (list.isEmpty()) return null
        if (list.size == 1) return list.first()
        val firstLeaf = parentIndex(list.lastIndex) + 1
        var max = list[firstLeaf]
        for (i in (firstLeaf + 1)..list.lastIndex) {
            if (list[i] > max) max = list[i]
        }
        return max
    }
}

fun <T : Comparable<T>> getKLargestElements(k: Int, stream: Stream<T>): String {
    val heap = MinHeap<T>()
    var count = 0
    for (element in stream) {
        if (count >= k && heap.getHighestPriority()!! < element) {
            heap.remove()
            heap.insert(element)
        } else if (count < k) {
            heap.insert(element)
        }
        count++
    }

    val sb = StringBuilder()
    while (heap.isEmpty().not()) {
        sb.append("${heap.remove()} ")
    }

    if (sb.isNotEmpty()) sb.deleteCharAt(sb.lastIndex)
    return sb.toString()
}