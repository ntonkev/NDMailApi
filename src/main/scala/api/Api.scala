package api

import spray.routing.RouteConcatenation
import akka.actor.{Props, ActorRefFactory}
import core.{Core, CoreActors}
import web.RoutedHttpService

/**
 * Created by Nikola on 3/11/14.
 */
trait Api extends RouteConcatenation{
  this: CoreActors with Core =>

    private implicit val _ = system.dispatcher

    val routes =
      new TestService(test).testRoute

    val rootService = system.actorOf(Props(new RoutedHttpService(routes)))


}
