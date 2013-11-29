package controllers

import play.api.mvc._

import models._

import scala.slick.driver.PostgresDriver.simple._

object TasksController extends ApplicationController {

  def list(groupName: String) = Action {
    val (group, tasks) = Groups.database.withTransaction { implicit session =>
      val group = Groups.byName(groupName).first
      val tasks = Tasks.byGroupId(group.id)

      (group, tasks.list)
    }

    Ok( views.html.tasks.list(group, tasks) )
  }

  def show(groupName: String, taskId: Long) = Action {
    val task = Tasks.database.withSession { implicit session =>
      Tasks.byGroupNameAndId(groupName, taskId).first
    }

    Ok( views.html.tasks.show(task) )
  }

  def create(groupName: String) = Action { request =>
    val values = request.body.asFormUrlEncoded
    val name   = values.get("name").head

    val (group, taskId)  = Groups.database.withTransaction { implicit session =>
      val group  = Groups.byName(groupName).first
      val taskId = Tasks.newWithGroupIdAndName += (group.id, Some(name))

      (group, taskId)
    }

    Redirect(routes.TasksController.list(group.name))
  }
}
