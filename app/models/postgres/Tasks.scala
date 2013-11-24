package models.postgres

import scala.slick.driver.PostgresDriver.simple._

case class Task(id: Long, name: Option[String], person_id: Long) {
  def greet = "hello there"
}

class Tasks(tag: Tag) extends Table[Task](tag, "tasks") {
  def id        = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name      = column[Option[String]]("name")
  def person_id = column[Long]("person_id")

  def person    = foreignKey("person_id", person_id, TableQuery[People])(_.id)

  def * = (id, name, person_id) <> (Task.tupled, Task.unapply)
}

object Tasks extends Base {
  val self = TableQuery[Tasks]

  def all = database.withSession { implicit session =>
    for(task <- self) yield task
  }

  def byId(id: Long) = database.withSession { implicit session =>
    all.filter { task => task.id === id }.first
  }

  def forPerson(id: Long) = database.withSession { implicit session =>
    for(task <- all if task.person_id === id) yield task
  }

  def forPersonById(person_id: Long, id: Long) = database.withSession { implicit session =>
    forPerson(person_id).filter { task => task.id === id }.first
  }
}