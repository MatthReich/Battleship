package Battleship.model

import scala.collection.mutable

case class fieldTest () {

  var field: mutable.TreeMap[String, Boolean] = new mutable.TreeMap[String, Boolean]()

  def insertMap (string: String, boolean: Boolean): Unit = {
    field.put(string, boolean)
  }

  def getKey (string: String): Unit = {
    if (field.contains(string)) {
      printf("nothing in here\n")
    } else {
      printf("connected\n")
    }
  }

  def print (): Unit = {
    for ((k,v) <- field) printf("key: %s, value: %s\n", k, v)
  }

}