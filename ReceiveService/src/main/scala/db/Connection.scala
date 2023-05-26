package com.knoldus
package db

import slick.jdbc.PostgresProfile.api._


class Connection{
  val db = Database.forConfig("mydb")
}
