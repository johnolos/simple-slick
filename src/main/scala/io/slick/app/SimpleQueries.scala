package io.slick.app

import java.time.{LocalDate, LocalDateTime}

import com.typesafe.scalalogging.LazyLogging
import io.slick.db._
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}


class SimpleQueries(implicit db: Database, implicit val executor: ExecutionContext) extends DbTables
  with DbLiterals with LazyLogging {


  def exampleOne(): Unit = {
    val q1 = for {
      e <- events if e.city === "Oslo"
    } yield e

    val statement1 = q1.result.statements
    statement1.foreach(s => logger.info(s))

    /**
      * select "id", "title", "person_id", "start_time", "end_time", "street", "city", "zip_code", "country"
      * from "events"
      * where "city" = 'Oslo'
      */

    val results1 = db.run(q1.result)

    results1 onComplete {
      case Success (r) => {
        logger.info (r.take(1).toString)
        logger.info (s"Number of results in Oslo: ${r.size}")
      }
      case Failure (e) => logger.error (e.getMessage, e)
    }

    val q2 = events.filter(_.city === "Trondheim")

    val statement2 = q2.result.statements
    statement2.foreach(s => logger.info(s))

    /**
      * select "id", "title", "person_id", "start_time", "end_time", "street", "city", "zip_code", "country"
      * from "events"
      * where "city" = 'Trondheim'
      */

    val results2 = db.run(q2.result)

    results2 onComplete {
      case Success(r) => {
        logger.info(r.take(1).toString)
        logger.info(s"Number of events in Trondheim is ${r.size}")
      }
      case Failure(e) => logger.error(e.getMessage, e)
    }
  }


  def exampleTwo(): Unit = {

    val query = events.filter(_.to <= current_date)

    val statement = query.result.statements
    statement.foreach(s => logger.info(s))

    /**
      * select "id", "title", "person_id", "start_time", "end_time", "street", "city", "zip_code", "country"
      * from "events"
      * where "start_time" <= current_date
      */

    val results = db.run(query.result)

    results onComplete {
      case Success (r) => {
        logger.info(r.take(1).toString)
        logger.info(s"Number of past events: ${r.size}")
      }
      case Failure (e) => logger.error (e.getMessage, e)
    }
  }

  def exampleThree(): Unit = {

    val query = for {
      event <- events if event.from >= LocalDateTime.now() && event.to <= LocalDateTime.now().plusWeeks(1)
      person <- persons if event.personId === person.id
    } yield (event.title, person.firstname, person.lastname)

    val statement = query.result.statements
    statement.foreach(s => logger.info(s))

    /**
      * select x2."title", x3."firstname", x3."lastname"
      * from "events" x2, "persons" x3
      * where ((x2."start_time" >= {ts '2017-06-15 23:32:36.639'})
      *   and (x2."start_time" <= {ts '2017-06-22 23:32:36.645'}))
      *   and (x2."person_id" = x3."id")
      */

    //

    val results = db.run(query.result)

    results onComplete {
      case Success (r) => {
        logger.info(r.take(1).toString)
        logger.info(s"Number of upcomming events: ${r.size}")
      }
      case Failure (e) => logger.error (e.getMessage, e)
    }

  }
}
