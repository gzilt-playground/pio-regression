package com.github.gzilt.regression

import org.apache.predictionio.controller.Evaluation
import org.apache.predictionio.controller.MetricEvaluator

object CompleteEvaluation extends Evaluation {
  engineEvaluator = (
    RegressionEngine(),
    MetricEvaluator(
      metric = Accuracy(),
      otherMetrics = Seq(Precision(0.0), Precision(1.0), Precision(2.0)),
      outputPath = "best.json"))
}