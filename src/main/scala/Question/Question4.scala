package Question

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.io.Source
import scala.util.Try

case class HarvestEntry(date: LocalDate, gatherer: String, fruit: String, amount: Double)
case class PriceEntry(date: LocalDate, fruit: String, price: Double)

object Question4 extends App {
  val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def readCSV(filePath: String): List[Array[String]] = {
    val source = Source.fromFile(filePath)
    val data = source.getLines().drop(1).map(_.split(",")).toList
    source.close()
    data
  }
  val harvestData = readCSV("src/main/scala/DocFiles/harvest/harvest.csv").flatMap { line =>
    Try {
      HarvestEntry(LocalDate.parse(line(1), dateFormat), line(0), line(2), line(3).toDouble)
    }.toOption
  }
  //harvestData.foreach(println)

  val priceData = readCSV("src/main/scala/DocFiles/harvest/prices.csv").flatMap { line =>
    Try {
      PriceEntry(LocalDate.parse(line(1), dateFormat), line(0), line(2).toDouble)
    }.toOption
  }
  //priceData.foreach(println)
  val monthlyHarvestData = harvestData.groupBy(entry => (entry.date.getYear, entry.date.getMonthValue, entry.fruit))
  val monthlyPriceData = priceData.groupBy(entry => (entry.date.getYear, entry.date.getMonthValue, entry.fruit))

  //monthlyHarvestData.foreach(println)
  // Calculate best gatherer by amount
  val bestGathererByMonth = monthlyHarvestData.map {
    case ((year, month, fruit), entries) =>
    val bestGatherer = entries.groupBy(_.gatherer).maxBy(_._2.map(_.amount).sum)._1
    (year, month, fruit) -> bestGatherer
  }
  //bestGathererByMonth.foreach(println)

  // Calculate best and least profitable fruits
  val profitabilityByMonth = monthlyHarvestData.map {
    case ((year, month, fruit), entries) =>
    val totalAmount = entries.map(_.amount).sum
    val price = monthlyPriceData.getOrElse((year, month, fruit), List()).headOption.map(_.price).getOrElse(0.0)
    val totalIncome = totalAmount * price
    (year, month, fruit) -> totalIncome
  }
  //profitabilityByMonth.foreach(println)

  val bestEarningFruitByMonth = if (profitabilityByMonth.nonEmpty) profitabilityByMonth.maxBy(_._2)._1 else "N/A"
 // println(bestEarningFruitByMonth)
  val leastProfitableFruitByMonth = if (profitabilityByMonth.nonEmpty) profitabilityByMonth.minBy(_._2)._1 else "N/A"
  //println(leastProfitableFruitByMonth)
  // Calculate top contributor by income
  val incomeByGatherer = harvestData.groupBy(_.gatherer).map {
    case (gatherer, entries) =>
    val totalIncome = entries.map(entry => {
      val price = priceData.find(p => p.date.getDayOfMonth-1 == entry.date.getDayOfMonth && p.date.getMonth==entry.date.getMonth  && p.fruit == entry.fruit).map(_.price).getOrElse(0.0)
      entry.amount * price
    }).sum
    gatherer -> totalIncome
  }
  //incomeByGatherer.foreach(println)
  val topContributor = if (incomeByGatherer.nonEmpty) incomeByGatherer.maxBy(_._2)._1 else "N/A"
  //println(topContributor)

   //Output results
  println(s"Best gatherer by month: $bestGathererByMonth")
  println(s"Best earning fruit by month: $bestEarningFruitByMonth")
  println(s"Least profitable fruit by month: $leastProfitableFruitByMonth")
  println(s"Top contributor: $topContributor")
}
