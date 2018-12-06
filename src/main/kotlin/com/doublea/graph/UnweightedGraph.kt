package com.doublea.graph

abstract class UnweightedGraph : Graph() {
    abstract fun addEdge(v1: Int, v2: Int)

    override fun shortestPath(v1: Int, v2: Int): List<Int> {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")

        val distanceTable = createDistanceTable(v1)

        return distanceTable.getPath(v1, v2)
    }

    private fun createDistanceTable(v1: Int): Map<Int, Graph.DistanceTableEntry> {
        val distanceTable = (0 until numVertices).map { it to Graph.DistanceTableEntry(-1) }.toMap()
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
}

class AdjacencyMatrixGraph(override val numVertices: Int, override val graphType: GraphType) : UnweightedGraph() {

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

class AdjacencySetGraph(override val numVertices: Int, override val graphType: GraphType) : UnweightedGraph() {

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