package controllers

import play.api.mvc._

object Tasks extends Controller {
  def index = Action {
    Ok("hello")
  }
}
