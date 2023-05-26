package com.knoldus
package elasticmq

import service.ReceiveData
import sqsconnection.SqsOperations
import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.model.{SendMessageRequest, SendMessageResult}
import com.typesafe.config.{Config, ConfigFactory}
import org.elasticmq.server.ElasticMQServer
import org.elasticmq.server.config.ElasticMQServerConfig
import scala.concurrent.ExecutionContextExecutor


class ElasticMqForSqs {

  implicit val actorSystem: ActorSystem = ActorSystem.create()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
  implicit val m_logger: LoggingAdapter = actorSystem.log

  private val queueName = "queue1"
  private val queueURL = s"http://localhost:9324/000000000000/$queueName"
  private val config: Config = ConfigFactory.load("elasticmq.conf")
  private val server = new ElasticMQServer(new ElasticMQServerConfig(config))
  m_logger.info("server started")
  server.start()

  // end point creation for the Queue
  private val endpoint = Some("http://localhost:9324")
  val region = "us-east-1"

  //instance  Sqs operations to test operations of SQS
  private val elasticMQForSQSTest = new SqsOperations(queueURL, Regions.fromName(region), endpoint)

  // send method call to test the receive service
  sendMessageRequest("101,SHESHNATH,YADAV,23,10000,GORAKHPUR")

  //receive the data
  val receiveData = new ReceiveData(queueURL, Regions.fromName(region))
  val resultOfReceive: Seq[String] = receiveData.receiveMessageRequest()


  def sendMessageRequest(message: String): SendMessageResult = {
    val sendMessageRequest = new SendMessageRequest(queueURL, message)
    elasticMQForSQSTest.amazonSQSClientBuilder.sendMessage(sendMessageRequest)
  }

}

// For testing Purpose
object Run extends App {
   new ElasticMqForSqs
}



