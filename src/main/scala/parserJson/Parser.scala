package parserJson

import io.circe.*
import io.circe.parser.*
import scala.io.Source

trait Parser[T: Decoder] {

  def parseToType(filePath: String): Either[Error, List[T]] = {
    val source = Source.fromFile(filePath)
    val jsonString = try source.mkString finally source.close()
    val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

    parseResult match {
      case Left(parsingError) => Left(parsingError)
      case Right(json) => parser.decode[List[T]](jsonString)
    }
  }
}