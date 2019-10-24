import scala.collection.mutable

class fieldTest {

  var field: mutable.Map[String, Boolean] = new mutable.TreeMap[String, Boolean]()

  def insertMap (): Unit = {
    this.field.put("A0", false)
  }

  def print (): Unit = {
    println(field.toString)
  }

}

case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y
field1.cells

print(field1.cells(0).x.equals(field1.cells(0).y))
print(true)

val creator_01 = "Marcel"
val creator_02 = "Matthias"

print(
  "#" * 20 + "\n" +
    "Battleship \n\n" +
    "#" * 20 + "\n" +
    "ceated by: \n" + "" +
    "\t: " + creator_01 + "\n" +
    "\t: " + creator_02 + "\n" +
    "#" * 20 + "\n"
)

Console.out.print( "Test " + Console.RED + " RED " + Console.RESET )