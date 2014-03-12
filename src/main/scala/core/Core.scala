package core

import akka.actor.ActorSystem

/**
 * Created by Nikola on 3/11/14.
 */
trait Core {
  implicit def system: ActorSystem
}
