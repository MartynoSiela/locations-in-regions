import entities.{Point, Polygon, Region}
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstSquarePolygon extends AnyFunSuite {
  val squarePolygon = Polygon(List(Point(2, 2), Point(3, 2), Point(3, 3), Point(2, 3)))
  val pointInside = Point(2.5, 2.5)
  val pointOutside = Point(3.5, 2.5)
  val pointOnCorner1 = Point(2, 2)
  val pointOnCorner2 = Point(3, 2)
  val pointOnCorner3 = Point(3, 3)
  val pointOnCorner4 = Point(2, 3)
  val pointOnEdge1 = Point(2.5, 2)
  val pointOnEdge2 = Point(3, 2.5)
  val pointOnEdge3 = Point(2.5, 3)
  val pointOnEdge4 = Point(2, 2.5)


  test("point inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside, squarePolygon) == true)
  }

  test("point outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside, squarePolygon) == false)
  }

  test("point1 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner1, squarePolygon) == false)
  }

  test("point2 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner2, squarePolygon) == false)
  }

  test("point3 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner3, squarePolygon) == false)
  }

  test("point4 on corner of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnCorner4, squarePolygon) == false)
  }

  test("point1 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge1, squarePolygon) == false)
  }

  test("point2 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge2, squarePolygon) == false)
  }

  test("point3 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge3, squarePolygon) == false)
  }

  test("point4 on edge of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOnEdge4, squarePolygon) == false)
  }
}
