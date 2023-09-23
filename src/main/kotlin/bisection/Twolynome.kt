package bisection

import bisection.exceptions.NoRootsException
import bisection.math.NegativeInfiniteSegment
import bisection.math.PositiveInfiniteSegment
import java.time.Duration
import java.time.Instant
import kotlin.math.absoluteValue
import kotlin.math.pow

open class Twolynome(private val a: Double, private val b: Double) : Polynome() {

    companion object {
        fun fromNonCanonical(a: Double, b: Double, c: Double): Twolynome {
            return Twolynome(b / a, c / a)
        }
    }

    private val discriminant: Double = a.pow(2) - 4 * b
    private val vertex: Double = -a / 2

    override fun compute(x: Double): Double = x.pow(2) + a * x + b

    override fun findRoots(epsilon: Double, time: Duration, step: Double): Array<Double> {
        if (discriminant < 0) {
            throw NoRootsException("discriminant is lower than 0")
        }

        val roots: MutableList<Double> = mutableListOf()
        try {
            val root = findRoot(epsilon, NegativeInfiniteSegment(vertex), Instant.now(), time, step)
            roots.add(root)
        } catch (_: NoRootsException) {
        }

        try {
            val root = findRoot(epsilon, PositiveInfiniteSegment(vertex), Instant.now(), time, step)
            roots.add(root)
        } catch (_: NoRootsException) {
        }

        if (roots.size == 2) {
            if (discriminant.absoluteValue < epsilon) {
                return arrayOf((roots[0] + roots[1]) / 2)
            }
        }

        return roots.toTypedArray()
    }
}
