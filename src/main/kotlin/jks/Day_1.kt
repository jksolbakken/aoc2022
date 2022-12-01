package jks

import java.lang.RuntimeException
import java.util.SortedMap

fun main() {
    println(elvesSortedByCalories().keys.first())
    println(elvesSortedByCalories().keys.take(3).sum())
}


fun elvesSortedByCalories(): SortedMap<Int, List<List<Int>>> {
    val input = object {}::class.java.getResource("/day1_input")?.readText()
        ?: throw RuntimeException("oh noes!")
    val elves = input.split("\\n\\n".toRegex())
        .map { it.split("\\n".toRegex()) }
        .map { elf -> elf.map { calorieCount -> calorieCount.toInt() } }
    return elves.groupBy { it.sum() }.toSortedMap { x, y -> y - x }
}