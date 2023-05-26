package com.knoldus
package Test.db

import db.{Connection, DbOperations, EmployeeTable}
import model.Employee
import org.scalatest.funsuite.AnyFunSuite
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class DbOperationsTest extends AnyFunSuite {

  val dbOperations = new DbOperations
  val employeeTable = TableQuery[EmployeeTable]

  val connection = new Connection
  val employee: Employee = Employee(101,"sheshnath","yadav",23,10000,"Gorakhpur")

  test("testInsertInToDb") {
    val actual = dbOperations.insertInToDb(employee)
    val affectedRows = Await.result(actual,5.seconds)
    assert(affectedRows>0)
  }

  test("testGetDetailsOfEmployeeBySomeSearchString") {
  val actual  = Vector(Employee(101,"sheshnath","yadav",23,10000,"Gorakhpur"))
  val expected = dbOperations.getDetailsOfEmployeeBySomeSearchString("a")
    val result = Await.result( expected,5.seconds)
    assert(actual===result)
  }

  test("testGetDetailsOfAll") {
    val actual = Vector(Employee(101, "sheshnath", "yadav", 23, 10000, "Gorakhpur"))
    val expected = dbOperations.getDetailsOfAll
    val result = Await.result(expected, 5.seconds)
    assert(result === actual)
  }

}
