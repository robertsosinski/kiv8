package models

import scala.slick.driver.PostgresDriver.simple._

case class Task(id: Long, group_id: Long, name: Option[String], done: Boolean = false)

class Tasks(tag: Tag) extends Table[Task](tag, "tasks") {
  def id       = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def group_id = column[Long]("group_id")
  def name     = column[Option[String]]("name")
  def done     = column[Boolean]("done")

  def group    = foreignKey("group_id", group_id, TableQuery[Groups])(_.id)

  def * = (id, group_id, name, done) <> (Task.tupled, Task.unapply)
}

object Tasks extends Base {
  val table = TableQuery[Tasks]

  def byGroupId(groupId: Long) = for(task <- table if task.group_id === groupId) yield task

  def byGroupIdAndId(groupId: Long, id: Long) = for {
    task <- byGroupId(groupId)
    if task.id === id
  } yield task

  def byGroupName(groupName: String) = {
    for {
      task  <- table
      group <- Groups.table
      if task.group_id === group.id
      if group.name    === groupName
    } yield task
  }

  def byGroupNameAndId(groupName: String, id: Long) = for {
    task <- byGroupName(groupName)
    if task.id === id
  } yield task

  def newWithGroupIdAndName = table.map { tasks =>
    (tasks.group_id, tasks.name)
  } returning table.map(_.id)
}