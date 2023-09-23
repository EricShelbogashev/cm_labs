package bisection

import ColorPrinter
import java.time.Duration
import java.util.Scanner

fun `test 1`() {
    ColorPrinter.printTestLn(1, "0 0 0 1e-8 : ожидается 0.0 кратности 3", ColorPrinter.Color.YELLOW)
    val a = 0.0
    val b = 0.0
    val c = 0.0
    val epsilon = 1e-8
    val polynomial = Threelynome(a, b, c)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }
    println("\n")
}

fun `test 2`() {
    val a = -3.0
    val b = 3.0
    val c = -1.0
    var epsilon = 1e-4
    ColorPrinter.printTestLn(2, "[$a] [$b] [$c] [$epsilon] : ожидается 1.0 кратности 3", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println()
    epsilon = 1e-8
    ColorPrinter.printTestLn(2, "значение эпсилон изменено на $epsilon", ColorPrinter.Color.YELLOW)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }
    println("\n")
}

fun `test 3 a`() {
    val a = 3.27
    val b = 3.5643
    val c = -1.29503
    val epsilon = 0.0001
    ColorPrinter.printTestLn(3, "[$a] [$b] [$c] [$epsilon] : ожидается 0.283314 кратности 3", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 3 b`() {
    val a = -13.2
    val b = 58.04
    val c = -85.008
    val epsilon = 0.01
    ColorPrinter.printTestLn(3, "[$a] [$b] [$c] [$epsilon] : ожидается корень кратности 3", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 3 c`() {
    val a = -13.2
    val b = 58.04
    val c = -85.008
    val epsilon = 1e-12
    ColorPrinter.printTestLn(3, "[$a] [$b] [$c] [$epsilon] : ожидаются  4.2,  4.4,  4.6", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 4`() {
    val a = -3.87
    val b = 4.9923
    val c = -2.14669
    val epsilon = 1e-8
    ColorPrinter.printTestLn(4, "[$a] [$b] [$c] [$epsilon] : ожидается 1.29 кратности 3", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 5`() {
    val a = -3.0
    val b = 5.0
    val c = -15.0
    val epsilon = 1e-5
    ColorPrinter.printTestLn(5, "[$a] [$b] [$c] [$epsilon] : ожидается 3.0 кратности 1", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 6`() {
    val a = 10.0
    val b = 5.0
    val c = 50.0
    val epsilon = 1e-5
    ColorPrinter.printTestLn(6, "[$a] [$b] [$c] [$epsilon] : ожидается -10.0 кратности 1", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 7`() {
    val a = 1.001
    val b = 1.0
    val c = 1.001
    val epsilon = 1e-5
    val polynomial = Threelynome(a, b, c)

    ColorPrinter.printTestLn(7, "[$a] [$b] [$c] [$epsilon] : ожидается -1.001 кратности 1", ColorPrinter.Color.YELLOW)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 8`() {
    val a = -824.0
    val b = 15.0
    val c = -12360.0
    val epsilon = 1e-5
    ColorPrinter.printTestLn(8, "[$a] [$b] [$c] [$epsilon] : ожидается 824 кратности 1", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 9`() {
    val a = -5.0
    val b = 8.0
    val c = -4.0
    var epsilon = 0.1
    ColorPrinter.printTestLn(9, "[$a] [$b] [$c] [$epsilon] : ожидаются 1  2  2", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println()

    epsilon = 1e-4
    ColorPrinter.printTestLn(9, "значение эпсилон изменено на $epsilon", ColorPrinter.Color.YELLOW)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(300),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 10`() {
    val a = -4.0
    val b = -3.0
    val c = 18.0
    val epsilon = 1e-8
    ColorPrinter.printTestLn(10, "[$a] [$b] [$c] [$epsilon] : ожидаются -2  3  3", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 11`() {
    val a = -2.98
    val b = -9.08
    val c = -5.1
    var epsilon = 0.001
    ColorPrinter.printTestLn(11, "[$a] [$b] [$c] [$epsilon] : ожидаются -1.02  -1  5", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println()

    epsilon = 0.0001
    ColorPrinter.printTestLn(11, "значение эпсилон изменено на $epsilon", ColorPrinter.Color.YELLOW)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 12`() {
    val a = 57.027
    val b = 0.51318
    val c = 27455.5
    val epsilon = 1e-8
    ColorPrinter.printTestLn(12, "[$a] [$b] [$c] [$epsilon] : ожидается -63.77 кратности 1", ColorPrinter.Color.YELLOW)
    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 13`() {
    val a = -8.0
    val b = -20.0
    val c = 0.0
    val epsilon = 1e-8
    ColorPrinter.printTestLn(13, "[$a] [$b] [$c] [$epsilon] : ожидаются  -2  0  10", ColorPrinter.Color.YELLOW)

    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(300),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 14`() {
    val a = -2.0
    val b = -13.0
    val c = -10.0
    val epsilon = 1e-8
    ColorPrinter.printTestLn(14, "[$a] [$b] [$c] [$epsilon] : ожидаются  -2  -1  5", ColorPrinter.Color.YELLOW)

    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 15`() {
    val a = 3.0
    val b = -10.0
    val c = -24.0
    val epsilon = 1e-8
    ColorPrinter.printTestLn(15, "[$a] [$b] [$c] [$epsilon] : ожидаются  -4  -2  3", ColorPrinter.Color.YELLOW)

    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun `test 16`() {
    val a = -1.99
    val b = -13.04
    val c = -10.05
    val epsilon = 1e-8
    ColorPrinter.printTestLn(16, "[$a] [$b] [$c] [$epsilon] : ожидаются  -2.01  -1  5", ColorPrinter.Color.YELLOW)

    val polynomial = Threelynome(a, b, c)

    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        0.5
    ).forEach { print("$it ") }

    println("\n")
}

fun tests() {
    `test 1`()
    `test 2`()
    `test 3 a`()
    `test 3 b`()
    `test 3 c`()
    `test 4`()
    `test 5`()
    `test 6`()
    `test 7`()
    `test 8`()
    `test 9`()
    `test 10`()
    `test 11`()
    `test 12`()
    `test 13`()
    `test 14`()
    `test 15`()
    `test 16`()
}

fun main() {
    tests()
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
    ).forEach { println(it) }
}