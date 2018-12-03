package com.doublea.heap

abstract class BinaryHeap<T : Comparable<T>> {

    protected val list: MutableList<T> = mutableListOf()

    protected fun parentIndex(i: Int) = (i - 1) / 2

    protected abstract fun compareFunction(first: T, second: T): Boolean

    private fun leftChildIndex(i: Int) = 2 * i + 1
    private fun rightChildIndex(i: Int) = 2 * i + 2

    private fun getLeftChild(index: Int): T? = list.getOrNull(leftChildIndex(index))

    private fun getRightChild(index: Int): T? = list.getOrNull(rightChildIndex(index))

    private fun getParent(index: Int): T? = list.getOrNull(parentIndex(index))

    private fun getElementAtIndex(index: Int): T? = list.getOrNull(index)

    private fun swap(index1: Int, index2: Int) {
        val temp = list[index1]
        list[index1] = list[index2]
        list[index2] = temp
    }

    private fun siftDown(index: Int) {
        val left = getLeftChild(index)
        val right = getRightChild(index)
        if (left == null && right == null) return

        val leftIndex = leftChildIndex(index)
        val rightIndex = rightChildIndex(index)

        val priorityIndex = if (left != null && right != null) {
            if (compareFunction(left, right)) leftIndex else rightIndex
        } else if (left != null) {
            leftIndex
        } else {
            rightIndex
        }

        val currentItem = getElementAtIndex(index)!!
        val nextItem = getElementAtIndex(priorityIndex)!!

        if (compareFunction(nextItem, currentItem)) {
            swap(priorityIndex, index)
            siftDown(priorityIndex)
        }
    }

    private fun siftUp(index: Int) {
        if (index == 0) return
        val parentIndex = parentIndex(index)
        val parent = getParent(index)!!
        val current = getElementAtIndex(index)!!
        if (compareFunction(current, parent)) {
            swap(index, parentIndex)
            siftUp(parentIndex)
        }
    }

    fun isEmpty(): Boolean = list.size == 0

    fun insert(data: T) {
        list.add(data)
        siftUp(list.lastIndex)
    }

    fun remove(): T? {
        if (list.size == 0) return null
        val itemToRemove = list.first()
        list[0] = list.last()
        list.removeAt(list.lastIndex)
        siftDown(0)
        return itemToRemove
    }

    fun getHighestPriority() = list.firstOrNull()
}