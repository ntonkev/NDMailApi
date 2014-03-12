import akka.actor.{ActorSystem, Props}
import akka.io.IO
import api.Api
import core._
import spray.can.Http
import web.Web

//import core.TestActor

/**
 * Created by Nikola on 3/5/14.
 */

/*
object Boot extends App {
  implicit val system = ActorSystem("NDMailApi")

  val service = system.actorOf(Props[TestActor], "NDMailApi")
  val host = system.settings.config.getString("app.interface")
  val port = system.settings.config.getInt("app.port")

  IO(Http) ! Http.Bind(service, host, port)

  println("Hit any key to stop the service.")
  val result = readLine()

  system.shutdown()
}
*/

object Boot extends App with Core with CoreActors with Api with Web
{

  implicit lazy val system = ActorSystem("NDMailApi")

  println("Hit any key to stop the service.")
  val result = readLine()
  system.shutdown()

}