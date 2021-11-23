package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person {
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("john")

  println(mary == john)

  val bobbie = Person(mary, john)

  // Scala Application =  Scala object with
  // def main(args: Array[String]): Unit
}
