package Battleship.model

//Standard  0
//Ship      1
//Hit       2
//Watter    3

case class Grid(size: Int) {
  private val matrix = Array.ofDim[Int](size, size)
  var ships: Array[Ship] = new Array[Ship](10)
  var shipSize = 0

  def getField(x: Int, y: Int): Int = {
    matrix(x)(y)
  }

  def setField(x: Int, y: Int, value: Int): Unit = {
    matrix(x)(y) = value
  }

  def setShip(array: Array[Int]): Unit = {
    var test: Boolean = true
    var idx = 0
    while (idx != shipSize) {
      if (ships(idx).colidate(array)) {
        test = false
      }
      idx += 1
    }
    if (test && ((array(0) == array(2)) ^ (array(1) == array(3)))) {
      ships(shipSize) = Ship(array)
      ships(shipSize).setCoordinates()
      shipSize += 1
      print("New Ship\n")
    } else {
      print("No new Ship\n")
    }
  }

}
