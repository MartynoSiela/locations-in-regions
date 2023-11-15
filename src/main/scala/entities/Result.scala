package entities

import java.io.{File, FileWriter}
import io.circe.{Error}
import io.circe.syntax.*
import parserJson.Codecs.encoderResult

case class Result(region: String, matched_locations: List[String])

object Result {
  def generateResults(regions: List[Region], locations: List[Location]): List[Result]  = {
    for region <- regions
    yield
      val matchedLocationNames =
        for polygon <- region.polygons
            location <- locations if Polygon.isPointInPolygon(location.point, polygon)
        yield location.name
      Result(region.name, matchedLocationNames)
  }

  def writeToFile(results: List[Result], filePath: String): Unit = {
    val fileWriter = new FileWriter(new File(filePath))
    fileWriter.write(results.asJson.toString())
    fileWriter.close()
  }
}
