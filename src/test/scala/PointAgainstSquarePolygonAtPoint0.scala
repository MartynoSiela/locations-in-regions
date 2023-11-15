import entities.{Point, Polygon, Region}
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstSquarePolygonAtPoint0 extends AnyFunSuite {
  val squarePolygon = Polygon(List(Point(0, 0), Point(1, 0), Point(1, 1), Point(0, 1)))
  val pointInside = Point(0.5, 0.5)
  val pointOutside = Point(1.5, 0.5)
  val pointOnCorner1 = Point(0, 0)
  val pointOnCorner2 = Point(1, 0)
  val pointOnCorner3 = Point(1, 1)
  val pointOnCorner4 = Point(0, 1)
  val pointOnEdge1 = Point(0.5, 0)
  val pointOnEdge2 = Point(1, 0.5)
  val pointOnEdge3 = Point(0.5, 1)
  val pointOnEdge4 = Point(0, 0.5)


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
