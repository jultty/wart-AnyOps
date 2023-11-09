val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "hello-scala",
    version := "0.1.0",
    scalaVersion := scala3Version,

    logLevel := Level.Warn,
    run / watchLogLevel := Level.Warn,

    Global / onChangedBuildSource := ReloadOnSourceChanges,

    wartremoverErrors ++= Warts.unsafe,
    wartremoverErrors ++= Seq(Wart.Nothing, Wart.ImplicitConversion),
    wartremoverErrors += ContribWart.OldTime,
  )
