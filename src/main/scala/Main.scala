import _root_.parserJson.{ParserLocations, ParserRegions}
import entities.Result
import io.circe.syntax.*

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
        (regions, locations) match
          case (Right(regionsValue), Right(locationsValue)) =>
            val results = Result.generateResults(regionsValue, locationsValue)
            Result.writeToFile(results, outputPath)
          case (Left(_), Right(_)) => println("issue while parsing regions json")
          case (Right(_), Left(_)) => println("issue while parsing locations json")
          case (Left(_), Left(_)) => println("issue while parsing regions and locations json")
      case _ => println(usage)
  }
}