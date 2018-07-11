package com.github.gzilt.regression

import org.apache.predictionio.controller.OptionAverageMetric
import org.apache.predictionio.controller.EmptyEvaluationInfo
import org.apache.predictionio.controller.Evaluation

case class Precision(prediction: Double)
  extends OptionAverageMetric[EmptyEvaluationInfo, Query, PredictedResult, ActualResult] {
  override def header: String = s"Precision(prediction = $prediction)"

  def calculate(query: Query, predicted: PredictedResult, actual: ActualResult)
  : Option[Double] = {
    if (predicted.prediction == prediction) {
      if (predicted.prediction == actual.prediction) {
        Some(1.0)  // True positive
      } else {
        Some(0.0)  // False positive
      }
    } else {
      None  // Unrelated case for calculating precision
    }
  }
}

object PrecisionEvaluation extends Evaluation {
  engineMetric = (RegressionEngine(), Precision(prediction = 1.0))
}
