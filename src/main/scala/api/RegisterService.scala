package api

import akka.actor.ActorRef
import scala.concurrent.ExecutionContext
import spray.routing.Directives
import models.ndapidtos.{RegisterModel, DeviceRegisterModel}

/**
 * Created by nikolatonkev on 2014-05-20.
 */
class RegisterService(registering: ActorRef)(implicit context: ExecutionContext)
  extends Directives with  DefaultJsonFormats  {

  implicit val DeviceRegisterFormater = jsonFormat2(DeviceRegisterModel)
  implicit val RegisterFormater = jsonFormat5(RegisterModel)
  //implicit val NDResponseFormater = jsonFormat3(NDApiResponse[Person])


  val regroute =
    path("register") {
      entity(as[RegisterModel]) { ent =>
        put {
          complete {
            ent
          }
        }
      }
    }~
    path("registerdevice") {
      entity(as[DeviceRegisterModel]) { ent =>
        put {
          complete {
            ent
          }
        }
      }
  }
}
