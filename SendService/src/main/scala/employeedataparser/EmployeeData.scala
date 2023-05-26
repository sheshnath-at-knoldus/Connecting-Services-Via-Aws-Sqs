package com.knoldus
package employeedataparser

import scala.io.Source
import scala.util.{Failure, Success, Try}

class EmployeeData {
  def parseCsv(path: String): List[String] = {
    val file = Try(Source.fromFile(path))
    file match {
      case Success(f) =>
        try {
          val lines = f.getLines.toList
          val header = lines.head.split(",").map(_.trim)
          val data = lines.tail.map(_.split(",").map(_.trim))
          val formattedData = data.map(row => header.zip(row).map { case (_, v) => s"$v" }.mkString(","))
          formattedData
        } finally {
          f.close()
        }
      case Failure(ex) => throw new Exception(ex)
    }
  }
}

