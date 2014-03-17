package models

import java.util.UUID
import models.ErrorStatus.ErrorStatus

object ErrorStatus extends Enumeration {
  type ErrorStatus = Value
  val None, NotAuthenticated, NotAutorized, BadAuthGuidFormat, MissingAuthGuid, AuthExpired = Value
}
import ErrorStatus._

case class NDApiRequest[G] (
  ClientGuid: UUID,
  AuthGuyd: UUID,
  data: G
)

case class NDApiResponse[G] (
  ErrorStatus: ErrorStatus,
  ErrorMessage: String,
  data: G
)
