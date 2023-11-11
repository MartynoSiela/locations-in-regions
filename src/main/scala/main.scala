import io.circe._, io.circe.parser._, io.circe.generic.semiauto._

@main
def main(): Unit = {

  case class Location(name: String, coordinates: List[Double])

  val source = scala.io.Source.fromFile("src/main/scala/input/locations.json")
  val jsonString = try source.mkString finally source.close()
  val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

  parseResult match {
    case Left(parsingError) =>
      throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
    case Right(json) =>
      implicit val locationDecoder: Decoder[Location] = deriveDecoder[Location]
      val locationDecoded = parser.decode[List[Location]](jsonString)
  }
}