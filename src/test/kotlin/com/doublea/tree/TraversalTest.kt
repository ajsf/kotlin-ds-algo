package com.doublea.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class TraversalTest : StringSpec({

    val nodeOne = Node(1)
    val nodeTwo = Node(2)
    val nodeThree = Node(3)
    val nodeFour = Node(4)
    val nodeFive = Node(5)
    val nodeSix = Node(6)
    val nodeSeven = Node(7)
    val nodeEight = Node(8)
    nodeOne.left = nodeTwo
    nodeOne.right = nodeThree
    nodeTwo.left = nodeFour
    nodeTwo.right = nodeFive
    nodeThree.left = nodeSix
    nodeThree.right = nodeSeven
    nodeFour.left = nodeEight
    "Breadth first traversal on the test tree should return one to eight in order" {
        val expectedList = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        nodeOne.breadthFirstTraversal() shouldBe expectedList
    }

    "Depth first pre order traversal on the test tree should return [1,2,4,8,5,3,6,7]" {
        val expectedList = listOf(1, 2, 4, 8, 5, 3, 6, 7)
        nodeOne.depthFirstPreOrder() shouldBe expectedList
        nodeOne.depthFirstPreOrderRecursive() shouldBe expectedList
    }

    "Depth first in order traversal on the test tree should return [8,4,2,5,1,6,3,7]" {
        val expectedList = listOf(8, 4, 2, 5, 1, 6, 3, 7)
        nodeOne.depthFirstInOrder() shouldBe expectedList
    }

    "Depth first post order traversal on the test tree should return [8,4,5,2,6,7,3,1]" {
        val expectedList = listOf(8, 4, 5, 2, 6, 7, 3, 1)
        nodeOne.depthFirstPostOrder() shouldBe expectedList
    }

    "Any of the traversals on nodeEight should return [8]" {
        nodeEight.breadthFirstTraversal() shouldBe listOf(8)
        nodeEight.depthFirstPreOrder() shouldBe listOf(8)
        nodeEight.depthFirstPreOrderRecursive() shouldBe listOf(8)
        nodeEight.depthFirstInOrder() shouldBe listOf(8)
        nodeEight.depthFirstPostOrder() shouldBe listOf(8)
    }
})