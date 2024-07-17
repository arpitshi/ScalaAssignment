package Question

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser
import org.apache.tika.sax.BodyContentHandler

import java.io.FileInputStream
object Question2 extends App{

  val parser = new OOXMLParser()
  val handler = new BodyContentHandler()
  val metadata = new Metadata()
  val context = new ParseContext()
  val inputStream = new FileInputStream("src/main/scala/DocFiles/thief_data.docx")

  
}
