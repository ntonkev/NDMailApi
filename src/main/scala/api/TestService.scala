package api

import spray.routing.{HttpService, Directives}
import akka.actor.{Props, ActorRef, ActorRefFactory}
import akka.util.Timeout
import models.{NDApiRequest, Person}

import spray.routing._
import scala.concurrent.ExecutionContext
import api.TestActor.{GetPerson, Test}


class TestService(testing: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats {

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
    path("person"){
      //entity(as[Person]){ ent =>
      entity(as[NDApiRequest[Person]]){ ent =>
        get {
          complete{
            ent
            //GetPerson(personId)
          }
        }
      }
    }

}
