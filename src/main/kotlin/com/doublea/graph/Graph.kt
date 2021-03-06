package com.doublea.graph

import com.doublea.queue.CircularQueue
import java.lang.RuntimeException

enum class GraphType { DIRECTED, UNDIRECTED }

interface GraphInterface {

    val numVertices: Int
    val graphType: GraphType

    fun getAdjacentVertices(v: Int): List<Int>

    fun shortestPath(v1: Int, v2: Int): List<Int>
}

abstract class Graph : GraphInterface {

    fun depthFirstTraversal(vertex: Int): Set<Int> {
        fun dft(visited: MutableSet<Int>, currentVertex: Int) {
            if (visited.contains(currentVertex)) return
            visited.add(currentVertex)
            for (v in getAdjacentVertices(currentVertex)) {
                dft(visited, v)
            }
        }

        val visited = mutableSetOf<Int>()
        dft(visited, vertex)
        return visited
    }

    fun traverseAll(): List<Int> = (0 until numVertices)
            .flatMap {
                depthFirstTraversal(it)
            }.distinct()

    fun breadthFirstTraversal(vertex: Int): Set<Int> {
        val visited = mutableSetOf<Int>()
        val queue = CircularQueue<Int>()
        queue.enqueue(vertex)
        while (queue.isEmpty().not()) {
            val v = queue.dequeue()
            if (visited.contains(v).not()) {
                visited.add(v)
                getAdjacentVertices(v).forEach { queue.enqueue(it) }
            }
        }
        return visited
    }

    fun topologicalSort(): List<Int> {
        if (graphType == GraphType.UNDIRECTED) throw RuntimeException("TOPOLOGICAL SORT CANNOT BE PERFORMED ON UNDIRECTED GRAPH")
        val adjacentVertices = (0 until numVertices).map { getAdjacentVertices(it) }
        val connections = adjacentVertices.flatten().groupingBy { it }.eachCount()
        val indegreesForVertices = adjacentVertices.indices
                .map { it to connections.getOrDefault(it, 0) }
                .filterIndexed { i, _ -> adjacentVertices[i].isNotEmpty() || connections.contains(i) }
                .toMap().toMutableMap()

        val result = mutableListOf<Int>()
        while (indegreesForVertices.isNotEmpty()) {
            if (indegreesForVertices.values.contains(0).not()) throw RuntimeException("TOPOLOGICAL SORT CANNOT BE PERFORMED ON A GRAPH WITH A CYCLE")
            val next = indegreesForVertices.filter { it.value == 0 }
            next.forEach {
                indegreesForVertices.remove(it.key)
                result.add(it.key)
                adjacentVertices[it.key].forEach { v -> indegreesForVertices[v] = indegreesForVertices[v]!! - 1 }
            }
        }
        return result
    }

    fun isConnected(): Boolean {
        for (i in 0 until numVertices) {
            if (breadthFirstTraversal(i).size != numVertices) return false
        }
        return true
    }

    protected data class DistanceTableEntry(var distance: Int, var lastVertex: Int = -1)

    protected fun createDistanceTable(size: Int, defaultDistance: Int, source: Int): Map<Int, DistanceTableEntry> {
        val table = (0 until size)
                .map { it to Graph.DistanceTableEntry(defaultDistance) }
                .toMap()
        table[source]!!.distance = 0
        table[source]!!.lastVertex = source
        return table
    }

    protected fun Map<Int, Graph.DistanceTableEntry>.getPath(v1: Int, v2: Int): List<Int> {
        val result = mutableListOf<Int>()
        var v = v2
        while (v != v1) {
            if (v == -1) throw RuntimeException("There is no connection from vertex $v1 to vertex $v2")
            result.add(v)
            v = get(v)!!.lastVertex
        }
        result.add(v)
        return result.reversed()
    }
}

