package parser

import java.io.FileNotFoundException
import io.circe.*
import io.circe.parser.*
import scala.io.Source
import scala.sys.exit

trait ParserJson[T: Decoder] {

  def parseToType(filePath: String): Either[Error, List[T]] = {
    var jsonString = ""
    try
      val source = Source.fromFile(filePath)
      jsonString = source.mkString
      source.close()
    catch
      case fileNotFound: FileNotFoundException =>
        println(s"file at '$filePath' was not found")
        exit(1)

    val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

    parseResult match {
      case Left(parsingError) =>
        println("Unable to parse the json string:")
        println(jsonString)
        exit(1)
      case Right(json) =>
        parser.decode[List[T]](jsonString)
    }
  }
}
