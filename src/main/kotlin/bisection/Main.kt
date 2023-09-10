package bisection

import java.time.Duration

fun main() {
    val a = 0.0
    val b = -4.0
    val epsilon = 0.001
    val polynomial = Twolynome(a, b)
    polynomial.findRoots(
        epsilon,
        Duration.ofSeconds(3),
        1.0
    ).forEach { println(it) }
}