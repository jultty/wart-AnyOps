val scala3Version = "3.3.1"

lazy val myWarts = project.in(file(".warts")).settings(
  scalaVersion := scala3Version,
  scalaSource in Compile := baseDirectory.value / "src" / "main" / "warts",
  libraryDependencies ++= Seq(
    "org.wartremover" % "wartremover" % wartremover.Wart.PluginVersion cross CrossVersion.full
  ),
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "hello-scala",
    version := "0.1.0",
    scalaVersion := scala3Version,
    scalaSource in Compile := baseDirectory.value / "src" / "main",

    logLevel := Level.Warn,
    run / watchLogLevel := Level.Warn,

    Global / onChangedBuildSource := ReloadOnSourceChanges,


    // wartremoverErrors ++= Warts.unsafe,
    wartremoverErrors ++= Seq(
      Wart.ArrayEquals, Wart.AnyVal, Wart.Equals, Wart.ExplicitImplicitTypes,
      Wart.FinalCaseClass, Wart.ImplicitConversion, 
      Wart.JavaConversions, Wart.JavaSerializable, Wart.LeakingSealed, 
      Wart.Nothing, Wart.Option2Iterable, Wart.PublicInference,
    ),
    wartremoverErrors ++= Seq(
      ContribWart.OldTime, ContribWart.UnsafeInheritance,
      ContribWart.MissingOverride, ContribWart.NoNeedForMonad, 
      ContribWart.UnintendedLaziness, ContribWart.DiscardedFuture,
    ),

    wartremoverErrors += Wart.custom("mywarts.Unimplemented"),
    wartremover.WartRemover.dependsOnLocalProjectWarts(myWarts),
  )

