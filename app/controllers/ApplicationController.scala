package controllers

import play.api.mvc._

trait ApplicationController extends Controller {
  val greeting = "Hello World"
}

object ApplicationController extends ApplicationController {
  def index = Action {
    Ok(this.greeting)
  }
}