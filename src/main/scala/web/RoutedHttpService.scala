package web

import spray.http.StatusCodes._
import spray.http._
import spray.routing._
import akka.actor.Actor
import spray.util.{LoggingContext, SprayActorLogging}
import scala.util.control.NonFatal
import Auth.AuthenticationDirectives


case class ErrorResponseException(responseStatus: StatusCode, response: Option[HttpEntity]) extends Exception

class RoutedHttpService(route: Route) extends Actor with HttpService with SprayActorLogging {

  implicit def actorRefFactory = context

  implicit val handler = ExceptionHandler {
    case NonFatal(ErrorResponseException(statusCode, entity)) => ctx =>
      ctx.complete(statusCode, entity)

    case NonFatal(e) => ctx => {
      log.error(e, InternalServerError.defaultMessage)
      ctx.complete(InternalServerError)
    }
  }


  def receive: Receive =
    runRoute(route)(handler, RejectionHandler.Default, context, RoutingSettings.default, LoggingContext.fromActorRefFactory)
}
