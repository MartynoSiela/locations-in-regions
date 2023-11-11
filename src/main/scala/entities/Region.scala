package entities

import io.circe.*
import io.circe.generic.semiauto.*

case class Region(name: String, coordinates: Array[Array[Array[Double]]])

object Region {
  implicit def regionDecoder: Decoder[Region] = deriveDecoder[Region]
}