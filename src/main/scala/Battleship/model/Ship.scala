package Battleship.model

case class Ship(shipCoordinates: Array[Int]) {
  var size = 0
  var coordinats = Array.ofDim[Int](5, 2)
  var row = 0
  var cols = 2
  var idx = 0
  shipCoordinates.length match {
    case 4 => {
      row = 2
      coordinats = Array.ofDim[Int](row, cols)
      insertCoordinates()
      size = 2
    }
    case 6 => {
      row = 3
      coordinats = Array.ofDim[Int](row, cols)
      insertCoordinates()
      size = 3
    }
    case 8 => {
      row = 4
      coordinats = Array.ofDim[Int](row, cols)
      insertCoordinates()
      size = 4
    }
    case 10 => {
      row = 5
      coordinats = Array.ofDim[Int](row, cols)
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
    for (outer <- 0 until row) {
      for (inner <- 0 until cols) {
        coordinats(outer)(inner) = shipCoordinates(idx)
        idx += 1
      }
    }
  }
}
