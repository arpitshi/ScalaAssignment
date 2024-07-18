package Question.TheifData

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser
import org.apache.tika.sax.BodyContentHandler

import java.io.FileInputStream
object Question2 extends App {

  val parser = new OOXMLParser()
  val handler = new BodyContentHandler(-1)
  val metadata = new Metadata()
  val context = new ParseContext()
  val inputStream = new FileInputStream("src/main/scala/DocFiles/thief_data.docx")

  try {
    parser.parse(inputStream, handler, metadata, context)
    val lines = handler.toString.split("\n")
    lines.foreach { x =>
      val doorConfig = x.trim

      val minFlips = calculateMinFlips(doorConfig)
      println(s"$x min flip $minFlips")

    }
  } catch {
    case e: Exception =>
      println(s"Error reading file: ${e.getMessage}")
  } finally
    inputStream.close()

  def calculateMinFlips(doorConfig: String): Int = {
//    var flipNeed = 0
//    var curr = doorConfig
//
//    for (i <- 0 until curr.length) {
//      if (curr(i) == '0')
//        flipNeed += 1
//        curr = flipSegment(curr, i)
//
//    }
//    flipNeed
val (_, flipsNeeded) = (0 until doorConfig.length).foldLeft((doorConfig, 0)) {
  case ((current, count), i) =>
    if (current(i) == '0') {
      val flipped = flipSegment(current, i)
      (flipped, count + 1)
    } else {
      (current, count)
    }
}

    flipsNeeded
  }

def flipSegment(doorConfig: String, startIndex: Int): String = {
  val flippedSegment:String = doorConfig.substring(0, startIndex) +
    doorConfig.substring(startIndex).map {
      case '0' => '1'
      case '1' => '0'
    }
  flippedSegment
}

}




//  def Solve(str: String, i: Int): Int = {
//    var count = 0;
//      if (i == str.length) 0
//      else if (str.charAt(i) == '0') {
//        val ans = Solve(Replacevalues(str), i + 1) + 1
//        count += ans
//        count
//    }
//    else
//      Solve(str, i + 1)
//  }



//  def Replacevalues(str: String): String = {
//    val st1 = new String(str.replaceAll("0","o"))
//    val st2 = new String(st1.replaceAll("1","0"))
////    val st3 =
//    new String(st2.replaceAll("o","1"))
//
//
//  }