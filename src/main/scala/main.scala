import _root_.parser.{ParserLocations, ParserRegions}

@main
def main(): Unit = {
  val parserLocations = new ParserLocations()
  val locations = parserLocations.parseToType("src/main/scala/input/locations.json")

  val parserRegions = new ParserRegions()
  val regions = parserRegions.parseToType("src/main/scala/input/regions.json")

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