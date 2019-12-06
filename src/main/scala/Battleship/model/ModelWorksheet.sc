import scala.util.{Failure, Success, Try}

val maybeDoubles = Array("5", "1.0", "8.5", "10.0", "item1", "item2")

val convertDoubles = maybeDoubles.map { x =>
  Try(x.toInt)
}

val convertedArray = convertDoubles.map {
  case Success(res) => res
  case Failure(f) => None
}

convertedArray



for (
  value <- convertedArray) yield value match {
  case Some(value) => print(value)
  case None => println("Found None")
}
