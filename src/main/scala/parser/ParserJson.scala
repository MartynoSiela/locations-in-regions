package parser

import io.circe.*
import io.circe.parser.*

trait ParserJson[T: Decoder] {

  def parseToType(filePath: String): Either[Error, List[T]] = {
    val source = scala.io.Source.fromFile(filePath)
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
