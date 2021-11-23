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
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
  override def toString: String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, Empty)

  override def printElements: String = ""

  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  /*
    [1, 2, 3].map(n * 2)
      = new Cons(2, [2, 3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
   */
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  /*
    [1, 2].flatMap(n => [n, n+1])
      = [1, 2] ++ [2].flatMap(n => [n, n+1])
      = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n+1]))
      = [1, 2] ++ [2, 3] ++ Empty
      = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }


  /*
    [1, 2, 3].filter(n % 2 == 0)
      = [2, 3].filter(n % 2 == 0)
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
   */
  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
    [1, 2] ++ [3, 4, 5]
      = new Cons(1, [2] ++ [3, 4, 5])
      = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

object ListTest extends App {
  val list = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))
  val anotherList = new Cons[Int](4, new Cons[Int](5, Empty))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)

  println(list.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(list.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((list ++ anotherList).toString)
  println((list.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons[Int](elem + 1, Empty))
  }).toString))
}