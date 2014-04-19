package Auth

import models.{ErrorStatus, AuthTokens}

/**
 * Created by nikolatonkev on 2014-04-19.
 */
class AuthController {

  def CheckAuth(token: AuthTokens) = {
    if(token.AuthGuyd.equals(None)){
      ErrorStatus.MissingAuthGuid
    }
    if(token.DeviceUniqueId.equals(None)){
      ErrorStatus.MissingDevUniqueId
    }

    ErrorStatus.None

  }

  def doAuth(token: AuthTokens) = {
    CheckAuth(token)
  }


}
