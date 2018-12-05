package com.doublea.graph

import com.doublea.queue.CircularQueue
import java.lang.RuntimeException

enum class GraphType { DIRECTED, UNDIRECTED }

interface Graph {

    val numVertices: Int
    val graphType: GraphType

    fun addEdge(v1: Int, v2: Int)

    fun getAdjacentVertices(v: Int): List<Int>

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

    fun shortestPath(v1: Int, v2: Int): List<Int> {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")

        val distanceTable = createDistanceTable(v1)

        val result = mutableListOf<Int>()
        var v = v2
        while (v != v1) {
            if (v == -1) throw RuntimeException("There is no connection from vertex $v1 to vertex $v2")
            result.add(v)
            v = distanceTable[v]!!.lastVertex
        }
        result.add(v)
        return result.reversed()
    }

    private fun createDistanceTable(v1: Int): Map<Int, DistanceTableEntry> {
        val distanceTable = (0 until numVertices).map { it to DistanceTableEntry() }.toMap()
        distanceTable[v1]!!.distance = 0
        distanceTable[v1]!!.lastVertex = v1

        val toVisit = mutableListOf(v1)

        while (toVisit.isNotEmpty()) {
            val v = toVisit.removeAt(0)
            val neighbors = getAdjacentVertices(v)
            neighbors.forEach { neighbor ->
                distanceTable[neighbor]?.let { entry ->
                    if (entry.distance == -1) {
                        entry.distance = distanceTable[v]!!.distance + 1
                        entry.lastVertex = v
                        toVisit.add(neighbor)
                    }
                }
            }
        }
        return distanceTable
    }

    private data class DistanceTableEntry(var distance: Int = -1, var lastVertex: Int = -1)
}

class AdjacencyMatrixGraph(override val numVertices: Int, override val graphType: GraphType) : Graph {

    private val adjacencyMatrix = MutableList(numVertices) { MutableList(numVertices) { false } }

    override fun addEdge(v1: Int, v2: Int) {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")
        adjacencyMatrix[v1][v2] = true
        if (graphType == GraphType.UNDIRECTED) adjacencyMatrix[v2][v1] = true
    }

    override fun getAdjacentVertices(v: Int): List<Int> {
        if (v >= numVertices || v < 0) {
            throw IllegalArgumentException("Vertex number $v is invalid")
        }
        return adjacencyMatrix[v]
                .mapIndexed { index, b -> if (b) index else -1 }
                .filter { it > -1 }
    }
}

class AdjacencySetGraph(override val numVertices: Int, override val graphType: GraphType) : Graph {

    private val adjacencySet = MutableList(numVertices) { mutableSetOf<Int>() }

    override fun addEdge(v1: Int, v2: Int) {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")
        adjacencySet[v1].add(v2)
        if (graphType == GraphType.UNDIRECTED) adjacencySet[v2].add(v1)
    }

    override fun getAdjacentVertices(v: Int): List<Int> {
        if (v >= numVertices || v < 0) {
            throw IllegalArgumentException("Vertex number $v is invalid")
        }
        return adjacencySet[v].sorted()
    }
}


