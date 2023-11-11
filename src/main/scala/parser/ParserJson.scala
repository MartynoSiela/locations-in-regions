package parser

import io.circe.*
import io.circe.parser.*
import scala.io.Source

trait ParserJson[T: Decoder] {

  def parseToType(resourceName: String): Either[Error, List[T]] = {
    val source = Source.fromResource(resourceName)
    val jsonString = try source.mkString finally source.close()
    val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

    parseResult match {
      case Left(parsingError) =>
        throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
      case Right(json) =>
        parser.decode[List[T]](jsonString)
    }
  }
}
