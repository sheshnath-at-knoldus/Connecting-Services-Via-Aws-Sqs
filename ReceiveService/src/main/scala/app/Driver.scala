package com.knoldus
package app

import service.ReceiveData

import com.amazonaws.regions.Regions

object Driver extends App{

  val queueUrl = "https://sqs.us-east-1.amazonaws.com/348478554515/Test"
  val region = Regions.EU_NORTH_1
  private val receiveData = new ReceiveData(queueUrl, region)
  receiveData.receiveMessageRequest()
  receiveData.closeSqsClient()
}
