package entities

import io.circe._

case class Region(name: String, polygons: Array[Polygon])

object Region {
  implicit def regionDecoder: Decoder[Region] = (cursor: HCursor) =>
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
}
