package Battleship.model

case class Ship(shipCoordinates: Array[Int]) {
  var size = 0
  var coordinats = Array.ofDim[Int](5, 2)
  var idx = 0
  shipCoordinates.length match {
    case 4 => {
      coordinats = Array.ofDim[Int](2, 2)
      insertCoordinates()
      size = 2
    }
    case 6 => {
      coordinats = Array.ofDim[Int](3, 2)
      insertCoordinates()
      size = 3
    }
    case 8 => {
      coordinats = Array.ofDim[Int](4, 2)
      insertCoordinates()
      size = 4
    }
    case 10 => {
      coordinats = Array.ofDim[Int](5, 2)
      insertCoordinates()
      size = 5
    }
  }

  def getCoordinates(): Array[Array[Int]] = {
    coordinats
  }

  def getSize(): Integer = {
    size
  }

  def insertCoordinates(): Unit = {
    var idx = 0
    for (outer <- coordinats.indices) {
      for (inner <- coordinats.indices) {
        coordinats(outer)(inner) = shipCoordinates(idx)
        idx += 1
      }
    }
  }
}
