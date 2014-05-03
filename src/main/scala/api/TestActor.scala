package api

import akka.actor.{Actor, ActorLogging}
import spray.routing.{Directives, HttpService}
import models.Person


object TestActor{
  case class Test
  case class GetPerson(personId: Int)
}

trait PersonBLL{

  def GetPersonById(personId: Int) = {
    //val person = new Person(1, "Nikola", "Tonkev", 36)
    val person = new Person(1)
    person
  }

}

class TestActor extends Actor with PersonBLL
{
  import TestActor._


  def receive = {
    case Test => sender ! Left(Test)
    case GetPerson(personId) => sender ! GetPersonById(personId)
  }


}
