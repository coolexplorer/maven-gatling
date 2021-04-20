package io.github.coolexplorer.loadtest

import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import io.github.coolexplorer.loadtest.configurations.Configuration
import io.github.coolexplorer.loadtest.constants.Assertion
import io.github.coolexplorer.loadtest.models.LoadProfile
import io.github.coolexplorer.loadtest.scenarios.LoadTestScenario

class LoadTestSimulation extends Simulation {

  val httpProtocol = LoadTestProtocol.createHttpProtocol()
  val successfulRequestRate = Configuration.getSuccessfulRequestRate()

  val loadProfile = LoadProfile()

  private def getSimulations(profile: LoadProfile): List[PopulationBuilder] = {
    List(
      LoadTestScenario.createScenario(profile)
        .inject(rampUsers(loadProfile.users).during(profile.duration)).protocols(httpProtocol)
    )
  }

  setUp(getSimulations(loadProfile))
    .maxDuration(loadProfile.duration)
    .assertions(
      global.responseTime.max.lt(Assertion.responseMaxTime),
      global.responseTime.mean.lt(Assertion.responseMinTime),
      global.successfulRequests.percent.gt(successfulRequestRate)
    )
}
