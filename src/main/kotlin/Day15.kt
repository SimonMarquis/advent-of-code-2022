import kotlin.math.absoluteValue

class Day15(input: List<String>) {

    private val regex = """x=(-?\d+), y=(-?\d+).*x=(-?\d+), y=(-?\d+)""".toRegex()

    private val sensorsToBeacons = input.map {
        regex.find(it)!!.destructured.toList().map(String::toInt)
    }.map { (sensorX, sensorY, beaconX, beaconY) ->
        Sensor(sensorX, sensorY) to Beacon(beaconX, beaconY)
    }

    fun part1(y: Int): Int = sensorsToBeacons.mapNotNull { (sensor, beacon) ->
        (sensor to beacon).horizontalSpanAt(y = y)
    }.flatMapTo(mutableSetOf()) {
        it.toList()
    }.apply {
        sensorsToBeacons.forEach { (sensor, beacon) ->
            sensor.takeIf { it.y == y }?.x.let(::remove)
            beacon.takeIf { it.y == y }?.x.let(::remove)
        }
    }.size

    fun part2(): Unit = TODO()

    private open class Point(val x: Int, val y: Int)
    private class Beacon(x: Int, y: Int) : Point(x, y)
    private class Sensor(x: Int, y: Int) : Point(x, y)

    private fun Pair<Sensor, Beacon>.horizontalSpanAt(y: Int = first.y): IntRange? {
        val range = first distanceTo second
        val d = first distanceTo Point(first.x, y)
        return if (d > range) null else (range - d).let { -it..it }.offset(first.x)
    }

    private infix fun Point.distanceTo(other: Point) = ((x - other.x).absoluteValue) + ((y - other.y).absoluteValue)

    private fun IntRange.offset(value: Int) = IntRange(start + value, last + value)

}
