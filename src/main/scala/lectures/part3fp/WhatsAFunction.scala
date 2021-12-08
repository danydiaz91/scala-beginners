package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // PROBLEM: OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
      1. Define a function which takes 2 strings and concatenates them
      2. transform the MyPredicate and MyTransformer into function types
      3. define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */

  val superAdder = new SuperAdder
  val adder3 = superAdder(3)

  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}

// 1.
class Concatenator extends ((String, String) => String) {
  override def apply(v1: String, v2: String): String = v1.concat(v2)
}

// 3.
class SuperAdder extends (Int => Int => Int) {
  override def apply(x: Int): Int => Int = y => x + y
}