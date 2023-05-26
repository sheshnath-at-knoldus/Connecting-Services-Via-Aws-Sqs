package com.knoldus
package sqs

import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials, DefaultAWSCredentialsProviderChain}
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.{AmazonSQS, AmazonSQSClientBuilder}

class SqsOperations(endPoint :Option[String]) {
  // Set up AWS SQS client

  val sqsClient: AmazonSQS = if (endPoint.isDefined) {
    val awsTestCreds = new BasicAWSCredentials("test", "test")
    AmazonSQSClientBuilder
      .standard()
      .withEndpointConfiguration(new EndpointConfiguration(endPoint.get, "us-east-1"))
      .withCredentials(new AWSStaticCredentialsProvider(awsTestCreds))
      .build
  } else {
    AmazonSQSClientBuilder
      .standard()
      .withRegion(Regions.EU_NORTH_1)
      .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
      .build()
  }
}
