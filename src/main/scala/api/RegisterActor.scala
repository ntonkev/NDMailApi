package api

import akka.actor.Actor
import models.ndapidtos.{RegisterModel, DeviceRegisterModel}
import utils.{NDApiLogging}
import models.{ErrorStatus, NDApiResponse}
import java.util.logging.ErrorManager

/**
 * Created by nikolatonkev on 2014-05-20.
 */
object RegisterActor extends NDApiLogging {

  def RegisterDevice(model: DeviceRegisterModel): Boolean = {
    errorLogger.error("Just a test!")
    true
  }

  def Register(model: RegisterModel): Boolean = {
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
