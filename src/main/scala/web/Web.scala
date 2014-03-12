package web

import api.Api
import akka.io.IO
import spray.can.Http
import api.Api
import core.Core

/**
 * Created by Nikola on 3/11/14.
 */
trait Web {
  this: Api with Core =>

  val host = system.settings.config.getString("app.interface")
  val port = system.settings.config.getInt("app.port")

  IO(Http) ! Http.Bind(rootService, host, port)
}
