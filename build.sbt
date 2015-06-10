name := "sbt_test "


version := "1.0"


scalaVersion  := "2.10.4"


mainClass in (Compile, run) := Some("hashset")


libraryDependencies += "com.twitter" %% "algebird-core" % "0.9.0"