package entities

import io.circe.*

case class Location(name: String, point: Point) 

object Location {
  implicit def locationDecoder: Decoder[Location] = (cursor: HCursor) =>
    for
      name <- cursor.get[String]("name")
      coordinates <- cursor.get[Array[Double]]("coordinates")
      point = Point(coordinates(0), coordinates(1))
    yield
      new Location(name, point)
}