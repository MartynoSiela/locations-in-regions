package entities

import io.circe._

case class Region(name: String, polygons: List[Polygon])  {
  def isPointInPolygon(point: Point, polygon: Polygon): Boolean = {
    if (polygon.points == List.empty[Point])
      return false
    else
      (polygon.points.last :: polygon.points).sliding(2).foldLeft(false) {
        case (countOfCrossesIsOdd, List(edgePoint1, edgePoint2)) =>
          val condition = {
            (
              (edgePoint1.longtitude >= point.longtitude) != (edgePoint2.longtitude >= point.longtitude)
            ) &&
            (
              point.latitude <=
              (edgePoint2.latitude - edgePoint1.latitude) *
              (point.longtitude - edgePoint1.longtitude) /
              (edgePoint2.longtitude - edgePoint1.longtitude) + edgePoint1.latitude
            )
          }
          if (condition) !countOfCrossesIsOdd else countOfCrossesIsOdd
      }
  }
}