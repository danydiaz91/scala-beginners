package exercises

trait MyPredicate[-A] {
  def test(elem: A): Boolean
}
