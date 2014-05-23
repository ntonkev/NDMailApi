package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef, ActorRefFactory}
import akka.util.Timeout
import models._
import scala.slick.driver.PostgresDriver.simple._

import spray.routing._
import scala.concurrent.ExecutionContext
import api.TestActor._
import Auth.{AuthenticationDirectives}
import spray.http.HttpResponse
import models.NDApiRequest
import models.Person
import models.AuthTokens
import spray.json.JsObject
import core.Core
import java.util.Properties

class TestService(testing: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats with AuthenticationDirectives
{

  import scala.concurrent.duration._
  implicit val timeout = Timeout(5.seconds)

  implicit val PersonFormater = jsonFormat4(Person)
  implicit val NDRequestFormater = jsonFormat3(NDApiRequest[Person])
  implicit val NDResponseFormater = jsonFormat3(NDApiResponse[Person])


  //http GET http://localhost:8080/test
  //http GET http://localhost:8080/person < person_wrong_auth.json
  //http GET http://localhost:8080/person < person_auth_ok.json

  val route =
    path("test") {
      get {
        //handleWith { tt: Test => testing ! tt }
        /* Works fine */

        try {
          val URL = "jdbc:postgresql://ec2-54-221-223-92.compute-1.amazonaws.com:5432/db7k8198l73h6l"
          val username = "aypkpqlvwdznkk"
          val password = "blItMMzvKwWjEFI1ItcWhc-uix"
          val props = new Properties
          props.setProperty("ssl", "true")
          props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory")

          val database = Database.forURL(URL, username, password, props)

          val query = for (u <- dal.DAL.users) yield u.email

          val result = database.withSession{
            session => query.list() (session)
          }

          println(result(0).toString())

        } catch {
          case e: Exception =>
            println(e.getStackTraceString)
        }
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
                  /*
                  val person = GetPerson(1)
                  val response = new NDApiResponse[Person](ErrorStatus.None, "", person)
                  response
                  */
                  GetPerson(1)
                 }
            }
          }
      }
    }
}
