package week3

object TypeClasses extends App {
	import annotation.implicitNotFound
	@implicitNotFound("No member of type class NumberLike in scope for ${T}")
	trait NumberLike[T] {
		def plus(x: T, y: T): T
		def divide(x: T, y: Int): T
		def minus(x: T, y: T): T
	}

	object NumberLike {
		implicit object NumberLikeDouble extends NumberLike[Double] {
			def plus(x: Double, y: Double): Double = x + y
			def divide(x: Double, y: Int): Double = x / y
			def minus(x: Double, y: Double): Double = x - y
		}
		implicit object NumberLikeInt extends NumberLike[Int] {
			def plus(x: Int, y: Int): Int = x + y
			def divide(x: Int, y: Int): Int = x / y
			def minus(x: Int, y: Int): Int = x - y
		}
	}

	val numbers = Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)
	println(Statistics.mean(numbers))
}

object Statistics {
	import TypeClasses.NumberLike
	def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T =
		ev.divide(xs.reduce(ev.plus(_, _)), xs.size)
}