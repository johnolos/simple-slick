package io.slick.db

import java.time.{LocalDate, LocalDateTime}

import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery


trait DbTables extends DbMappers {

  class PersonTable(tag: Tag) extends Table[Person](tag, "persons") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def firstname = column[String]("firstname")
    def lastname = column[String]("lastname")
    def birthday = column[Option[LocalDate]]("birthday")
    def gender = column[Gender.Gender]("gender")
    def * = (id.?, firstname, lastname, birthday, gender) <>
      (Person.tupled, Person.unapply)
  }

  val persons: TableQuery[PersonTable] = TableQuery[PersonTable]

  class AddressTable(tag: Tag) extends Table[Address](tag, "addresses") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def personId = column[Int]("person_id")
    def street = column[String]("street")
    def city = column[String]("city")
    def zipCode = column[String]("zip_code")
    def country = column[String]("country")
    def * = (id.?, personId, street, city, zipCode, country) <>
      (Address.tupled, Address.unapply)

    def person = foreignKey("fk_person_id", personId, persons)(_.id)
  }

  val addresses: TableQuery[AddressTable] = TableQuery[AddressTable]

  class EventTable(tag: Tag) extends Table[Event](tag, "events") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def personId = column[Int]("person_id")
    def from = column[LocalDateTime]("start_time")
    def to = column[LocalDateTime]("end_time")
    def street = column[String]("street")
    def city = column[String]("city")
    def zipCode = column[String]("zip_code")
    def country = column[String]("country")
    def * = (id.?, title, personId, from, to, street, city, zipCode, country) <>
      (Event.tupled, Event.unapply)

    def person = foreignKey("fk_person_id", personId, persons)(_.id)
  }

  val events: TableQuery[EventTable] = TableQuery[EventTable]
}
