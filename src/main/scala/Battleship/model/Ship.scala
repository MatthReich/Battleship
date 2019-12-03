package Battleship.model

case class Ship(shipCoordinates: Array[Int]) {
  var size = 0
  var coordinates: Array[Array[Int]] = Array.ofDim[Int](5, 2)

  def setCoordinates(): Unit = {
    if (shipCoordinates(0) == shipCoordinates(2)) {
      size = shipCoordinates(3) - shipCoordinates(1) + 1
      coordinates = Array.ofDim[Int](size, 2)
      var idy = 0
      while (idy != size) {
        coordinates(idy)(0) = shipCoordinates(0)
        coordinates(idy)(1) = shipCoordinates(1) + idy
        idy += 1
      }
    } else {
      size = shipCoordinates(2) - shipCoordinates(0) + 1
      coordinates = Array.ofDim[Int](size, 2)
      var idy = 0
      while (idy != size) {
        coordinates(idy)(0) = shipCoordinates(0) + idy
        coordinates(idy)(1) = shipCoordinates(1)
        idy += 1
      }
    }
  }

  def colidate(array: Array[Int]): Boolean = {
    var output: Boolean = false
    var idx = 0
    while (idx != size) {
      if (array(0) == array(2)) {
        var idy = array(1)
        while (idy <= array(3)) {
          if (array(0) == coordinates(idx)(0) && idy == coordinates(idx)(1)) {
            output = true
          }
          idy += 1
        }
      } else if (array(1) == array(3)) {
        var idy = array(0)
        while (idy <= array(2)) {
          if (idy == coordinates(idx)(0) && array(1) == coordinates(idx)(1)) {
            output = true
          }
          idy += 1
        }
      } else {
        output = true
      }
      idx += 1
    }
    output
  }

}
