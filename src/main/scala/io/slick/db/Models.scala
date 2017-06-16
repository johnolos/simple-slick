package io.slick.db

import java.time.{LocalDate, LocalDateTime}

import slick.jdbc.PostgresProfile.api._

object Gender extends Enumeration {
  type Gender = Value
  val Male = Value("M")
  val Female = Value("F")

  implicit val genderMapper =
    MappedColumnType.base[Gender.Gender, String](e => e.toString, s => Gender.withName(s))
}

case class Person(id: Option[Int], firstname: String, lastname: String, birthday: Option[LocalDate], gender: Gender.Gender)

case class Address(id: Option[Int], personId: Int, street: String, city: String, zipCode: String, country: String)

case class Event(id: Option[Int], title: String, personId: Int, from: LocalDateTime, to: LocalDateTime, street: String, city: String, zipCode: String, country: String)
