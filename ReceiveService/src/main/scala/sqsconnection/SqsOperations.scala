package com.knoldus
package sqsconnection

import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials, DefaultAWSCredentialsProviderChain}
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.model.ReceiveMessageRequest
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}


class SqsOperations(queueUrl: String, region: Regions, endPoint: Option[String]) {

  val amazonSQSClientBuilder: AmazonSQS = if (endPoint.isDefined) {
    val awsTestCredentials = new BasicAWSCredentials("test", "test")
    AmazonSQSClientBuilder
      .standard()
      .withEndpointConfiguration(new EndpointConfiguration(endPoint.get, region.toString))
      .withCredentials(new AWSStaticCredentialsProvider(awsTestCredentials))
      .build
  } else {
    AmazonSQSClientBuilder
      .standard()
      .withRegion(region)
      .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
      .build()
  }

  val receiveRequest: ReceiveMessageRequest = new ReceiveMessageRequest()
    .withQueueUrl(queueUrl)
    .withMaxNumberOfMessages(10)

}

