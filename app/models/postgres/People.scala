package models.postgres

import scala.slick.driver.PostgresDriver.simple._

class People(tag: Tag) extends Table[(Long, String)](tag, "people") {
  def id   = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name)
}
