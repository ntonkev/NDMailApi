import akka.actor.{ActorSystem}
import api.Api
import core.{Core, CoreActors}
import web.Web

/**
 * Created by Nikola on 3/14/14.
 */
object NDMailRestApi extends App with Core with CoreActors with Api with Web {

  implicit lazy val system = ActorSystem("NDMailApi")

  /*******************************************************************/
  /*                                    TEST ONLY
  val service = system.actorOf(Props[TestActor], "NDMailApi")
  val host = system.settings.config.getString("app.interface")
  val port = system.settings.config.getInt("app.port")
  IO(Http) ! Http.Bind(service, host, port)                          */
  /*******************************************************************/


  println("Hit any key to stop the service.")
  val result = readLine()
  system.shutdown()


}
//with Boot //with CoreActors