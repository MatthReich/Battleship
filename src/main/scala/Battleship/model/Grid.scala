package Battleship.model

//Standart  0
//Schiff    1
//Treffer   2
//Wasser    3

case class Grid(size: Int){
  private val matrix = Array.ofDim[Int](size,size)

  def getField(x: Int, y: Int): Int = {
    matrix(x)(y)
  }

  def setField(x: Int, y: Int, value: Int): Unit = {
    matrix(x)(y) = value
  }

  def setShip(x: Int, y: Int, x2: Int, y2: Int): Unit = {
    if ((x2 - x) > (y2 - y)) {
      var tmp = x2
      while (x <= tmp) {
        matrix(tmp)(y) = 1
        tmp -= 1
      }
    } else {
      var tmp = y2
      while (y <= tmp) {
        matrix(x)(tmp) = 1
        tmp -= 1
      }
    }
  }

  def getSize: Int = {
    size
  }

}
