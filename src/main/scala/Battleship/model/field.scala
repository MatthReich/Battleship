package Battleship.model

import scala.collection.mutable

class fieldTest (player: Player) {

  var player_name:Player = player
  var field: mutable.TreeMap[String, mutable.ListMap[Int, Boolean]] = new mutable.TreeMap[String, mutable.ListMap[Int, Boolean]]()

  def insertMap (string: String, int: Int ,boolean: Boolean ): Unit = {
      field.put(string, mutable.ListMap(int -> boolean))
  }

  def getKey (string: String): Unit = {
    printf("%b\n", field.contains(string))
  }

  def print (): Unit = {
    printf("field for: %s\n", player_name)
    for ((k,v) <- field) printf("key: %s, value: %s\n", k, v)
  }

}