import entities.{Point, Polygon, Region}
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstSquarePolygon extends AnyFunSuite {
  val squarePolygon = Polygon(List(Point(2, 2), Point(3, 2), Point(3, 3), Point(2, 3)))
  val pointInside1 = Point(2.000001, 2.000001)
  val pointInside2 = Point(2.999999, 2.999999)
  val pointInside3 = Point(2.000001, 2.999999)
  val pointInside4 = Point(2.999999, 2.000001)
  val pointOutside1 = Point(3.000001, 3.000001)
  val pointOutside2 = Point(1.999999, 1.999999)
  val pointOutside3 = Point(1.999999, 3.000001)
  val pointOutside4 = Point(3.000001, 1.999999)
  val pointOnCorner1 = Point(2, 2)
  val pointOnCorner2 = Point(3, 2)
  val pointOnCorner3 = Point(3, 3)
  val pointOnCorner4 = Point(2, 3)
  val pointOnEdge1 = Point(2.5, 2)
  val pointOnEdge2 = Point(3, 2.5)
  val pointOnEdge3 = Point(2.5, 3)
  val pointOnEdge4 = Point(2, 2.5)

  test("point1 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside1, squarePolygon) == true)
  }

  test("point2 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside2, squarePolygon) == true)
  }

  test("point3 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside3, squarePolygon) == true)
  }

  test("point4 inside of polygon is seen as inside") {
    assert(Polygon.isPointInPolygon(pointInside4, squarePolygon) == true)
  }

  test("point1 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside1, squarePolygon) == false)
  }

  test("point2 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside2, squarePolygon) == false)
  }

  test("point3 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside3, squarePolygon) == false)
  }

  test("point4 outside of polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(pointOutside4, squarePolygon) == false)
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
