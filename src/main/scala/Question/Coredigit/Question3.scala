package Question.Coredigit

import scala.annotation.tailrec
import scala.io.Source

object Question3 extends App {
  val input = Source.fromFile("src/main/scala/DocFiles/CoreData.txt").getLines().next()
    println(calculateCoreDigit(input))

  def calculateCoreDigit(input: String): Int = {
    val Array(numStr, repeatStr) = input.split(" ")
    val repeatedSum = sumDigits(numStr) * repeatStr.toInt
    getCoreDigit(repeatedSum)
  }

  def sumDigits(numStr: String): Int = {
    numStr.map(_.asDigit).sum
  }

  @tailrec
  def getCoreDigit(num: Int): Int = {
    if (num < 10) num
    else getCoreDigit(num.toString.map(_.asDigit).sum)
  }
}
