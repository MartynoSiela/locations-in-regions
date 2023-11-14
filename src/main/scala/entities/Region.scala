package entities

import io.circe._

case class Region(name: String, polygons: Array[Polygon])  {
  def isPointInPolygon(point: Point, polygon: Polygon): Boolean = {
    var countOfCrossesIsOdd: Boolean = false
    var i = 0
    var j = polygon.points.length - 1
    while (i < polygon.points.length) {
      if (((polygon.points(i).longtitude >= point.longtitude) != (polygon.points(j).longtitude >= point.longtitude)) &&
        (point.latitude <=
          (polygon.points(j).latitude - polygon.points(i).latitude) *
            (point.longtitude - polygon.points(i).longtitude) /
            (polygon.points(j).longtitude - polygon.points(i).longtitude) + polygon.points(i).latitude)
      ) countOfCrossesIsOdd = !countOfCrossesIsOdd
      j = i
      i += 1
    }
    countOfCrossesIsOdd
  }
}