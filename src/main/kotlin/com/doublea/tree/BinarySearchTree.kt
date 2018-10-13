package com.doublea.tree

class BinarySearchTree<T : Comparable<T>> {
    private var head: Node<T>? = null

    fun insert(data: T) {
        val newNode = Node(data)
        head = insert(newNode, head)
    }

    private fun insert(nodeToAppend: Node<T>, currentNode: Node<T>?): Node<T> {
        if (currentNode == null) return nodeToAppend
        if (nodeToAppend.data <= currentNode.data) {
            currentNode.left = insert(nodeToAppend, currentNode.left)
        } else {
            currentNode.right = insert(nodeToAppend, currentNode.right)
        }
        return currentNode
    }

    fun lookup(data: T): T? {
        var current = head
        while (current != null) {
            if (current.data == data) return data
            if (data > current.data) current = current.right
            else if (data < current.data) current = current.left
        }
        return null
    }

    fun findMinimumValue() = findMinimumValue(head)

    private fun findMinimumValue(node: Node<T>?): T? {
        if (node == null) return null
        return if (node.left == null) node.data
        else findMinimumValue(node.left)
    }

    fun breadthFirstTraversal() = head?.breadthFirstTraversal() ?: listOf()

    fun depthFirstPreOrder() = head?.depthFirstPreOrder() ?: listOf()

    fun depthFirstInOrder() = head?.depthFirstInOrder() ?: listOf()

    fun printWithinRange(start: T, end: T, currentNode: Node<T>? = head) {
        currentNode?.let {
            val data = it.data
            if (data in start..end) println(data)
            if (data >= start) printWithinRange(start, end, it.left)
            if (data <= end) printWithinRange(start, end, it.right)
        }
    }

}