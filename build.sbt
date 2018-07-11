name := "com.github.gzilt.regression"

scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
  "org.apache.predictionio" %% "apache-predictionio-core" % "0.12.1" % "provided",
  "org.apache.spark"        %% "spark-core"               % "2.1.1" % "provided",
  "org.apache.spark"        %% "spark-mllib"              % "2.1.1" % "provided",
  "io.netty"                % "netty-all"                 % "4.1.25.Final",
  "org.xerial.snappy"       % "snappy-java"               % "1.1.1.7",
  "com.esotericsoftware"    % "kryo"                      % "4.0.1"
)
