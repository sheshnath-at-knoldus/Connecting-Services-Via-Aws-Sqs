package com.knoldus
package db

import model.Employee
import slick.jdbc.PostgresProfile.api._


class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
  private def id = column[Int]("id", O.PrimaryKey)

  def firstName = column[String]("firstname")

  def lastName = column[String]("lastname")

  def age = column[Int]("age")

  def salary = column[Int]("salary")

  def address = column[String]("address")

  override def * = (id, firstName, lastName, age, salary, address) <> (Employee.tupled, Employee.unapply)

}


