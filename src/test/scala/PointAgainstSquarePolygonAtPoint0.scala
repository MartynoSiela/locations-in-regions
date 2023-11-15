import entities.{Point, Polygon, Region}
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstSquarePolygonAtPoint0 extends AnyFunSuite {
  val squarePolygon = Polygon(List(Point(0, 0), Point(1, 0), Point(1, 1), Point(0, 1)))
  val pointInside1 = Point(0.000001, 0.000001)
  val pointInside2 = Point(0.999999, 0.999999)
  val pointInside3 = Point(0.999999, 0.000001)
  val pointInside4 = Point(0.000001, 0.999999)
  val pointOutside1 = Point(1.000001, 1.000001)
  val pointOutside2 = Point(-0.000001, -0.000001)
  val pointOutside3 = Point(-0.000001, 1.000001)
  val pointOutside4 = Point(1.000001, -0.000001)
  val pointOnCorner1 = Point(0, 0)
  val pointOnCorner2 = Point(1, 0)
  val pointOnCorner3 = Point(1, 1)
  val pointOnCorner4 = Point(0, 1)
  val pointOnEdge1 = Point(0.5, 0)
  val pointOnEdge2 = Point(1, 0.5)
  val pointOnEdge3 = Point(0.5, 1)
  val pointOnEdge4 = Point(0, 0.5)

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
