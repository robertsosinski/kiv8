package controllers

import play.api.mvc._

import models._

import scala.slick.driver.PostgresDriver.simple._

object TasksController extends Controller {
  def index = Action {
    Ok( views.html.tasks.index(tasks = Tasks.all) )
  }

  def get(id: Long) = Action {
    Ok( views.html.tasks.get(task = Tasks.byId(id)) )
  }
}
