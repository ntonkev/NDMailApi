package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef, ActorRefFactory}
import akka.util.Timeout
import models._

import spray.routing._
import scala.concurrent.ExecutionContext
import api.TestActor._
import Auth.{AuthenticationDirectives}
import spray.http.HttpResponse
import models.NDApiRequest
import models.Person
import models.AuthTokens
import spray.json.JsObject

class TestService(testing: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats with AuthenticationDirectives
{

  import scala.concurrent.duration._
  implicit val timeout = Timeout(5.seconds)

  implicit val PersonFormater = jsonFormat4(Person)
  implicit val NDRequestFormater = jsonFormat3(NDApiRequest[Person])
  implicit val NDResponseFormater = jsonFormat3(NDApiResponse[Person])

  val route =
    path("test") {
      get {
        //handleWith { tt: Test => testing ! tt }

          /* Works fine */
          complete {
            "Test route OK"
          }

      }
    }~
    path("person") {
      entity(as[NDApiRequest[Person]]) { ent =>
          get {
            val tokens = new AuthTokens(ent.AuthGuyd, ent.DeviceUniqueId)
            authenticate(authenticateUser(tokens)) {
              st =>
                complete {
                  val person = GetPerson(1)
                  val response = new NDApiResponse[Person](ErrorStatus.None, "", person)
                  response
                 }
            }
          }
      }
    }
}
