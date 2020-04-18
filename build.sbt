import sbtrelease.ReleaseStateTransformations._

lazy val root = (project in file("."))
  .settings(
    name := "spy",
    description := "Pretty printed human readable String inspection of Scala objects",
    organization := "com.gu",
    organizationName := "The Guardian",
    scalaVersion := "2.13.1",
    Compile / doc / sources := List(),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      publishArtifacts,
      setNextVersion,
      commitNextVersion,
      releaseStepCommand("sonatypeReleaseAll"),
      pushChanges
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.2" % Test,
  )

