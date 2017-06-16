package io.slick.db

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime}

import slick.jdbc.PostgresProfile.api._

trait DbMappers {

  implicit val localDateMapper =
    MappedColumnType.base[LocalDate, Date](
      ld => Date.valueOf(ld),
      d => LocalDate.from(d.toLocalDate)
    )

  implicit val localDateTimeMapper =
    MappedColumnType.base[LocalDateTime, Timestamp](
      ldt => Timestamp.valueOf(ldt),
      ts => LocalDateTime.from(ts.toLocalDateTime)
    )
}
