package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch, crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract class
  // 1 - traits doesn't have constructor parameters
  // 2 - multiple traits mey be inherited by the same class
  // 3 - traits = behavior, abstract class is a thing
}
