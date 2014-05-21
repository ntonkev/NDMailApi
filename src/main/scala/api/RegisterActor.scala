package api

import akka.actor.Actor
import models.ndapidtos.{RegisterModel, DeviceRegisterModel}

/**
 * Created by nikolatonkev on 2014-05-20.
 */
object RegisterActor {

  def RegisterDevice(model: DeviceRegisterModel) = {
    true
  }

  def Register(model: RegisterModel) = {
    true
  }

}

class RegisterActor extends Actor {

  import RegisterActor._

  def receive = {
    case (model: DeviceRegisterModel) => sender ! RegisterDevice(model)
    case (model: RegisterModel) => sender ! Register(model)
  }

}
