package api

import akka.actor.{Actor, ActorLogging}
import spray.can.parsing.Result.Ok

/**
 * Created by Nikola on 3/5/14.
 */
object TestWorkerActor{
  //case class Ok(id: Int)
  case class Test(str: String)
}

class TestWorkerActor extends Actor with ActorLogging{
  import TestWorkerActor._

  def receive = {
    case Test(str) =>
    {
      log.info(s"Test ${str}")
      sender ! Test(str)
    }
  }
}
