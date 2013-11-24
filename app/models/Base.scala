package models

import play.api.Play.current
import play.api.db.{DB => PlayDB}

import scala.slick.driver.PostgresDriver.simple._

class Base {
  def db = PlayDB
  def database = Database.forDataSource(PlayDB.getDataSource())
}

object Base extends Base