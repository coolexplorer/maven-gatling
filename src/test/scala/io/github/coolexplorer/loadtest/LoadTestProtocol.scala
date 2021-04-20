package io.github.coolexplorer.loadtest

import io.gatling.core.Predef._
import io.gatling.http.HeaderNames
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.github.coolexplorer.loadtest.configurations.Configuration

object LoadTestProtocol {
  def createHttpProtocol(): HttpProtocolBuilder = {
    http
      .baseUrls(Configuration.getTargetServerUrls())
      .acceptHeader("*/*")
      .headers(Map(
        HeaderNames.ContentType -> "application/json"
      ))
      .disableWarmUp
      .disableCaching
  }
}
