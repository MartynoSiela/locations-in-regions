import _root_.parserJson.{ParserLocations, ParserRegions}
import entities.Result
import io.circe.syntax.*

import scala.sys.exit

object Main {
  def main(args: Array[String]): Unit = {

    val usage = """
        Usage: [--regions String] [--locations String] [--output String]
    """

    val argsMap = args
      .sliding(2, 2)
      .collect { case Array(key, value) => (key, value) }
      .toMap

    (argsMap.get("--regions"), argsMap.get("--locations"), argsMap.get("--output")) match
      case (Some(regionsPath), Some(locationsPath), Some(outputPath)) =>
        val locations = ParserLocations.parseToType(locationsPath)
        val regions = ParserRegions.parseToType(regionsPath)
        val results = Result.generateResults(regions, locations)
        results.match {
          case Right(results) => Result.writeToFile(results, outputPath)
          case Left(error) => throw error
        }
      case _ => println(usage)
  }
}