package io.github.coolexplorer.loadtest.configurations

object Configuration {

  def getTargetServerUrls(): List[String] = {
    List("https://www.google.com")
  }

  def getSuccessfulRequestRate(): Double = {
    0.0
  }
}
