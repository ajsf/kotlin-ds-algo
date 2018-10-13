package com.doublea.tree

class Node<T>(val data: T) {
    var left: Node<T>? = null
    var right: Node<T>? = null

    fun findMaximumDepth() = findMaximumDepth(this)

    private fun findMaximumDepth(node: Node<T>?): Int {
        if (node == null) return 0
        if (node.left == null && node.right == null) return 0
        return 1 + Math.max(findMaximumDepth(node.left), findMaximumDepth(node.right))
    }

    fun mirror() = mirror(this)

    private fun mirror(node: Node<T>?) {
        if (node == null) return
        mirror(node.right)
        mirror(node.left)
        val leftNode = node.left
        node.left = node.right
        node.right = leftNode
    }
}