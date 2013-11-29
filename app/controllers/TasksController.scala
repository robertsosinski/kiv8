package controllers

import play.api.mvc._

import models._

import scala.slick.driver.PostgresDriver.simple._

object TasksController extends ApplicationController {

  def list(groupName: String) = Action {
    val (group, tasks) = Groups.database.withTransaction { implicit session =>
      val group = Groups.byName(groupName).first
      val tasks = Tasks.byGroupId(group.id.get)

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

}
