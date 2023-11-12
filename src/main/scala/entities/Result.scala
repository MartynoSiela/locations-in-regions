package entities

import java.io.{File, FileWriter}
import io.circe.*
import io.circe.syntax.*

case class Result(region: String, matched_locations: Array[String])

object Result {
  implicit val encoder: Encoder[Result] = new Encoder[Result] {
    final def apply(a: Result): Json = {
      Json.obj(
        ("region", Json.fromString(a.region)),
        ("matched_locations", Json.fromValues(a.matched_locations.map { location =>
          Json.fromString(location)
        }))
      )
    }
  }

  implicit def writeToFile(results: List[Result], filePath: String): Unit = {
    val fileWriter = new FileWriter(new File(filePath))
    fileWriter.write(results.asJson.toString())
    fileWriter.close()
  }
}
