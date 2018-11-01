package main

import com.google.gson.{Gson, JsonArray}
import main.Album._
import org.scalatest.FunSuite

class AlbumSuite extends FunSuite {

  test("Test FetchAlbum function with illegal argument(out of the range)") {
    intercept[IllegalArgumentException] {
      FetchAlbum(123)
    }
  }

  test("Test FetchAlbum function with legal argument") {
    assert(FetchAlbum(1).get(0).getAsJsonObject.get("id").getAsInt === 1)
  }

  test("Test ParseJson function with legal argument") {
    val json1 = """
    [
    {
      "albumId": 1,
      "id": 1,
      "title": "accusamus beatae ad facilis cum similique qui sunt",
      "url": "https://via.placeholder.com/600/92c952",
      "thumbnailUrl": "https://via.placeholder.com/150/92c952"
    },
    {
      "albumId": 1,
      "id": 2,
      "title": "reprehenderit est deserunt velit ipsam",
      "url": "https://via.placeholder.com/600/771796",
      "thumbnailUrl": "https://via.placeholder.com/150/771796"
    }
    ]
    """
    val json2 = """
    [
    {
      "id": 1,
      "title": "accusamus beatae ad facilis cum similique qui sunt"
    },
    {
      "id": 2,
      "title": "reprehenderit est deserunt velit ipsam"
    }
    ]
    """
    val testJson1 = new Gson().fromJson(json1, classOf[JsonArray])
    val testJson2 = new Gson().fromJson(json2, classOf[JsonArray])
    val testParsed = ParseJson(testJson1)
    assert(testParsed === testJson2)

  }
}
