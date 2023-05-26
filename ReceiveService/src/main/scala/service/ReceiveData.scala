package com.knoldus
package service

import model.Employee
import db.DbOperations
import sqsconnection.SqsOperations
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.model.DeleteMessageRequest
import com.typesafe.scalalogging.Logger
import scala.jdk.CollectionConverters.ListHasAsScala
import scala.util.Try


class ReceiveData(queueUrl: String, region: Regions) {

  private val dbOperations = new DbOperations
  private val sqsConnection = new SqsOperations(queueUrl, region,None)
  private val logger: Logger =  Logger(getClass)

  def receiveMessageRequest(): List[String] = {

    val receivedMessages = sqsConnection.amazonSQSClientBuilder.receiveMessage(sqsConnection.receiveRequest).getMessages.asScala.toList

    // Process each received message
    receivedMessages.foreach {
      message =>
        val body = message.getBody
        val result = body.split(",").toList

        //data can be converted into model
        logger.info("Received Messages " + result)

        val employee = getEmployeeDetails(result)

        //inserting each received data into db
        Try(dbOperations.insertInToDb(employee))

        // Delete the consumed message from the queue
        val deleteRequest = new DeleteMessageRequest()
          .withQueueUrl(queueUrl)
          .withReceiptHandle(message.getReceiptHandle)
        sqsConnection.amazonSQSClientBuilder.deleteMessage(deleteRequest)
    }
    Thread.sleep(1000)
    receiveMessageRequest()
  }

  private def getEmployeeDetails(value: List[String]): Employee = {
    value match {
      case empId :: firstName :: lastName :: age :: salary :: address :: Nil => Employee(empId.toInt, firstName, lastName, age.toInt, salary.toInt, address)
      case _ => throw new IllegalArgumentException("Format is not right")
    }
  }

  // Close the SQS client
  def closeSqsClient(): Unit = {
    sqsConnection.amazonSQSClientBuilder.shutdown()
  }
}



