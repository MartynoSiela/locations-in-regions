import _root_.parser.{ParserLocations, ParserRegions}
import entities.Result
import io.circe.syntax.*
import scala.sys.exit

object Main {
  def main(args: Array[String]): Unit = {
    val usage = """
        Usage: [--regions String] [--locations String] [--output String]
    """

    var regionsFilePath = ""
    var locationsFilePath = ""
    var outputFilePath = ""

    if (args.isEmpty || args.length % 2 != 0){
      println(usage)
      exit(1)
    }

    args.sliding(2, 2).toList.collect {
      case Array("--regions", value: String) => regionsFilePath = value
      case Array("--locations", value: String) => locationsFilePath = value
      case Array("--output", value: String) => outputFilePath = value
      case _ =>
        println(usage)
        exit(1)
    }

    val parserLocations = new ParserLocations()
    val locations = locationsFilePath match {
      case "" =>
        println("Locations file location was not provided")
        exit(1)
      case _ => parserLocations.parseToType(locationsFilePath)
    }

    val parserRegions = new ParserRegions()
    val regions = regionsFilePath match {
      case "" =>
        println("Regions file location was not provided")
        exit(1)
      case _ => parserRegions.parseToType(regionsFilePath)
    }

    val results = Result.generateResults(regions, locations)
    results.match {
      case Right(results) =>
        outputFilePath match {
          case "" =>
            println("Output file location was not provided")
            exit(1)
          case _ => Result.writeToFile(results, outputFilePath)
        }
      case Left(error) => throw error
    }

    println("Finished")
    exit(0)
  }
}