package exercises

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}
