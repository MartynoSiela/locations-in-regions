package parserJson

import entities.*
import io.circe.*

object Codecs {
  implicit def decoderLocation: Decoder[Location] = (cursor: HCursor) =>
    for name <- cursor.get[String]("name")
        coordinates <- cursor.get[Array[Double]]("coordinates")
        point = Point(coordinates(0), coordinates(1))
    yield Location(name, point)

  implicit def decoderRegion: Decoder[Region] = (cursor: HCursor) =>
    for name <- cursor.get[String]("name")
        coordinates <- cursor.get[Array[Array[Array[Double]]]]("coordinates")
        polygons =
          for polygonArray <- coordinates
              points =
                for pointsArray <- polygonArray
                yield Point(pointsArray(0), pointsArray(1))
          yield Polygon(points)
    yield Region(name, polygons)

  implicit def encoderResult: Encoder[Result] = new Encoder[Result] {
    final def apply(a: Result): Json = {
      Json.obj(
        ("region", Json.fromString(a.region)),
        ("matched_locations", Json.fromValues(
          for location <- a.matched_locations
          yield Json.fromString(location)))
      )
    }
  }
}
