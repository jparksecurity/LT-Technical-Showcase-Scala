package main

import com.google.gson.JsonArray
import main.Album._

import scala.io.StdIn.readInt

object Main extends App{

  println("Enter which album you want to see(1~100)? \n e.g. 50")
  val input = readInt()

  val json = FetchAlbum(input)
  val parsed = ParseJson(json)
}
