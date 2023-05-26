ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "TestProject1",
    idePackagePrefix := Some("com.knoldus")
  )

libraryDependencies+="com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
libraryDependencies += "software.amazon.awssdk" % "s3" % "2.20.65" % Test
libraryDependencies += "com.amazonaws" % "aws-java-sdk-sqs" % "1.12.468"
libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.12.468"
libraryDependencies += "org.elasticmq" %% "elasticmq-server" % "1.3.14"
libraryDependencies+= "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test"
libraryDependencies += "software.amazon.awssdk" % "sqs" % "2.20.26"
libraryDependencies +="org.scalatest" %% "scalatest" % "3.2.15" % Test
