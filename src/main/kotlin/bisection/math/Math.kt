package bisection.math

open class Segment(val a: Double, val b: Double) {
    private val length = (b - a)
    fun leftMiddle(): Segment = Segment(a, a + length / 2.0)
    fun rightMiddle(): Segment = Segment(b - length / 2.0, b)
    fun leftStep(): Segment = Segment(a - length, a)
    fun rightStep(): Segment = Segment(b, b + length)
    fun middle(): Double = (b + a) / 2.0
}

class PositiveInfiniteSegment(private val start: Double) : Segment(start, Double.POSITIVE_INFINITY)

class NegativeInfiniteSegment(private val end: Double) : Segment(Double.NEGATIVE_INFINITY, end)
