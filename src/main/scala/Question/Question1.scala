package Question

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser
import org.apache.tika.sax.BodyContentHandler

import java.io.FileInputStream

object Question1 extends App {
  val parser = new OOXMLParser()
  val handler = new BodyContentHandler()
  val metadata = new Metadata()
  val context = new ParseContext()
  val inputStream = new FileInputStream("src/main/scala/DocFiles/exam_data.docx")

//  try {
//    parser.parse(inputStream, handler, metadata, context)
//    println("Contents of the document:")
//    println(handler.toString)
//    println("Metadata of the document:")
//    metadata.names().foreach(name => println(s"$name: ${metadata.get(name)}"))
//  } finally {
//    inputStream.close()
//  }

  try {
    parser.parse(inputStream, handler, metadata, context)
    val lines = handler.toString.split("\n").toList

    lines.foreach { line =>
      val parts = line.split(",").map(_.trim).map(_.toInt)
      parts match {
        case Array(k, l, m) =>
          val res = if ((k * l) >= m) "yes" else "no"
          println(s"$k, $l, $m -> $res")
        case _ =>
          println(s"Invalid line format: $line")
      }
    }
  } finally
    inputStream.close()
}
