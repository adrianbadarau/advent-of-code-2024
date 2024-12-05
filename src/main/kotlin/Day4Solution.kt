package org.example

import java.io.File

fun day4Solution() {
    val file = File("src/main/resources/input4.txt")
    if (!file.exists()) {
        println("File not found!")
        return
    }

    val inputLines = file.readLines()
    val count = countXMASOccurrences(inputLines)
    println("The word 'XMAS' appears $count times in the file.")
}

const val WORD = "XMAS"

// Define all possible directions: horizontal, vertical, diagonal, backwards
val DIRECTIONS = listOf(
    Pair(0, 1),   // right
    Pair(1, 0),   // down
    Pair(1, 1),   // down-right
    Pair(-1, -1), // up-left
    Pair(0, -1),  // left
    Pair(-1, 0),  // up
    Pair(-1, 1),  // up-right
    Pair(1, -1)   // down-left
)

fun countXMASOccurrences(input: List<String>): Int {
    var count = 0

    for (i in input.indices) {
        for (j in input[i].indices) {
            for ((dx, dy) in DIRECTIONS) {
                if (searchFromPosition(input, i, j, dx, dy)) {
                    count++
                }
            }
        }
    }

    return count
}

private fun searchFromPosition(input: List<String>, x: Int, y: Int, dx: Int, dy: Int): Boolean {
    val wordLength = WORD.length
    for (i in 0 until wordLength) {
        val newX = x + i * dx
        val newY = y + i * dy

        // Check if we are out of bounds or the characters don't match
        if (!isValidPosition(input, newX, newY) || input[newX][newY] != WORD[i]) {
            return false
        }
    }

    return true
}

private fun isValidPosition(input: List<String>, x: Int, y: Int): Boolean {
    return x in input.indices && y in input[x].indices
}
