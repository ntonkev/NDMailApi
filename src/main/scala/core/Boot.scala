package core

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import api.TestActor


trait Boot extends Core
{

  implicit lazy val system = ActorSystem("NDMailApi")

  /*******************************************************************/
  //val service = system.actorOf(Props[TestActor], "sbtpro")
  //val host = system.settings.config.getString("NDMailApi.interface")
  //val port = system.settings.config.getInt("NDMailApi.port")
  //Console.println("host -> " + host)
  //Console.println("port -> " + port)
  //IO(Http) ! Http.Bind(service, host, port)
  /*******************************************************************/


  //println("Hit any key to stop the service.")
  //val result = readLine()
  //system.shutdown()

  //sys.addShutdownHook(system.shutdown())

}