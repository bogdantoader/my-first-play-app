package model

import play.api.libs.json._
import play.api.libs.functional.syntax._



object ModelImplicits {

	implicit val locationWrites: Writes[Location] = (
	  (JsPath \ "lat").write[Double] and
	  (JsPath \ "long").write[Double]
	)(unlift(Location.unapply))

	implicit val placeWrites: Writes[Place] = (
	  (JsPath \ "name").write[String] and
	  (JsPath \ "location").write[Location]
	)(unlift(Place.unapply))

	implicit val locationReads: Reads[Location] = (
  (JsPath \ "lat").read[Double] and
  (JsPath \ "long").read[Double]
)(Location.apply _)

implicit val placeReads: Reads[Place] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "location").read[Location]
)(Place.apply _)

} 