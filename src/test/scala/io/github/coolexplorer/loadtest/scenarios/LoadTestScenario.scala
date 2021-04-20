package io.github.coolexplorer.loadtest.scenarios

import io.gatling.core.structure.ScenarioBuilder
import io.github.coolexplorer.loadtest.models.LoadProfile

object LoadTestScenario {
  def createScenario(profile: LoadProfile): ScenarioBuilder = {
    DummyScenario.createScenario(profile)
  }
}
