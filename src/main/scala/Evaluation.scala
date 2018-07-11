package com.github.gzilt.regression

import org.apache.predictionio.controller.AverageMetric
import org.apache.predictionio.controller.EmptyEvaluationInfo
import org.apache.predictionio.controller.EngineParams
import org.apache.predictionio.controller.EngineParamsGenerator
import org.apache.predictionio.controller.Evaluation

case class Accuracy()
  extends AverageMetric[EmptyEvaluationInfo, Query, PredictedResult, ActualResult] {
  def calculate(query: Query, predicted: PredictedResult, actual: ActualResult)
  : Double = (if (predicted.prediction == actual.prediction) 1.0 else 0.0)
}

object AccuracyEvaluation extends Evaluation {
  // Define Engine and Metric used in Evaluation
  engineMetric = (RegressionEngine(), Accuracy())
}

object EngineParamsList extends EngineParamsGenerator {
  // Define list of EngineParams used in Evaluation

  // First, we define the base engine params. It specifies the appId from which
  // the data is read, and a evalK parameter is used to define the
  // cross-validation.
  private[this] val baseEP = EngineParams(
    dataSourceParams = DataSourceParams(appName = "pio-regression", evalK = Some(5)))

  // Second, we specify the engine params list by explicitly listing all
  // algorithm parameters. In this case, we evaluate 3 engine params, each with
  // a different algorithm params value.
  engineParamsList = Seq(
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.001)))),
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.0001)))),
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.00001)))),
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.000001)))),
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.0000001)))),
    baseEP.copy(algorithmParamsList = Seq(("sgd", AlgorithmParams(100, 0.01))))
  )
}
