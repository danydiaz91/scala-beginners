package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  // this ^^ will crash with a NPE

  // throwing exceptions

  //val aWeirdValue: String = throw new NullPointerException


  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with StackOverflowError
   */

  // OOM
  // val array = Array.ofDim(Int.MaxValue)

  // SO
  // def infinite: Int = 1 + infinite
  //  val noLimit = infinite
}
