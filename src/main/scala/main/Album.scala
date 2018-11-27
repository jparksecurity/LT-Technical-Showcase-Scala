package main

import com.google.gson.{Gson, JsonArray, JsonElement, JsonObject}
import scalaj.http._

// import scala.util.Try

object Album {

  def FetchAlbum(id: Int): JsonArray = {

    require(id >= 1 && id <= 100, "Album ID should be in the range 1 through 100")

    val url = "https://jsonplaceholder.typicode.com/photos?albumId=%d".format(id)
    val response: HttpResponse[String] = Http(url).asString

    if (response.code != 200 || response.body.substring(1,2) == "]") throw new IllegalArgumentException()

    new Gson().fromJson(response.body, classOf[JsonArray])
  }

  def ParseJson(data: JsonArray): JsonArray = {

//    case class AlbumClass(id: Int, title: String)

    require(data.size() != 0, "Return Value is NULL.")

    val it = data.iterator()
    val parsedData = new JsonArray()

//    val objects = (0 until data.size()).map(i => data.getAsJsonObject)
//    val albums = objects.map(s => Try(AlbumClass(s.get("id").getAsInt, s.get("title").getAsString)))

    def ParseElement(ele: JsonElement):JsonObject ={
      val parsed = new JsonObject()
      parsed.add("id", ele.getAsJsonObject.get("id"))
      parsed.add("title", ele.getAsJsonObject.get("title"))
      parsed
    }

    it.forEachRemaining( x => {
      val parsed = ParseElement(x)
      println("[%d] %s".format(parsed.get("id").getAsInt, parsed.get("title").getAsString))
      parsedData.add(parsed)
    }
    )
    parsedData
  }

}
