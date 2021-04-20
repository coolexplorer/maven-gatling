package io.github.coolexplorer.loadtest.apis

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DummyAPI {
  val dummyRequest = {
    exec(http("Dummy Request").get(""))
  }
}
