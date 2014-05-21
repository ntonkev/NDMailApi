package api

import spray.routing.{RouteConcatenation}
import akka.actor.{Props, ActorRefFactory}
import core.{Core, CoreActors}
import web.RoutedHttpService

trait Api extends RouteConcatenation {
  this: CoreActors with Core =>

    private implicit val _ = system.dispatcher

    val routes =
      new TestService(testing).route ~
      new RegisterService(registering).regroute


  val rootService = system.actorOf(Props(new RoutedHttpService(routes)))

}
