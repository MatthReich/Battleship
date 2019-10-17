package Battleship

import java.lang.reflect.Array


object Battleship {
  def main(args: Array[String]): Unit = {

    val creator_01 = model.Player("Marcel")
    val creator_02 = model.Player("Matthias")

    print(
      "#"*20 + "\n" +
      "Battleship \n\n" +
      "#"*20 + "\n" +
      "played by: \n" + "" +
      "\t: " + creator_01 + "\n" +
      "\t: " + creator_02 + "\n" +
      "#"*20 + "\n"
    )
  }

}
