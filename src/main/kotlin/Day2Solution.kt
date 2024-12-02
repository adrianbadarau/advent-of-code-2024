package org.example

import java.io.File

class Day2Solution {
    companion object {
        fun run() {
            part2()
        }

        private fun part1() {
            val file = File("src/main/resources/input2.txt")
            var safeReports = 0

            file.forEachLine { line ->
                val levels = line.trim().split(" ").map { it.toInt() }

                if (levels.size < 2) return@forEachLine // A report with less than two levels can't be considered

                val isIncreasing = levels.zip(levels.drop(1)).all { (a, b) -> a + 1 <= b && b <= a + 3 }
                val isDecreasing = levels.zip(levels.drop(1)).all { (a, b) -> a - 1 >= b && b >= a - 3 }

                if (isIncreasing || isDecreasing) {
                    safeReports++
                }
            }

            println("Number of safe reports: $safeReports")
        }

        private fun part2() {
            val file = File("src/main/resources/input2.txt")
            var safeReports = 0

            file.forEachLine { line ->
                val levels = line.trim().split(" ").map { it.toInt() }

                if (levels.size < 2) return@forEachLine // A report with less than two levels can't be considered

                fun isSafeSequence(seq: List<Int>): Boolean {
                    if (seq.size < 2) return true
                    val isIncreasing = seq.zip(seq.drop(1)).all { (a, b) -> a + 1 <= b && b <= a + 3 }
                    val isDecreasing = seq.zip(seq.drop(1)).all { (a, b) -> a - 1 >= b && b >= a - 3 }
                    return isIncreasing || isDecreasing
                }

                if (isSafeSequence(levels)) {
                    safeReports++
                } else {
                    for (i in levels.indices) {
                        val modifiedLevels = levels.toMutableList()
                        modifiedLevels.removeAt(i)
                        if (isSafeSequence(modifiedLevels)) {
                            safeReports++
                            break
                        }
                    }
                }
            }

            println("Number of safe reports: $safeReports")
        }
    }


}