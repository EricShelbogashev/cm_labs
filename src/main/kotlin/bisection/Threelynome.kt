package bisection

import bisection.exceptions.NoRootsException
import bisection.math.NegativeInfiniteSegment
import bisection.math.PositiveInfiniteSegment
import java.time.Duration
import java.time.Instant
import kotlin.math.pow

class Threelynome(private val a: Double, private val b: Double, private val c: Double) : Polynome() {
    private val derivative = Twolynome.fromNonCanonical(3.0, 2 * a, b)

    override fun compute(x: Double): Double = x.pow(3) + a * x.pow(2) + b * x + c

    override fun findRoots(epsilon: Double, time: Duration, step: Double): Array<Double> {
        val startTime = Instant.now()
        val derRoots = try {
            derivative.findRoots(epsilon, time, step)
        } catch (e: NoRootsException) {
            arrayOf()
        }

        if (derRoots.size <= 1) {
            return findRootsMonotonic(epsilon, time, startTime, step)
        }

        return findRootsNonMonotonic(epsilon, time, startTime, step)
    }

    private fun findRootsMonotonic(epsilon: Double, time: Duration, startTime: Instant, step: Double): Array<Double> {
        return if (compute(0.0) < -epsilon) {
            arrayOf(findRoot(epsilon, PositiveInfiniteSegment(0.0), startTime, time, step))
        } else {
            arrayOf(findRoot(epsilon, NegativeInfiniteSegment(0.0), startTime, time, step))
        }
    }

    private fun findRootsNonMonotonic(epsilon: Double, time: Duration, startTime: Instant, step: Double): Array<Double> {
        return if (compute(0.0) < -epsilon) {
            arrayOf(findRoot(epsilon, PositiveInfiniteSegment(0.0), startTime, time, step))
        } else {
            arrayOf(findRoot(epsilon, NegativeInfiniteSegment(0.0), startTime, time, step))
        }
    }
}
