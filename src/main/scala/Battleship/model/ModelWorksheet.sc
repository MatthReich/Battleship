import Battleship.model.Ship

val coordinats = Array(0, 0, 0, 0)
val shipCoordinates = Array(1, 2, 1, 6, 4, 5, 5, 6)

for {
  i <- 0 until 2
  j <- 0 until 2
} coordinats(i)(j) = shipCoordinates(0)

