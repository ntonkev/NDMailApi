package core

import akka.actor.Props
import api.{TestActor}

trait CoreActors{
  this: Core =>
    val testing = system.actorOf(Props[TestActor])
}
