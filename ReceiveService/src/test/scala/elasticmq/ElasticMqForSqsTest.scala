package com.knoldus
package Test.elasticmq

import elasticmq.ElasticMqForSqs

import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.mockito.MockitoSugar.mock


/*
to test this first start the elasticMq server
 */

class ElasticMqForSqsTest extends AnyFunSuiteLike {

  val mockElasticMqForSqs: ElasticMqForSqs = mock[ElasticMqForSqs]

  test("Receive Message from the Queue "){
    val actual =mockElasticMqForSqs.sendMessageRequest("10,SHESHNATH,YADAV,23,10000,GORAKHPUR")
    val expected =mockElasticMqForSqs.resultOfReceive
    assert(actual===expected)
  }
}
