# Simple Slick Setup

A simple Slick setup with a dockerized postgres-database.

Pull the repository for a quick and dirty playground with Slick.

## Installation
To run the application, you need `sbt`.

Installation guide found on [http://www.scala-sbt.org/](http://www.scala-sbt.org/)

## Database setup
The application requires a running PostgreSQL database. By default, it assumes that
the server runs on `localhost:5432`, with a database named `slick`, a user
`slick`, and a password `slick`  password as defined in `build`-file and `application.conf`. These connection parameters are set in 
`application.conf`, and can be overridden by setting the following environment variables:

- `POSTGRES_JDBC_URL`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`

To set up a development PostgreSQL server with the default settings with docker, do the following:
```bash
$ chmod +x bin/start-postgres.sh
$ ./bin/start-postgres.sh
```

You'll need docker though (doh).

## Database migrations

### Using flyway

Use ```$ sbt flywayClean``` to clean your database and ```$ sbt flywayMigrate``` to run the migration process with flyway.

### Using Scala Forklift

See ```Migrations.scala``` for how to migrate your database with Scala Forklift.

## Development Setup

### Docker installation and running local postgres docker container

See the contents of `bin/start-postgres.sh`.

Remember to run Docker-application on macOS or enable service on Linux.

For installation details visit [https://www.docker.com/](https://www.docker.com/)

More details on the postgres docker container and configuration options, visit [https://hub.docker.com/_/postgres/](https://hub.docker.com/_/postgres/).

### Mockaroo
Mock data is generated at [http://mockaroo.com/](http://mockaroo.com/).

The schemas for the generated .json-data is available here:
* Person-schema at [https://www.mockaroo.com/81b4be40](https://www.mockaroo.com/81b4be40)
* Event-schema at [https://www.mockaroo.com/4cd2c3a0](https://www.mockaroo.com/4cd2c3a0)
* Addresses-schema at [https://www.mockaroo.com/ef80ed20](https://www.mockaroo.com/ef80ed20)


### Debugging purposes

Should you need it, you can go directly into the postgres database to see how state looks like.

Do the following steps:
```
$ docker exec -it {name} sh
# su postgres
# psql
# \c
# select * from persons;
```
