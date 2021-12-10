package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // 1 - Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - Match Anything
  // 2.1 Wildcard

  val matchAnything = x match {
    case _ =>
  }

  // 2.2 Variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // PMs can be NESTED!

  // 4 - Case Classes - Constructor Pattern
  // PMs can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subHead, subTail)) =>
  }

  // 5 - List Patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // A list of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _
  niti}

  // 7 - Name Binding
  val nameBindingMatch = aList match {
    case notEmptyList @ Cons(_, _) => // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested pattern
  }

  // 8 - multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => // compound pattern (multi-pattern)
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch) // a list of strings
  // JVM trick question
  // At the end the generic type is not evaluated, what it means both cases are the same (List, List)
}
