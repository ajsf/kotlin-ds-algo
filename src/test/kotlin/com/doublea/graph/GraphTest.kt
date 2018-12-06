package com.doublea.graph

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import java.lang.RuntimeException


internal class AdjacencyMatrixGraphTest : StringSpec({
    "getAdjacentVertices returns a list of the correct vertices for an undirected graph" {
        val graph = AdjacencyMatrixGraph(10, GraphType.UNDIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.getAdjacentVertices(0) shouldBe listOf(1, 2, 3)
        graph.getAdjacentVertices(1) shouldBe listOf(0, 2)
        graph.getAdjacentVertices(2) shouldBe listOf(0, 1)
    }

    "getAdjacentVertices returns a list of the correct vertices for a directed graph" {
        val graph = AdjacencyMatrixGraph(10, GraphType.DIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.getAdjacentVertices(0) shouldBe listOf(1, 2, 3)
        graph.getAdjacentVertices(1) shouldBe listOf(2)
        graph.getAdjacentVertices(2) shouldBe listOf()
    }

    "topological sort returns a list in the correct order" {
        val graph = AdjacencyMatrixGraph(10, GraphType.DIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(4, 1)
        graph.addEdge(4, 3)
        graph.topologicalSort() shouldBe listOf(0, 2, 4, 1, 3)
    }
})

internal class AdjacencySetGraphTest : StringSpec({
    "getAdjacentVertices returns a list of the correct vertices for an undirected graph" {
        val graph = AdjacencySetGraph(10, GraphType.UNDIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.getAdjacentVertices(0) shouldBe listOf(1, 2, 3)
        graph.getAdjacentVertices(1) shouldBe listOf(0, 2)
        graph.getAdjacentVertices(2) shouldBe listOf(0, 1)
    }

    "getAdjacentVertices returns a list of the correct vertices for a directed graph" {
        val graph = AdjacencySetGraph(10, GraphType.DIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.getAdjacentVertices(0) shouldBe listOf(1, 2, 3)
        graph.getAdjacentVertices(1) shouldBe listOf(2)
        graph.getAdjacentVertices(2) shouldBe listOf()
    }

    "traverseAll returns all nodes in an unconnected graph" {
        val graph = AdjacencySetGraph(10, GraphType.DIRECTED)
        graph.addEdge(0, 1)
        graph.traverseAll() shouldBe listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    "topological sort returns a list in the correct order" {
        val graph = AdjacencySetGraph(10, GraphType.DIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(4, 1)
        graph.addEdge(4, 3)
        graph.topologicalSort() shouldBe listOf(0, 2, 4, 1, 3)
    }

    "isConnected returns false for an unconnected graph" {
        val graph = AdjacencySetGraph(5, GraphType.UNDIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(3, 4)
        graph.isConnected() shouldBe false
    }

    "isConnected returns true for a connected graph" {
        val graph = AdjacencySetGraph(5, GraphType.UNDIRECTED)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.isConnected() shouldBe true
    }

    "shortestPath returns list of shortest path from one vertex to another" {
        val graph = AdjacencySetGraph(5, GraphType.DIRECTED)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(4, 1)

        graph.shortestPath(0, 3) shouldBe listOf(0, 2, 4, 1, 3)
        graph.shortestPath(1, 3) shouldBe listOf(1, 3)
        graph.shortestPath(2, 1) shouldBe listOf(2, 4, 1)

        graph.addEdge(0, 1)

        graph.shortestPath(0, 3) shouldBe listOf(0, 1, 3)
        graph.shortestPath(2, 1) shouldBe listOf(2, 4, 1)
    }

    "shortestPath works for a graph with cycles" {
        val graph = AdjacencySetGraph(5, GraphType.UNDIRECTED)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 4)
        graph.addEdge(4, 1)

        graph.shortestPath(0, 3) shouldBe listOf(0, 2, 4, 1, 3)
        graph.shortestPath(1, 3) shouldBe listOf(1, 3)
        graph.shortestPath(2, 1) shouldBe listOf(2, 4, 1)
    }

    "shortestPath throws a RuntimeException when there is no connection from one vertex to another" {
        val graph = AdjacencySetGraph(5, GraphType.DIRECTED)
        graph.addEdge(0, 2)
        graph.addEdge(2, 4)
        graph.addEdge(4, 1)
        shouldThrow<RuntimeException> {
            graph.shortestPath(2, 0)
        }
    }
})

