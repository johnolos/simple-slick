package io.slick.app

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import io.slick.db.{DB, DbTables}
import slick.jdbc.PostgresProfile.api._
import io.slick.db.{Address, Event, Person}

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object Application extends App with DB with DbTables with LazyLogging {

  override def main(args: Array[String]): Unit = {
    val simpleQueries = new SimpleQueries()
    //simpleQueries.exampleOne()
    //simpleQueries.exampleTwo()
    simpleQueries.exampleThree()
  }

}
