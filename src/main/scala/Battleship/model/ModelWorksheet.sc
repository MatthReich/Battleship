import scala.collection.mutable
import scala.collection.mutable.ListBuffer

val size = 3

val listA0 = ListBuffer.fill(size) (false)
val listA1 = ListBuffer.fill(size) (false)
val listA2 = ListBuffer.fill(size) (false)

val playingField = mutable.Map (
  "A0" -> listA0,
  "A1" -> listA1,
  "A2" -> listA2
)

for ((k, v) <- playingField)
  println(k + " : " + v)







Console.out.print( "Test " + Console.RED + " RED " + Console.RESET )