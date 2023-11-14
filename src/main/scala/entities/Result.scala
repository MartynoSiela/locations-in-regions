package entities

import java.io.{File, FileWriter}
import io.circe.*
import io.circe.syntax.*

case class Result(region: String, matched_locations: Array[String])

object Result {
  implicit val encoder: Encoder[Result] = new Encoder[Result] {
    final def apply(a: Result): Json = {
      Json.obj(
        ("region", Json.fromString(a.region)),
        ("matched_locations", Json.fromValues(a.matched_locations.map { location =>
          Json.fromString(location)
        }))
      )
    }
  }

  def generateResults(regions: Either[Error, List[Region]],
                               locations: Either[Error, List[Location]]
                              ):Either[Error, List[Result]]  = {
    (regions, locations) match {
      case (Left(error), Left(_)) => throw error
      case (Left(error), Right(_)) => throw error
      case (Right(_), Left(error)) => throw error
      case (Right(regionList), Right(locationList)) =>
        val resultList = regionList.map { region =>
          val matchedLocationNames = region.polygons.flatMap { polygon =>
            locationList.filter(location => region.isPointInPolygon(location.point, polygon))
              .map(_.name)
          }
          Result(region.name, matchedLocationNames)
        }
        Right(resultList)
    }
  }

  def writeToFile(results: List[Result], filePath: String): Unit = {
    val fileWriter = new FileWriter(new File(filePath))
    fileWriter.write(results.asJson.toString())
    fileWriter.close()
  }
}
