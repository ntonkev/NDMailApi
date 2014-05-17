package dal

object Tables extends {
  val profile = scala.slick.driver.PostgresDriver
} with tables

trait tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  import scala.slick.jdbc.{GetResult => GR}

}
