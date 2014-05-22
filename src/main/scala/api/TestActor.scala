package api

import akka.actor.{Actor, ActorLogging}
import spray.routing.{Directives, HttpService}
import models.Person
import utils.NDApiLogging

object TestActor extends NDApiLogging {
  //case class Test
  //case class GetPerson(personId: Int)

  def GetPerson(personId: Int): Person = {
    try{
      val name: Option[String] = Some("Desy")
      val family: Option[String] = Some("Slaveva")
      val age: Option[Int] = Some(38)
      val person = new Person(personId, name, family, age)
      person
    }
    catch {
      case e: Exception => {
        errorLogger.error(e.getStackTraceString)
      }
      null
    }
  }
}

class TestActor extends Actor
{
  import TestActor._
  /*
  def GetPersonById(personId: Int) = {
    val person: Person = new Person(1)
    person
  }
  */


  def receive = {
    case x: Int => sender ! GetPerson(x)
  }


}
