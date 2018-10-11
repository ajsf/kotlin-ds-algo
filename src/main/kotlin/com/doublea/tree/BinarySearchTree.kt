package com.doublea.tree

class BinarySearchTree<T : Comparable<T>> {
    var head: Node<T>? = null

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
}