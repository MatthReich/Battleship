package Battleship.model

case class Ship(shipCoordinates: Array[Int]) {
  var size = 0
  var coordinats = Array.ofDim[Int](5, 2)

  def setCoordinates(): Unit = {
    if (shipCoordinates(0) == shipCoordinates(2)) {
      size = shipCoordinates(3) - shipCoordinates(1)
      coordinats = Array.ofDim[Int](size, 2)
      var idy = 0
      while (idy != size) {
        coordinats(idy)(0) = shipCoordinates(0)
        coordinats(idy)(1) = shipCoordinates(1 + idy)
        idy += 1
      }
    } else {
      size = shipCoordinates(2) - shipCoordinates(0)
      coordinats = Array.ofDim[Int](size, 2)
      var idy = 0
      while (idy != size) {
        coordinats(idy)(0) = shipCoordinates(0 + idy)
        coordinats(idy)(1) = shipCoordinates(1)
        idy += 1
      }
    }
  }


}
