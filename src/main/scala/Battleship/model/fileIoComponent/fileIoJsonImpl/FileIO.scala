package Battleship.model.fileIoComponent.fileIoJsonImpl

import Battleship.model.fileIoComponent.FileIOInterface
import Battleship.model.gridComponent.InterfaceGrid
import play.api.libs.json.{JsNumber, JsValue, Json}

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: InterfaceGrid = {
    var grid: InterfaceGrid = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString.toInt
    // val injector = Guice.createInjector(new SudokuModule)
    /*size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }*/
    for (index <- 0 until size * size) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      val cell = (json \\ "cell") (index)
      val value = (cell \ "value").as[Int]
      // grid = grid.setField(row, col, value)
      val given = (cell \ "given").as[Boolean]
      val showCandidates = (cell \ "showCandidates").as[Boolean]
      // if (given) grid = grid.setGiven(row, col, value)
      // if (showCandidates) grid = grid.setShowCandidates(row, col)
    }
    grid
  }

  override def save(grid: InterfaceGrid): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close
  }

  def gridToJson(grid: InterfaceGrid) = {
    val gridSize = 10
    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(gridSize),
        "cells" -> Json.toJson(
          for {
            row <- 0 until gridSize
            col <- 0 until gridSize
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(grid.getValue(row, col))
            )
          }
        )
      )
    )
  }

}
