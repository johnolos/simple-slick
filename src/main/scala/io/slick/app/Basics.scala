package io.slick.app

import java.time.LocalDate

import com.typesafe.scalalogging.LazyLogging
import io.slick.db._
import slick.dbio.DBIO
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext


class Basics(implicit db: Database, implicit val executor: ExecutionContext) extends DbTables
  with DbLiterals with LazyLogging {


  def insert(): Unit = {

    val ola = Person(None, "Ola", "Nordmann", Some(LocalDate.of(1978, 5, 20)), Gender.Male)

    val insertStatements = DBIO.seq(
      persons += Person(None, "Ola", "Nordmann", Some(LocalDate.of(1978, 5, 23)), Gender.Male),

      persons ++= Seq(
        Person(None, "Kari", "Nordmann", None, Gender.Female),
        Person(None, "Bente", "Nordmann", Some(LocalDate.of(1998,8,15)), Gender.Female)
      )
    )

    // val statement = insertStatements.transactionally.result

    // db.run(insertStatements.)
  }

  def update(): Unit = {

  }

  def delete(): Unit = {

  }

}
