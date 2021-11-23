package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
}

// Constructor
class Person(name: String, val age: Int) {
  // body
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I'm ${this.name}")

  // multiple constructors
  def this(name: String) = this(name, 0)
}

// class parameters are NOT FIELDS

/*
    Novel and a Writer

    Writer: first name, surname, year
      - method fullname

    Novel: name, year of release, author
      - authorAge
      - isWrittenBy(author)
      - copy (new year of release) = new instance of Novel
   */

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = firstName + " " + surname
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, this.author)
}

class Counter(value: Int = 0) {

  def increment(value: Int = 1): Counter = new Counter(this.value + value)
  def decrement(value: Int = 1): Counter = new Counter(this.value - value)
}