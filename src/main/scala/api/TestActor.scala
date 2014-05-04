package api

import akka.actor.{Actor, ActorLogging}
import spray.routing.{Directives, HttpService}
import models.Person

object TestActor{
  //case class Test
  //case class GetPerson(personId: Int)

  def GetPerson(personId: Int): Person = {
    val name: Option[String] = Some("Desy")
    val family: Option[String] = Some("Slaveva")
    val age: Option[Int] = Some(38)
    val person = new Person(37, name, family, age)
    person
  }
}

class TestActor extends Actor
{
  import TestActor._

  def GetPersonById(personId: Int) = {
    //Console.println("IN GET PERSON BY ID !!!")
    val person: Person = new Person(1)
    person
  }

  /* OLD VERSION OBSOLID !!! */
  /*
  def receive = {
    case Test => sender ! Left(Test)
    case GetPerson(personId) => sender ! GetPersonById(personId)
  }
  */

  def receive = {
    case x: Int => sender ! GetPerson(x)
  }


}
