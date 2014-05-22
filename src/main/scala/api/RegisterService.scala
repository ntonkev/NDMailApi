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

  //http PUT http://localhost:8080/register < register.json
  //http PUT http://localhost:8080/registerdevice < registerdevice.json
  val route =
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
