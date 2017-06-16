package io.slick.db

import slick.jdbc.PostgresProfile.api._


trait DbLiterals extends DbMappers {
  val current_date =
    SimpleLiteral[java.time.LocalDateTime]("current_date")
}
