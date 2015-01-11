package controllers

import play.api._
import play.api.mvc._

object Clients extends Controller {

	def show(id: Long) = Action {
		Ok("The ID is " + id)
	}

}