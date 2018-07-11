package com.github.gzilt.regression

import org.apache.predictionio.controller.EngineFactory
import org.apache.predictionio.controller.Engine

case class Query(
  attr0: Int,
  attr1: Int,
  attr2: Double,
  attr3: Double,
  attr4: Double,
  attr5: Double,
  attr6: Int
)

case class PredictedResult(
  prediction: Double
)

case class ActualResult(
   prediction: Double
)

object RegressionEngine extends EngineFactory {
  def apply() = {
    new Engine(
      classOf[DataSource],
      classOf[Preparator],
      Map("sgd" -> classOf[LinearRegressionAlgorithm]),
      classOf[Serving])
  }
}
