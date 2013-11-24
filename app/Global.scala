import play.api.GlobalSettings

import play.core.StaticApplication

object Global extends GlobalSettings {
  def start() {
    new StaticApplication(new java.io.File("."))
  }
}