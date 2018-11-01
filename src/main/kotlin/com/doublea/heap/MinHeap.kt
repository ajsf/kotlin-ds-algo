package com.doublea.heap

class MinHeap<T : Comparable<T>> : BinaryHeap<T>() {
    override fun compareFunction(first: T, second: T): Boolean {
        return first < second
    }
}