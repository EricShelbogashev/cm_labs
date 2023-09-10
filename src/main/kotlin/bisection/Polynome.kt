package bisection

import bisection.exceptions.NoRootsException
import bisection.math.NegativeInfiniteSegment
import bisection.math.PositiveInfiniteSegment
import bisection.math.Segment
import java.time.Duration
import java.time.Instant
import java.util.concurrent.TimeoutException
import kotlin.math.absoluteValue
import kotlin.math.sign

abstract class Polynome {
    abstract fun compute(x: Double): Double
    abstract fun findRoots(epsilon: Double, time: Duration, step: Double): Array<Double>


    private fun findRoot(epsilon: Double, segment: Segment, startTime: Instant, time: Duration): Double {
        assert(epsilon > 0)

        if (compute(segment.a).sign == compute(segment.b).sign) {
            throw NoRootsException("the function has the same sign at the edges of the segment")
        }

        return findRootImpl(epsilon, segment, startTime, time)
    }

    private fun findRootImpl(epsilon: Double, seg: Segment, startTime: Instant, time: Duration): Double {
        var segment = seg

        while (startTime.plusMillis(time.toMillis()).isAfter(Instant.now())) {
            val middle = segment.middle()
            val computed = compute(middle)

            if (computed.absoluteValue < epsilon) {
                return middle
            }

            if (compute(segment.a).sign != computed.sign) {
                segment = Segment(segment.a, middle)
            } else if (computed.sign != compute(segment.b).sign) {
                segment = Segment(middle, segment.b)
            }
        }
        throw TimeoutException("time is over")
    }

    protected fun findRoot(
        epsilon: Double, seg: PositiveInfiniteSegment, startTime: Instant, time: Duration, step: Double
    ): Double {
        val computed = compute(seg.a)
        if (computed.sign > 0) {
            throw NoRootsException("right vertex of parabola, start point already is greater than 0, no roots")
        }

        var segment = Segment(seg.a, seg.a + step)
        while (startTime.plusMillis(time.toMillis()).isAfter(Instant.now())) {
            try {
                return findRoot(epsilon, segment, startTime, time)
            } catch (e: NoRootsException) {
                segment = segment.rightStep()
            }
        }
        throw TimeoutException("time is over")
    }

    protected fun findRoot(
        epsilon: Double, seg: NegativeInfiniteSegment, startTime: Instant, time: Duration, step: Double
    ): Double {
        val computed = compute(seg.b)
        if (computed.sign > 0) {
            throw NoRootsException("left vertex of parabola, end point is not lower than 0 yet, no roots")
        }

        var segment = Segment(seg.b - step, seg.b)
        while (startTime.plusMillis(time.toMillis()).isAfter(Instant.now())) {
            try {
                return findRoot(epsilon, segment, startTime, time)
            } catch (e: NoRootsException) {
                segment = segment.leftStep()
            }
        }
        throw TimeoutException("time is over")
    }
}
