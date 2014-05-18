package dal

import scala.slick.driver.PostgresDriver.simple._
import java.util.UUID

class user extends tables {
  val profile = scala.slick.driver.PostgresDriver
/*
  def findById(id: UUID)(implicit s:Session){
    users.where(_.userid.toString() == id.toString()).firstOption()
  }

  def findByName(name: String)(implicit s: Session){
    users.where(_.username like name).list()
  }

  def insert(user: UserRow)(implicit s: Session) {
    users.insert(user)
  }

  def update(id: UUID, user: UserRow)(implicit s: Session) {
    users.where(_.userid.eq(id)).update(user)
  }
*/
}