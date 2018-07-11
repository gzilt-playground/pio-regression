package com.github.gzilt.regression

import org.apache.predictionio.controller.{P2LAlgorithm, Params}

import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.regression.LinearRegressionModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkContext
import grizzled.slf4j.Logger

case class AlgorithmParams(iterations: Int, stepSize: Double) extends Params


class LinearRegressionAlgorithm(val ap: AlgorithmParams)
  extends P2LAlgorithm[PreparedData, LinearRegressionModel, Query, PredictedResult] {

    @transient lazy val logger = Logger[this.type]

    def train(sc: SparkContext, data: PreparedData): LinearRegressionModel = {
      // MLLib LinearRegressionWithSGD cannot handle empty training data.
      require(data.labeledPoints.take(1).nonEmpty,
        s"RDD[labeledPoints] in PreparedData cannot be empty." +
          " Please check if DataSource generates TrainingData" +
          " and Preparator generates PreparedData correctly.")

      LinearRegressionWithSGD.train(data.labeledPoints, ap.iterations, ap.stepSize)
    }

    def predict(model: LinearRegressionModel, query: Query): PredictedResult = {
      val prediction = model.predict(Vectors.dense(
        Array(query.attr0,
              query.attr1,
              query.attr2,
              query.attr3,
              query.attr4,
              query.attr5,
              query.attr6
        )
      ))
      PredictedResult(prediction)
    }
}
