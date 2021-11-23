package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, acc: BigInt): BigInt = {
      if (x <= 1) acc
      else factHelper(x - 1, x * acc) // TAIL RECURSION = use recursive call as the LAST Expression
    }

    factHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = factHelper(6, 7 * 8 * 9 * 10 * 1)
    = ...
   */

  println(anotherFactorial(10))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive
   */

  @tailrec
  def concatenateString(aString: String, times: Int, acc: String = ""): String = {
    if (times == 0) acc
    else concatenateString(aString, times - 1, acc + aString)
  }

  println(concatenateString("hello", 3))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, acc: Boolean = true): Boolean = {
      if (t <= 1) acc
      else isPrimeUntil(t-1, n % t != 0 && acc)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))


  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibonacciHelper(i + 1, last + nextToLast, last)
    }

    if (n < 2) 1
    else fibonacciHelper(2, 1, 1)
  }



  println(fibonacci(8))
}
