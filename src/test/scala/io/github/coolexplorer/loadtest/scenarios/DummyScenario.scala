package io.github.coolexplorer.loadtest.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.github.coolexplorer.loadtest.apis.DummyAPI
import io.github.coolexplorer.loadtest.models.LoadProfile
import scala.concurrent.duration._

object DummyScenario {
  val sessionVarsFeeder = Iterator.continually(
    Map(
      "username" -> "dummy"
    )
  )

  def createScenario(profile: LoadProfile) : ScenarioBuilder = {
    scenario("Dummy Scenario")
      .feed(sessionVarsFeeder)
      .during(profile.duration) {
        exec(DummyAPI.dummyRequest).pause(5 seconds)
      }
  }
}
