package io.github.coolexplorer.loadtest.configurations

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import scala.concurrent.duration._


object Configuration {
  private val logger = LoggerFactory.getLogger(this.getClass.getName)
  val urlPattern = raw"^([a-zA-Z]+)://(([a-zA-Z0-9-_]+.)*([a-zA-Z0-9][a-zA-Z0-9-_]+.)*([a-zA-Z0-9]{2,11}))(:[0-9]+)*".r
  val loadTestConfig = ConfigFactory.load("loadtest.conf")
  val targetServer: String = getTargetServer(loadTestConfig.getString("loadtest.targetServer"))
  val users: Int = loadTestConfig.getInt("loadtest.users")
  val durationUnit: String = loadTestConfig.getString("loadtest.duration.unit")

  val rampUpDuration: FiniteDuration = getDuration(
    loadTestConfig.getInt("loadtest.duration.rampUpTime"),
    durationUnit
  )

  val duration: FiniteDuration = getDuration(
    loadTestConfig.getInt("loadtest.duration.time"),
    durationUnit
  )

  def getTargetServerUrls(): List[String] = {
    List(targetServer)
  }

  def getSuccessfulRequestRate(): Double = {
    0.0
  }

  def checkUrlPattern(url:String): Boolean = url match {
    case urlPattern(_*) => true
    case _ => false
  }

  def getTargetServer(targetServer: String): String = {
    if (checkUrlPattern(targetServer)) {
      targetServer
    } else {
      logger.error("Target server pattern is not matched with URL type.")
      ""
    }
  }

  def getDuration(time: Int, unit:String): FiniteDuration = {
    unit match {
      case "second" | "seconds" => time seconds
      case "minute" | "minutes" => time minutes
      case "hour" | "hours" => time hours
    }
  }
}
