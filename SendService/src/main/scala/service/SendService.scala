package com.knoldus
package service

import sqs.SqsOperations

import com.amazonaws.services.sqs.model.SendMessageRequest
import com.typesafe.scalalogging.Logger

import scala.util.Try

class SendService(queueUrl: String, empData: List[String] ,endPoint :Option[String]){

  private val logger: Logger = Logger(getClass)
  val sqsOperations = new SqsOperations(endPoint)
  Try {
    empData.foreach {
      employeeData =>
        val sendMessageRequest = new SendMessageRequest(queueUrl, employeeData.mkString)
        sqsOperations.sqsClient.sendMessage(sendMessageRequest)
        logger.info(employeeData.mkString)
    }
  }
}

