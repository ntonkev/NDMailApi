package api

import akka.actor.{Actor, ActorLogging}
import spray.routing.{Directives, HttpService}

object TestActor{
  case class Test
}


class TestActor extends Actor
{
  import TestActor._


  def receive = {
    case Test => sender ! Left(Test)
  }


}
