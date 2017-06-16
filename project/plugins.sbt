resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.1.1")
addSbtPlugin("org.scalatra.scalate" % "sbt-scalate-precompiler" % "1.8.0.1")
addSbtPlugin("org.scalatra.sbt" % "scalatra-sbt" % "0.5.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.5")
