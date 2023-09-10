package bisection

import bisection.exceptions.NoRootsException
import bisection.math.NegativeInfiniteSegment
import bisection.math.PositiveInfiniteSegment
import bisection.math.Segment
import java.time.Duration
import java.time.Instant
import kotlin.math.absoluteValue
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

        val alpha = derRoots[0]
        val beta = derRoots[1]
        return findRootsNonMonotonic(epsilon, time, startTime, step, alpha, beta)
    }

    private fun findRootsMonotonic(epsilon: Double, time: Duration, startTime: Instant, step: Double): Array<Double> {
        return if (compute(0.0) < -epsilon) {
            arrayOf(findRoot(epsilon, PositiveInfiniteSegment(0.0), startTime, time, step))
        } else {
            arrayOf(findRoot(epsilon, NegativeInfiniteSegment(0.0), startTime, time, step))
        }
    }

    private fun findRootsNonMonotonic(
        epsilon: Double,
        time: Duration,
        startTime: Instant,
        step: Double,
        alpha: Double,
        beta: Double
    ): Array<Double> {
        val a1 = compute(alpha)
        val b1 = compute(beta)
        if (a1 < -epsilon && b1 < -epsilon) {
            // 1
            return arrayOf(
                findRootUnderOX(epsilon, time, startTime, step, beta)
            )
        } else if (a1 > epsilon && b1 > epsilon) {
            // 2
            return arrayOf(
                findRootUpOX(epsilon, time, startTime, step, alpha)
            )
        } else if (a1 > epsilon && b1 < - epsilon) {
            // 3
            return findThreeRootsOnOX(epsilon, time, startTime, step, alpha, beta)
        } else if (a1.absoluteValue < epsilon && b1 < -epsilon) {
            // 5
            return findTwoRootsOnUnderOX(epsilon, time, startTime, step, alpha, beta)
        } else if (b1.absoluteValue < epsilon && a1 > epsilon) {
            // 4
            return findTwoRootsOnUpOX(epsilon, time, startTime, step, alpha, beta)
        } else {
            // 6
            return arrayOf((beta + alpha) / 2)
        }
    }

    private fun findRootUnderOX(epsilon: Double, time: Duration, startTime: Instant, step: Double, beta: Double): Double {
        return findRoot(epsilon, PositiveInfiniteSegment(beta), startTime, time, step)
    }
    private fun findRootUpOX(epsilon: Double, time: Duration, startTime: Instant, step: Double, alpha: Double): Double {
        return findRoot(epsilon, NegativeInfiniteSegment(alpha), startTime, time, step)
    }

    private fun findThreeRootsOnOX(
        epsilon: Double,
        time: Duration,
        startTime: Instant,
        step: Double,
        alpha: Double,
        beta: Double
    ): Array<Double> {
        val roots = mutableListOf<Double>()
        roots.add(
            findRoot(epsilon, NegativeInfiniteSegment(alpha), startTime, time, step)
        )
        roots.add(
            findRoot(epsilon, Segment(alpha, beta), startTime, time)
        )
        roots.add(
            findRoot(epsilon, PositiveInfiniteSegment(beta), startTime, time, step)
        )
        return roots.toTypedArray()
    }

    private fun findTwoRootsOnUpOX(
        epsilon: Double,
        time: Duration,
        startTime: Instant,
        step: Double,
        alpha: Double,
        beta: Double
    ): Array<Double> {
        val roots = mutableListOf<Double>()
        roots.add(
            findRoot(epsilon, NegativeInfiniteSegment(alpha), startTime, time, step)
        )
        roots.add(
            beta
        )
        return roots.toTypedArray()
    }

    private fun findTwoRootsOnUnderOX(
        epsilon: Double,
        time: Duration,
        startTime: Instant,
        step: Double,
        alpha: Double,
        beta: Double
    ): Array<Double> {
        val roots = mutableListOf<Double>()
        roots.add(
            alpha
        )
        roots.add(
            findRoot(epsilon, PositiveInfiniteSegment(beta), startTime, time, step)
        )
        return roots.toTypedArray()

    }
}
