package entities

import io.circe.*

case class Result(region: String, matched_locations: Array[String])

object Result {
  implicit val encodeResult: Encoder[Result] = new Encoder[Result] {
    final def apply(a: Result): Json = {
      Json.obj(
        ("region", Json.fromString(a.region)),
        ("matched_locations", Json.fromValues(a.matched_locations.map { location =>
          Json.fromString(location)
        }))
      )
    }
  }
}
