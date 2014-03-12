package core

import akka.actor.Props
import api.TestWorkerActor

/**
 * Created by Nikola on 3/11/14.
 */
trait CoreActors {
  this: Core =>
    val test = system.actorOf(Props[TestWorkerActor])
    //val test = system.actorOf(Props[TestActor])

}
