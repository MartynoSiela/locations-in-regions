import entities.*
import org.scalatest.funsuite.AnyFunSuite

class PointAgainstEmptyPolygon extends AnyFunSuite {
  val emptyPolygon = Polygon(List.empty[Point])
  val point = Point(1, 1)

  test("any point against an empty polygon is seen as outside") {
    assert(Polygon.isPointInPolygon(point, emptyPolygon) == false)
  }
}
