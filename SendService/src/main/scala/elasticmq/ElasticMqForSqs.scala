package com.knoldus
package elasticmq


import employeedataparser.EmployeeData
import service.SendService
import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import com.amazonaws.services.sqs.model.ReceiveMessageResult
import com.typesafe.config.{Config, ConfigFactory}
import org.elasticmq.server.ElasticMQServer
import org.elasticmq.server.config.ElasticMQServerConfig
import scala.concurrent.ExecutionContextExecutor


class ElasticMqForSqs {

  implicit val actorSystem: ActorSystem = ActorSystem.create()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
  implicit val m_logger: LoggingAdapter = actorSystem.log

  private val queueName = "queue1"
  private val queueUrl = s"http://localhost:9324/000000000000/$queueName"
  private val config: Config = ConfigFactory.load("elasticmq.conf")
  private val server = new ElasticMQServer(new ElasticMQServerConfig(config))
  m_logger.info("server started")
  server.start()

  // end point creation for the Queue
  private val endPoint = Some("http://localhost:9324")
  private val employeeData = new EmployeeData
  val empData: List[String] = employeeData.parseCsv("src/main/resources/EmployeeDetails.csv")

  private val sendService = new SendService(queueUrl, empData, endPoint)
  val receive: ReceiveMessageResult = sendService.sqsOperations.sqsClient.receiveMessage(queueUrl)
  m_logger.info( "receive message"+receive.getMessages)

}

//just for testing Purpose
object Run extends App {
  new ElasticMqForSqs
}



