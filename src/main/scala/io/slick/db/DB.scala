package io.slick.db

import akka.actor.ActorSystem
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext


trait DB {
  implicit val db: Database = Database.forConfig("slick")
  protected implicit def executor: ExecutionContext = ActorSystem().dispatcher
}
