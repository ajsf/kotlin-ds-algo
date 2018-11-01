package com.doublea.heap

class MaxHeap<T : Comparable<T>> : BinaryHeap<T>() {
    override fun compareFunction(first: T, second: T): Boolean {
        return first > second
    }
}