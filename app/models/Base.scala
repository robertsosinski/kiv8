package models

import play.api.Play.current
import play.api.db.{DB => PlayDB}

import scala.slick.driver.PostgresDriver.simple._

trait Base {
  def database = Database.forDataSource(PlayDB.getDataSource())
}
