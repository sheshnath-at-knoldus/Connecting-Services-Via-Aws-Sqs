package com.knoldus
package app

import employeedataparser.EmployeeData
import service.SendService

object Driver extends App {
  private val employeeData = new EmployeeData
  val empData = employeeData.parseCsv("src/main/resources/EmployeeDetails.csv")
  private val queueUrl = "https://sqs.us-east-1.amazonaws.com/348478554515/Test" // Replace with your SQS queue URL
  new SendService(queueUrl, empData,None)
}
