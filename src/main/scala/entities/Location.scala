package entities

import io.circe.*
import io.circe.generic.semiauto.*

case class Location(name: String, coordinates: Array[Double])

object Location {
  implicit def locationDecoder: Decoder[Location] = deriveDecoder[Location]
}