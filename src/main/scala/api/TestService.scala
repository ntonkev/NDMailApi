package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef}
import akka.util.Timeout
import spray.httpx.{Json4sSupport, SprayJsonSupport}
import spray.routing._
import akka.pattern.ask
import scala.concurrent.duration._

/**
 * Created by Nikola on 3/4/14.
 */
trait TestService extends HttpService  {

  //import Json4sProtocol._
  import TestWorkerActor._

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout(5 seconds)

  val worker = actorRefFactory.actorOf(Props[TestWorkerActor], "worker")

  val testRoute = {
    path("test4e") {
      get {
        complete((worker ? Test("xaxaxa"))
          .mapTo[Ok]
          .map(result => s"I got a response: ${result}")
          .recover { case _ => "error" })
          }
         }
      }

}
