package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import model._
import model.ModelImplicits._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready woop!."))
  }

  def cool = Action { request => 
  	Ok("Hello cool ssssup [" + request + "]")
  }

  def hello(name: String) = if (name == "Bobby")
  	helloBob
  else Action {
    Ok("Hello " + name)
  }

  // Redirect to /hello/Bob
  def helloBob = Action {
  	Redirect(routes.Application.hello("Bobby boss"))
  }

  def listPlaces = Action {
  	val json = Json.toJson(Place.list)
  	Ok(json)
  }

  def savePlace = Action(BodyParsers.parse.json) { request =>
  	val placeResult = request.body.validate[Place]
  	placeResult.fold(
	    errors => {
	      BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toFlatJson(errors)))
	    },
	    place => { 
	      Place.save(place)
	      Ok(Json.obj("status" ->"OK", "message" -> ("Place '"+place.name+"' saved.") ))  
	    }
  	)
  }

}