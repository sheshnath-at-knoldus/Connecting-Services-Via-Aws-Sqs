package com.knoldus
package elasticmq

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

/*
to test this first start the elastic Mq server
 */


class ElasticMqForSqsTest extends AnyFunSuite {

  val mockElasticMqForSqs: ElasticMqForSqs = mock[ElasticMqForSqs]
  test("test the send data ") {
    val actual = mockElasticMqForSqs.receive
    val expected =mockElasticMqForSqs.empData
    assert(actual ===expected)
  }

}
