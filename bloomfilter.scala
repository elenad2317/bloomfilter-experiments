/**
 * Created by epaz on 6/9/15.
 */

import com.twitter.algebird._

object bloomfilter {
  def main(args: Array[String]): Unit ={
    val now = System.nanoTime()

    // create the Bloomfilter
    val NUM_HASHES = 70
    val WIDTH = 5500*100
    val SEED = 1
    val bfMonoid = new BloomFilterMonoid(NUM_HASHES, WIDTH, SEED)

    // import the words from macbeth into an array of strings called 'words'
    val source = scala.io.Source.fromFile("macbeth.txt")
    val lines = try source.mkString finally source.close()
    val words = lines.split(" ")

    // put all the words into the bloomfilter
    var bf = bfMonoid.create("the")
    for (i <- 0 until words.length){
      var bf1 = bfMonoid.create(words(i))
      bf = bf ++ bf1
    }

    // let's get othello words
    val source2 = scala.io.Source.fromFile("othello.txt")
    val lines2 = try source2.mkString finally source2.close()
    val othello_words = lines2.split(" ")

    // according to python there is an overlap of 1158 words
    // lets see how many the bloom filter gives us

    var commonWords = 0
    for (j <- 0 until othello_words.length){
      val approxBool = bf.contains(othello_words(j))
      val realBool = approxBool.isTrue
      if (realBool) {commonWords += 1 }
    }


    // moment of truth
    println("Words in common: "+ commonWords)

    //var actual_common = 0
    //for (j <- 0 until othello_words.length){
    //  if (words contains othello_words(j)){
    //    actual_common += 1
    //  }
    //}

    //println("ACTUAL WORDS IN COMMON " + actual_common)
    //print elapsed time
    val elapsed_nanos = System.nanoTime() - now
    val elapsed_ms = elapsed_nanos/1000000
    println(elapsed_ms)
  }
}
