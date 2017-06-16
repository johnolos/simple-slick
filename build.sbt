import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import ScalateKeys._

val ScalatraVersion = "2.5.0"
val Json4sVersion = "3.5.0"
val AkkaVersion = "2.4.17"
val PostgresVersion = "42.0.0"
val SlickVersion = "3.2.0"
val ScalaTestVersion = "3.0.1"

ScalatraPlugin.scalatraSettings

flywayUrl := sys.env.getOrElse("POSTGRES_JDBC_URL", default = "jdbc:postgresql://localhost:5432/slick")
flywayUser := sys.env.getOrElse("POSTGRES_USER", default = "slick")
flywayPassword := sys.env.getOrElse("POSTGRES_PASSWORD", default = "slick")

scalateSettings

organization := "io.slick"

name := "one"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.1"

resolvers += Classpaths.typesafeReleases
resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "org.flywaydb" % "flyway-core" % "4.1.1",
  "org.postgresql" % "postgresql" % PostgresVersion,
  "org.json4s"   %% "json4s-jackson" % Json4sVersion,
  "org.json4s"   %% "json4s-ext" % Json4sVersion,
  "org.scalactic" %% "scalactic" % ScalaTestVersion,
  "org.scalatest" %% "scalatest" % ScalaTestVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.1" % "runtime",
  "com.github.tminglei" %% "slick-pg" % "0.15.0-RC",
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion
)

// For debugging in IntelliJ IDEA
javaOptions ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

scalateTemplateConfig in Compile := {
  val base = (sourceDirectory in Compile).value
  Seq(
    TemplateConfig(
      base / "webapp" / "WEB-INF" / "templates",
      Seq.empty,  /* default imports should be added here */
      Seq(
        Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
      ),  /* add extra bindings here */
      Some("templates")
    )
  )
}
