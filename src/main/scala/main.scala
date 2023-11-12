import _root_.parser.{ParserLocations, ParserRegions}
import entities.Result
import io.circe.syntax.*

@main
def main(): Unit = {
  val parserLocations = new ParserLocations()
  val locations = parserLocations.parseToType("locations.json")

  val parserRegions = new ParserRegions()
  val regions = parserRegions.parseToType("regions.json")

  val results: Either[Error, List[Result]] = (regions, locations) match {
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

  results.match {
    case Right(results) => println(results.asJson)
  }

  regions.foreach { regionList =>
    regionList.foreach { region =>
      println(region.name)
      println()
      region.polygons.foreach { polygon =>
        locations.foreach { locationList =>
          locationList.foreach { location =>
            println(region.isPointInPolygon(location.point, polygon))
          }
        }
        println()
      }
      println("----")
    }
  }

  println("Finished")
}