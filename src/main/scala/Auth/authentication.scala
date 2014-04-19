import Auth.AuthController
import models.AuthTokens
import spray.routing.{
HttpService, RequestContext,
AuthenticationFailedRejection,
AuthenticationRequiredRejection
}

import concurrent.Future
import spray.routing.authentication.Authentication

trait AuthenticationDirectives {
  this: HttpService =>


  def doAuthenticate(token: AuthTokens)

}

trait UsersAuthenticationDirectives
  extends AuthenticationDirectives {
  this: HttpService =>

  val authController = new AuthController

  override def doAuthenticate(token: AuthTokens) = {
    //Future {
      authController.doAuth(token)
    //}
  }
}