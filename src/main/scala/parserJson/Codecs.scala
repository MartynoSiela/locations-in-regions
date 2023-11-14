package parserJson

import entities.*
import io.circe.{Decoder, Encoder, HCursor, Json}

object Codecs {
  implicit def decoderLocation: Decoder[Location] = (cursor: HCursor) =>
    for
      name <- cursor.get[String]("name")
      coordinates <- cursor.get[Array[Double]]("coordinates")
      point = Point(coordinates(0), coordinates(1))
    yield
      new Location(name, point)

  implicit def decoderRegion: Decoder[Region] = (cursor: HCursor) =>
    for
      name <- cursor.get[String]("name")
      coordinates <- cursor.get[Array[Array[Array[Double]]]]("coordinates")
      polygons = coordinates.map { polygonArray =>
        val points = polygonArray.map { pointsArray =>
          Point(pointsArray(0), pointsArray(1))
        }
        Polygon(points)
      }
    yield
      new Region(name, polygons)

  implicit def encoderResult: Encoder[Result] = new Encoder[Result] {
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
