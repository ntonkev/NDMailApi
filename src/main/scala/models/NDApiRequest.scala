package models

import java.util.UUID

case class NDApiRequest[G] (
  ClientGuid: UUID,
  AuthGuyd: UUID,
  data: G
)

case class NDApiResponse[G] (
  ErrorMessage: String,
  data: G
)
