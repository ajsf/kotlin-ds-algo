package com.doublea.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec


internal class NodeTest : StringSpec({

    "findMaximumDepth returns 0 for a null node" {
        val node = Node(1)
        node.findMaximumDepth() shouldBe 0
    }

    "findMaximumDepth returns 0 for leaf nodes" {
        val node = Node(1)
        node.findMaximumDepth() shouldBe 0
    }

    "findMaximumDepth returns the correct max depth for trees with more than one item" {
        val node = Node(5)
        node.left = Node(1)
        node.findMaximumDepth() shouldBe 1
        node.right = Node(9)
        node.findMaximumDepth() shouldBe 1
        node.left?.left = Node(10)
        node.findMaximumDepth() shouldBe 2
    }

    "mirror should mirror the tree" {
        val node = Node(5)
        node.left = Node(1)
        node.right = Node(9)
        node.breadthFirstTraversal() shouldBe listOf(5, 1, 9)
        node.mirror()
        node.breadthFirstTraversal() shouldBe listOf(5, 9, 1)
        node.mirror()
        node.breadthFirstTraversal() shouldBe listOf(5, 1, 9)
        node.left?.left = Node(2)
        node.right?.right = Node(8)
        node.breadthFirstTraversal() shouldBe listOf(5, 1, 9, 2, 8)
        node.mirror()
        node.breadthFirstTraversal() shouldBe listOf(5, 9, 1, 8, 2)
        node.mirror()
        node.breadthFirstTraversal() shouldBe listOf(5, 1, 9, 2, 8)
    }
})
