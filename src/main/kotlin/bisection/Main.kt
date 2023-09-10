package bisection

import java.time.Duration
import java.util.Scanner

fun `test twolinome 0`() {
    val a = -2.0
    val b = 1.0
    val epsilon = 0.001
    val polynomial = Twolynome(a, b)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

// 0.999755859375
fun `test threelinome 1`() {
    val a = 0.0
    val b = 0.0
    val c = -1.0
    val epsilon = 0.001
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

// -0.999755859375
fun `test threelinome 2`() {
    val a = 0.0
    val b = 0.0
    val c = 1.0
    val epsilon = 0.001
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

fun `test threelinome 3`() {
    val a = 1.5
    val b = 0.0
    val c = 0.0
    val epsilon = 0.001
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

fun `test threelinome 4`() {
    val a = 1.5
    val b = 0.0
    val c = -0.5
    val epsilon = 0.001
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

fun `test threelinome 5`() {
    val a = 3.0
    val b = -1.0
    val c = -2.0
    val epsilon = 0.001
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}

fun main() {
/*
    `test twolinome 0`()
    `test threelinome 1`()
    `test threelinome 2`()
    `test threelinome 3`()
    `test threelinome 4`()
    `test threelinome 5`()
*/
    val scanner = Scanner(System.`in`)
    println("Enter: a b c epsilon")
    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()
    val epsilon = scanner.nextDouble()
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { print(it) }
}