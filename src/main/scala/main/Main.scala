package main

import java.io.{BufferedWriter, File, FileWriter}
import java.nio.file.{Files, Paths}
import java.util.Calendar

import Album._

import scala.io.StdIn.readInt

object Main extends App{

  val path = Paths.get("./errorlog.txt")
  if(!Files.exists(path)) Files.createFile(path)
  val file = new File("./errorlog.txt")
  val bw = new BufferedWriter(new FileWriter(file, true))

  println("Enter which album you want to see(1~100)? ")
  val input = readInt()

  try {
    val json = FetchAlbum(input)
    val parsed = ParseJson(json)
  } catch {
    case e:Exception => {
      bw.write(Calendar.getInstance().getTime().toString + "\n")
      bw.write(e.toString + "\n")
      bw.write(s"User input was $input.\n")
    }
  }

  bw.close()
}
