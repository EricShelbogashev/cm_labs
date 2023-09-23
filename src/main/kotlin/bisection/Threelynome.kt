package bisection

import bisection.exceptions.NoRootsException
import bisection.math.NegativeInfiniteSegment
import bisection.math.PositiveInfiniteSegment
import bisection.math.Segment
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

        if (derRoots.isEmpty()) {
            val monotonic = findRootsMonotonic(epsilon, time, startTime, step)
            return arrayOf(monotonic)
        } else if (derRoots.size == 1) {
            val monotonic = findRootsMonotonic(epsilon, time, startTime, step)
            return arrayOf(monotonic, monotonic, monotonic)
        }

        val alpha = derRoots[0]
        val beta = derRoots[1]
        return findRootsNonMonotonic(epsilon, time, startTime, step, alpha, beta)
    }

    private fun findRootsMonotonic(epsilon: Double, time: Duration, startTime: Instant, step: Double): Double {
        return if (compute(0.0) < -epsilon) {
            findRoot(epsilon, PositiveInfiniteSegment(-1.0), startTime, time, step)
        } else {
            findRoot(epsilon, NegativeInfiniteSegment(1.0), startTime, time, step)
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
        return if (a1 < -epsilon && b1 < -epsilon) {
            /* Первый экстремум под Ox, второй тоже, пересечение правее второго экстремума, кратность 1. */

            arrayOf(
                findRootUnderOX(epsilon, time, startTime, step, beta)
            )

        } else if (a1 >= -epsilon && a1 <= epsilon && b1 < -epsilon) {
            /* Первый экстремум попал в зону epsilon, кратность 2, второй пересекается правее beta. */

            val secondRoot = findRoot(epsilon, PositiveInfiniteSegment(beta), startTime, time, step)
            arrayOf(
                alpha, alpha, secondRoot
            )

        } else if (a1 > epsilon && b1 < -epsilon) {
            /* Первый экстремум выше Ox, второй - ниже. 3 разных корня. */

            findThreeRootsOnOX(epsilon, time, startTime, step, alpha, beta)

        } else if (a1 < -epsilon && b1 >= -epsilon && b1 <= epsilon) {
            /* Первый экстремум меньше Ox - нет корня, второй в зоне epsilon, имеет удвоенную кратность. */
            arrayOf(
                beta, beta
            )

        } else if (a1 >= -epsilon && a1 <= epsilon && b1 >= -epsilon && b1 <= epsilon) {
            /* Первый экстремум в зоне низкой точности, второй также - один корень кратности 3. */

            val root = (beta + alpha) / 2
            arrayOf(
                root, root, root
            )

        } else if (a1 > epsilon && b1 >= -epsilon && b1 <= epsilon) {
            /* Первый экстремум больше Ox, второй в зоне epsilon - двойная кратность. */

            val firstRoot = findRoot(epsilon, NegativeInfiniteSegment(alpha), startTime, time, step)
            arrayOf(
                firstRoot, beta, beta
            )

        } else if (a1 < -epsilon && b1 > epsilon) {
            /* Первый экстремум ниже Ox, второй выше - невозможно. */

            throw IllegalStateException("первый экстремум ниже Ox, второй выше Ox")

        } else if (a1 >= -epsilon && a1 <= epsilon && b1 > epsilon) {
            /* Первый экстремум в зоне epsilon, второй выше Ox - невозможно. */

            throw IllegalStateException(
                "первый экстремум в зоне epsilon, второй выше Ox : должен быть либо в epsilon, либо ниже"
            )

        } else if (a1 > epsilon && b1 > epsilon) {
            /* Первый экстремум и второй выше Ox - один корень левее alpha. */

            val root = findRoot(epsilon, NegativeInfiniteSegment(alpha), startTime, time, step)
            arrayOf(
                root
            )
        } else {
            throw IllegalStateException("необработанный случай: alpha=$alpha, beta=$beta")
        }
    }

    private fun findRootUnderOX(
        epsilon: Double,
        time: Duration,
        startTime: Instant,
        step: Double,
        beta: Double
    ): Double {
        return findRoot(epsilon, PositiveInfiniteSegment(beta), startTime, time, step)
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

}
