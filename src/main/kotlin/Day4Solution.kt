package org.example

import java.io.File

class Day4Solution {

    companion object {
        const val WORD = "MAS"

        // Define the relative positions for the 'X' shape in four directions
        private val X_SHAPE_FORWARD_DOWN_RIGHT = listOf(
            Pair(-1, -1),  // up-left
            Pair(1, 1)     // down-right
        )
        private val X_SHAPE_BACKWARD_UP_RIGHT = listOf(
            Pair(-1, 1),   // up-right
            Pair(1, -1)    // down-left
        )

        val X_SHAPES = listOf(
            X_SHAPE_FORWARD_DOWN_RIGHT,
            X_SHAPE_BACKWARD_UP_RIGHT
        )
    }


    private fun countMASOccurrences(input: List<String>): Int {
        var count = 0

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (searchFromPosition(input, i, j)) {
                    count++
                }
            }
        }

        return count
    }

    private fun searchFromPosition(input: List<String>, x: Int, y: Int): Boolean {
        // Check the middle character
        if (!isValidPosition(input, x, y) || input[x][y] != WORD[1]) {
            return false
        }

        // Check all four 'X' shapes around the center character
        for (shape in X_SHAPES) {
            var isValid = true

            for ((dx, dy) in shape) {
                val newX = x + dx
                val newY = y + dy

                // Check if we are out of bounds or the characters don't match
                if (!isValidPosition(input, newX, newY)) {
                    isValid = false
                    break
                }

                when (dx to dy) {
                    Pair(-1, -1) -> { // up-left
                        if (input[newX][newY] != WORD[0]) isValid = false
                    }
                    Pair(1, 1) -> { // down-right
                        if (input[newX][newY] != WORD[2]) isValid = false
                    }
                    Pair(-1, 1) -> { // up-right
                        if (input[newX][newY] != WORD[0]) isValid = false
                    }
                    Pair(1, -1) -> { // down-left
                        if (input[newX][newY] != WORD[2]) isValid = false
                    }
                }

                if (!isValid) break
            }

            if (isValid) {
                return true
            }
        }

        return false
    }

    private fun isValidPosition(input: List<String>, x: Int, y: Int): Boolean {
        return x in input.indices && y in input[x].indices
    }

    fun run() {
        val file = File("src/main/resources/input4.txt")
        if (!file.exists()) {
            println("File not found!")
            return
        }

        val inputLines = file.readLines()
        val count = countMASOccurrences(inputLines)
        println("The 'X' shaped pattern for 'MAS' appears $count times in the file.")
    }
}