package controllers

import play.api._
import play.api.mvc._
import play.api.http.ContentTypeOf

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def soap = Action(parse.xml) { request =>
	  (request.body \\ "name" headOption).map(_.text).map { name =>
	    Ok(<message status="OK">Hello {name}</message>)	  
	  }.getOrElse {
	    BadRequest(<message status="KO">Missing parameter [name]</message>)
	  }
	}

}