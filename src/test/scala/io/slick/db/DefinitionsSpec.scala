package io.slick.db

import com.typesafe.scalalogging.LazyLogging
import io.slick.db.{Address, DB, Person, DbTables}
import io.slick.util.JsonFormat
import org.json4s.jackson.JsonMethods.parse
import org.scalatest.{FlatSpec, Matchers}
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.{Codec, Source}


class DefinitionsSpec extends FlatSpec with DbTables with LazyLogging with DB with Matchers with JsonFormat {

  it should "add persons" in {
    val action = DBIO.seq(
      persons ++= personData
    )
    try {
      Await.result(db.run(action), 1.second)
    } catch {
      case e : Exception => logger.error(e.getMessage)
    }
  }

  it should "add addresses" in {
    val action = DBIO.seq(
      addresses ++= addressData
    )
    try {
      Await.result(db.run(action), 1.second)
    } catch {
      case e : Exception => logger.error(e.getMessage)
    }
  }

  it should "add events" in {
    val action = DBIO.seq(
      events ++= eventData
    )
    try {
      Await.result(db.run(action), 1.second)
    } catch {
      case e : Exception => logger.error(e.getMessage)
    }
  }

  def personData: Seq[Person] = createJsonFromResource[Person]("data/persons.json")

  def addressData: Seq[Address] = createJsonFromResource[Address]("data/addresses.json")

  def eventData: Seq[Event] = createJsonFromResource[Event]("data/events.json")

  def createJsonFromResource[T](resource: String)(implicit m: Manifest[T]): Seq[T] = {
    val raw = Source.fromResource(resource)(Codec.UTF8).getLines().mkString
    parse(raw).extract[Seq[T]]
  }

}
