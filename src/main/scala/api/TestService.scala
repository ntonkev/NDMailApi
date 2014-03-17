package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef, ActorRefFactory}
import akka.util.Timeout
//import spray.httpx.{Json4sSupport, SprayJsonSupport}
import spray.routing._
import scala.concurrent.ExecutionContext
import api.TestActor.Test


class TestService(testing: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats {

  import akka.pattern.ask
  import scala.concurrent.duration._
  implicit val timeout = Timeout(5.seconds)

  val route =
    path("test4e") {
      get {
        //handleWith { tt: Test => testing ! tt }

          /* Works fine */
          complete {
            "OK"
          }

      }
    }
}
