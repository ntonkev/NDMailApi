package core

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import api.TestActor


trait Boot extends Core
{

  implicit lazy val system = ActorSystem("NDMailApi")

/*******************************************************************/
/*                                    TEST ONLY                    */
  val service = system.actorOf(Props[TestActor], "sbtpro")
  val host = system.settings.config.getString("app.interface")
  val port = system.settings.config.getInt("app.port")
  IO(Http) ! Http.Bind(service, host, port)
  /*******************************************************************/


  println("Hit any key to stop the service.")
  val result = readLine()
  system.shutdown()

  //sys.addShutdownHook(system.shutdown())

}