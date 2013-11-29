package models

import scala.slick.driver.PostgresDriver.simple._

case class Task(id: Option[Long], group_id: Long, name: Option[String], done: Boolean)

class Tasks(tag: Tag) extends Table[Task](tag, "tasks") {
  def id       = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def group_id = column[Long]("group_id")
  def name     = column[Option[String]]("name")
  def done     = column[Boolean]("done")

  def group    = foreignKey("group_id", group_id, TableQuery[Groups])(_.id.get)

  def * = (id, group_id, name, done) <> (Task.tupled, Task.unapply)
}

object Tasks extends Base {
  val table = TableQuery[Tasks]

  def byGroupId(groupId: Long) = for(task <- table if task.group_id === groupId) yield task

  def byGroupIdAndId(groupId: Long, taskId: Long) = for {
    task <- byGroupId(groupId)
    if task.id === taskId
  } yield task

  def byGroupName(groupName: String) = {
    for {
      task  <- table
      group <- Groups.table
      if task.group_id === group.id
      if group.name    === groupName
    } yield task
  }

  def byGroupNameAndId(groupName: String, taskId: Long) = for {
    task <- byGroupName(groupName)
    if task.id === taskId
  } yield task


//  def all = database.withSession { implicit session =>
//    table.list
//  }
//
//  def byId(id: Long) = database.withSession { implicit session =>
//    table.filter { task => task.id === id }.first
//  }
//
//  def forGroup(id: Long) = database.withSession { implicit session =>
//    for(task <- table if task.group_id === id) yield task
//  }
//
//  def forGroupById(group_id: Long, id: Long) = database.withSession { implicit session =>
//    forGroup(group_id).filter { task => task.id === id }.first
//  }

}