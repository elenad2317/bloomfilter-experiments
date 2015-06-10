/**
 * Created by epaz on 6/10/15.
 */

import scala.collection.mutable.HashSet
object hashset {

  def main(args: Array[String]): Unit ={
    val now = System.nanoTime()

    // import the words from macbeth into an array of strings called 'words'
    val source = scala.io.Source.fromFile("macbeth.txt")
    val lines = try source.mkString finally source.close()
    val words = lines.split(" ")

    // let's get othello words
    val source2 = scala.io.Source.fromFile("othello.txt")
    val lines2 = try source2.mkString finally source2.close()
    val othello_words = lines2.split(" ")

    // create hashset with words from macbeth
    var hashset : HashSet[String] = new HashSet()
    for (i <- 0 until words.length){
      hashset = hashset + words(i)
    }

    //var o_hashset : HashSet[String] = new HashSet()
    //for (j <- 0 until othello_words.length){
    //  o_hashset = o_hashset + words(j)
    //}

    //val intersection = hashset.&(o_hashset)
    //println(intersection.size)

    var commonWords = 0

    for (j <- 0 until othello_words.length){
      if (hashset.contains(othello_words(j))){
        commonWords += 1
      }
    }

    // moment of truth
    println("Words in common: "+ commonWords)
    val elapsed_nanos = System.nanoTime() - now
    val elapsed_ms = elapsed_nanos/1000000
    println(elapsed_ms)
  }
}
