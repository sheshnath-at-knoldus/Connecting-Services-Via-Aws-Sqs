package com.knoldus
package db

import model.Employee
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future


class DbOperations extends Connection {

  private val employeeTable = TableQuery[EmployeeTable]

  def insertInToDb(employee: Employee): Future[Int] = {
    val insert = employeeTable += employee
    db.run(insert)
  }

  def getDetailsOfAll: Future[Seq[EmployeeTable#TableElementType]] = {
    val action = employeeTable.result
    db.run(action)
  }

  def getDetailsOfEmployeeBySomeSearchString(searchString: String): Future[Seq[EmployeeTable#TableElementType]] = {
    val result = employeeTable.filter(
      employeeTable =>
        employeeTable.firstName.like(s"%$searchString%") || employeeTable.lastName.like(s"%$searchString%") || employeeTable.address.like(s"%$searchString%")
    ).result
    db.run(result)
  }
}
