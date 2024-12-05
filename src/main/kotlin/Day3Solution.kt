package org.example

import java.io.File
import java.util.regex.Pattern

fun day3Solution() {
//    val file = File("src/main/resources/input3.txt")
//    var sum = 0
//    file.forEachLine { line ->
//        sum+= updatedSumLine(line.trim())
//    }
    println("The sum of all results from valid multiplication instructions is: ${updatedSumLine()}")

//    println("Result is ${updatedSumLine("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")}")
}


fun sumLine(string: String): Int {
    // Regular expression to match valid mul instructions
    val pattern = Pattern.compile("mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)")

    val matcher = pattern.matcher(string)
    var sum = 0

    while (matcher.find()) {
        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()
        sum += x * y
    }
    return sum

}

fun updatedSumLine(): Int {
    val file = File("src/main/resources/input3.txt")
    var sum = 0
    var enabled = true

    // Regular expressions for do() and don't()
    val doPattern = Pattern.compile("do\\(\\)")
    val dontPattern = Pattern.compile("don't\\(\\)")

    // Combine all patterns to find matches in one pass
    val combinedPattern = Pattern.compile("(?:mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)|do\\(\\)|don't\\(\\))")

    file.forEachLine { line ->
        val matcher = combinedPattern.matcher(line.trim())

        while (matcher.find()) {
            if (doPattern.matcher(matcher.group()).find()) {
                enabled = true
            } else if (dontPattern.matcher(matcher.group()).find()) {
                enabled = false
            }
            if (enabled && matcher.group(1) != null && matcher.group(2) != null) {
                val x = matcher.group(1).toInt()
                val y = matcher.group(2).toInt()
                sum += x * y
            }
        }
    }




    return sum
}