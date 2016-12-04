package adventcode2016

object Day3 extends App {
	def isValidTriangle(v: Array[Int]): Boolean =
		v(0) + v(1) > v(2) && v(0) + v(2) > v(1) && v(1) + v(2) > v(0)

	val filename = "src/adventcode2016/day3_input.txt"
	val lines = scala.io.Source.fromFile(filename).getLines.toList

	val count1 = lines
		.map(line => line.trim.split("""\s+""").map(_.toInt))
		.filter(isValidTriangle).length

	val count2 = lines
		.map(line => line.trim.split("""\s+""").toList.map(_.toInt))
		.grouped(3).toList
		.map(_.transpose).flatten
		.filter(v => isValidTriangle(v.toArray)).length

	println(count1 + "\n" + count2)
}