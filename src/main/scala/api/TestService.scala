package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef, ActorRefFactory}
import akka.util.Timeout
import models.{ErrorStatus, AuthTokens, NDApiRequest, Person}

import spray.routing._
import scala.concurrent.ExecutionContext
import api.TestActor.{GetPerson, Test}
import Auth.{AuthenticationDirectives}
import models.ErrorStatus.ErrorStatus
import models.ErrorStatus

class TestService(testing: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats with AuthenticationDirectives
{

  import scala.concurrent.duration._
  implicit val timeout = Timeout(5.seconds)

  implicit val PersonFormater = jsonFormat4(Person)
  implicit val NDRequestFormater = jsonFormat3(NDApiRequest[Person])

  val route =
    path("test") {
      get {
        //handleWith { tt: Test => testing ! tt }

          /* Works fine */
          complete {
            "OK"
          }

      }
    }~
    path("person") {
      entity(as[NDApiRequest[Person]]) { ent =>
          get {
            val tokens = new AuthTokens(ent.AuthGuyd, ent.DeviceUniqueId)
            authenticate(authenticateUser(tokens)) { st =>
              complete {
                ent
              }
            }
          }
      }
    }
}
