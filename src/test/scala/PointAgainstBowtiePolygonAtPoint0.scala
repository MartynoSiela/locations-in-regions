import entities.{Point, Polygon, Region}
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstBowtiePolygonAtPoint0 extends AnyFunSuite {
  val bowtiePolygon = Polygon(List(Point(-6, 6), Point(6, -6), Point(6, 6), Point(-6, -6)))
  val pointInside1 = Point(-4, -2)
  val pointInside2 = Point(-2, 0)
  val pointOutside1 = Point(0, -2)
  val pointOutside2 = Point(-8, -2)
  val pointOutside3 = Point(-8, 0)
  val pointOnCorner1 = Point(-6, 6)
  val pointOnCorner2 = Point(6, 6)
  val pointOnCorner3 = Point(-6, -6)
  val pointOnCorner4 = Point(6, -6)
  val pointOnEdge1 = Point(2, 2)
  val pointOnEdge2 = Point(-2, 2)
  val pointOnEdge3 = Point(-2, -2)
  val pointOnEdge4 = Point(2, -2)
  val pointOnEdgeIntersection = Point(0, 0)

  test("point1 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside1, bowtiePolygon) == true)
  }

  test("point2 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside2, bowtiePolygon) == true)
  }

  test("point1 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside1, bowtiePolygon) == false)
  }

  test("point2 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside2, bowtiePolygon) == false)
  }

  test("point3 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside3, bowtiePolygon) == false)
  }

  test("point1 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner1, bowtiePolygon) == false)
  }

  test("point2 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner2, bowtiePolygon) == false)
  }

  test("point3 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner3, bowtiePolygon) == false)
  }

  test("point4 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner4, bowtiePolygon) == false)
  }

  test("point1 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge1, bowtiePolygon) == false)
  }

  test("point2 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge2, bowtiePolygon) == false)
  }

  test("point3 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge3, bowtiePolygon) == false)
  }

  test("point4 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge4, bowtiePolygon) == false)
  }

  test("point on edge intersection of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdgeIntersection, bowtiePolygon) == false)
  }
}
