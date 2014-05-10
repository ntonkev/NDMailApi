package web

import spray.http.StatusCodes._
import spray.http._
import spray.routing._
import akka.actor.Actor
import spray.util.{LoggingContext, SprayActorLogging}
import scala.util.control.NonFatal
import api.DefaultJsonFormats
import models.{ErrorStatus, NDApiResponse}

case class ErrorResponseException(responseStatus: StatusCode, response: Option[HttpEntity]) extends Exception

class RoutedHttpService(route: Route) extends Actor with HttpService with SprayActorLogging with  DefaultJsonFormats {

  implicit val NDResponseFormater = jsonFormat3(NDApiResponse[String])

  implicit def actorRefFactory = context

  /*
  implicit val NDRejectionHandler = RejectionHandler{
    case AuthenticationFailedRejection(CredentialsMissing, realm) :: _ => ctx => {
      val response = new NDApiResponse[String](ErrorStatus.NotAuthenticated, "This request have not been authorized !!!", "")
      ctx.complete(response)
    }
  }
  */

  implicit val handler = ExceptionHandler {
    case NonFatal(ErrorResponseException(statusCode, entity)) => ctx =>
      ctx.complete(statusCode, entity)

    case NonFatal(e) => ctx => {
      log.error(e, InternalServerError.defaultMessage)
      ctx.complete(InternalServerError)
    }
  }

  //implicit val rejectionHandler = NDRejectionHandler
  implicit val rejectionHandler = RejectionHandler.Default

  def receive: Receive =
    runRoute(route)(handler, rejectionHandler, context, RoutingSettings.default, LoggingContext.fromActorRefFactory)
}
