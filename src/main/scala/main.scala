import _root_.parser.{LocationsParser, RegionsParser}

@main
def main(): Unit = {
  val locationsObject = new LocationsParser()
  val locations = locationsObject.parseToType("src/main/scala/input/locations.json")

  val regionsObject = new RegionsParser()
  val regions = regionsObject.parseToType("src/main/scala/input/regions.json")

  println("Finished")
}