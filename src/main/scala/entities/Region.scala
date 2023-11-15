package entities

import io.circe._

case class Region(name: String, polygons: List[Polygon])