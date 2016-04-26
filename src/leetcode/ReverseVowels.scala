package leetcode

import scala.annotation.tailrec
import scala.collection.breakOut

object ReverseVowels extends App {

	val vowels = Set('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

	def reverseVowels(str: String): String = {
		@tailrec
		def partialMap(str: List[Char], map: String, acc: List[Char]): String =
			str match {
				case Nil => acc.reverse mkString
				case x :: xs if (vowels.contains(x)) => partialMap(xs, map.tail, map.head :: acc)
				case x :: xs => partialMap(xs, map, x :: acc)
			}
		partialMap(str toList, str.filter(vowels.contains(_)).reverse, Nil)
	}

	def reverseVowels2(str: String): String = {
		val (v, i) = str.zipWithIndex.filter(vowels contains _._1) unzip
		val replacements: Map[Int, Char] = (i zip v.reverse)(breakOut)
		str.zipWithIndex map {
			case (v, i) => replacements getOrElse (i, v)
		} mkString
	}

	assert(reverseVowels2("hEllo") == "hollE")
	assert(reverseVowels2("aeiou") == "uoiea")
	assert(reverseVowels2("") == "")
	assert(reverseVowels2("bahul") == "buhal")
	assert(reverseVowels2("game changer") == "gema chengar")
	assert(reverseVowels2("able") == "ebla")
	assert(reverseVowels("hEllo") == "hollE")
	assert(reverseVowels("aeiou") == "uoiea")
	assert(reverseVowels("") == "")
	assert(reverseVowels("bahul") == "buhal")
	assert(reverseVowels("game changer") == "gema chengar")
	assert(reverseVowels("able") == "ebla")
}
/*
 * 1,a
 * 3,e
 * 7,a
 * 10,e
 */ 