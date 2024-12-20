package org.example

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

fun day1Solution(){
    val filePath = "input.txt"
    val (firstList, secondList) = readDataFromResources(filePath)
    println("First column: $firstList")
    println("Second column: $secondList")

    // Calculate the total distance between the paired numbers
    val totalDistance = calculateTotalDistance(firstList, secondList)
    println("Total Distance: $totalDistance")

    // Calculate the total similarity score
    val totalSimilarityScore = calculateTotalSimilarityScore(firstList, secondList)
    println("Total Similarity Score: $totalSimilarityScore")
}


fun calculateTotalDistance(firstList: List<Int>, secondList: List<Int>): Int {
    // Sort both lists
    val sortedFirstList = firstList.sorted()
    val sortedSecondList = secondList.sorted()

    var totalDistance = 0

    // Calculate the distance between each pair of numbers
    for (i in sortedFirstList.indices) {
        val distance = Math.abs(sortedFirstList[i] - sortedSecondList[i])
        totalDistance += distance
    }

    return totalDistance
}

fun readDataFromResources(fileName: String): Pair<List<Int>, List<Int>> {
    val firstColumn = mutableListOf<Int>()
    val secondColumn = mutableListOf<Int>()

    try {
        // Get the input stream of the file from resources
        val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
            ?: throw FileNotFoundException("File '$fileName' not found in resources.")

        // Use BufferedReader to read the file line by line
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            for (line in reader.lineSequence()) {
                // Split the line by whitespace and convert to integers
                val values = line.split("\\s+".toRegex()).map { it.toInt() }
                if (values.size == 2) {
                    firstColumn.add(values[0])
                    secondColumn.add(values[1])
                }
            }
        }
    } catch (e: FileNotFoundException) {
        println("Error: The file '$fileName' was not found.")
    } catch (e: NumberFormatException) {
        println("Error: There was an issue converting the data to integers.")
    }

    return firstColumn.toList() to secondColumn.toList()
}

fun calculateTotalSimilarityScore(firstList: List<Int>, secondList: List<Int>): Int {
    // Count occurrences of each number in the right list
    val countMap = secondList.groupingBy { it }.eachCount()

    var totalSimilarityScore = 0

    // Calculate the similarity score
    for (number in firstList) {
        val countInRightList = countMap[number] ?: 0
        totalSimilarityScore += number * countInRightList
    }

    return totalSimilarityScore
}