package models

import scala.slick.driver.PostgresDriver.simple._

case class Group(id: Option[Long], name: String)

class Groups(tag: Tag) extends Table[Group](tag, "groups") {
  def id   = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Group.tupled, Group.unapply)
}

object Groups extends Base {
  val table = TableQuery[Groups]

  def byId(id: Long) = table.filter(_.id === id)

  def byName(name: String) = table.filter(_.name === name)
}
