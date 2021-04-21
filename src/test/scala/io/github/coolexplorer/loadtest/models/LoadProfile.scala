package io.github.coolexplorer.loadtest.models

import io.github.coolexplorer.loadtest.constants.LoadTestDuration

import scala.concurrent.duration.FiniteDuration

case class LoadProfile(
                      users: Int = 1,
                      duration: FiniteDuration = LoadTestDuration.DEFAULT_DURATION,
                      rampUpDuration: FiniteDuration = LoadTestDuration.DEFAULT_DURATION
                      ) {

}
