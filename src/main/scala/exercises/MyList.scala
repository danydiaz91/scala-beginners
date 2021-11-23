package exercises

abstract class MyList[+A] {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new List with this element added
    toString = a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](elem: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, Empty)

  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
}

object ListTest extends App {
  val list = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)
}