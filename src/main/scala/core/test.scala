package core

import akka.actor.{ActorLogging, Actor, ActorRef}
import scala.concurrent.ExecutionContext
import spray.routing.Directives
import spray.routing._
import spray.can.server.Stats
import spray.http.StatusCodes._
import spray.can.Http
import akka.pattern.ask
import api.TestService

import spray.httpx.Json4sSupport
/**
 * Created by Nikola on 3/4/14.
*/
class TestActor extends Actor with TestService with ActorLogging{
  def actorRefFactory = context
  def receive = runRoute(testRoute)
}
