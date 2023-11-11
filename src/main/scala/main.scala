import _root_.parser.{ParserLocations, ParserRegions}

@main
def main(): Unit = {
  val parserLocations = new ParserLocations()
  val locations = parserLocations.parseToType("src/main/scala/input/locations.json")

  val parserRegions = new ParserRegions()
  val regions = parserRegions.parseToType("src/main/scala/input/regions.json")

  println("Finished")
}