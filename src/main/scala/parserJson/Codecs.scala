package parserJson

import entities.*
import io.circe.*

object Codecs {
  implicit def decoderLocation: Decoder[Location] = (cursor: HCursor) =>
    for name <- cursor.get[String]("name")
        coordinates <- cursor.get[Array[Double]]("coordinates")
        point = (coordinates.lift(0), coordinates.lift(1)) match
          case (Some(lat), Some(long)) => Point(coordinates(0), coordinates(1))
          // case _ =>
          // Not sure how to handle the case when the point does not contains latitude or longtitude or both
          // By not handling the case these locations will be ommited and the program will not crash
          // I my opinion, some sort of error should be shown to the user regarding this location
    yield Location(name, point)

  implicit def decoderRegion: Decoder[Region] = (cursor: HCursor) =>
    for name <- cursor.get[String]("name")
        coordinates <- cursor.get[List[List[List[Double]]]]("coordinates")
        polygons =
          for polygonArray <- coordinates
              points =
                for pointsArray <- polygonArray
                yield (pointsArray.lift(0), pointsArray.lift(1)) match
                  case (Some(lat), Some(long)) => Point(pointsArray(0), pointsArray(1))
                  // case _ =>
                  // Not sure how to handle the case when the point does not contains latitude or longtitude or both
                  // By not handling the case these points will be ommited and the program will not crash
                  // But that does not seem like the right way as this will result with a polygon that is out shape
                  // I my opinion, some sort of error should be shown to the user regarding this point
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
