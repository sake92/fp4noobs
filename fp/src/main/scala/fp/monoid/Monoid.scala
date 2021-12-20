package fp.monoid

import fp.semigroup.Semigroup

trait Monoid[T] extends Semigroup[T] {
  def empty: T
}

object Monoid {

  def apply[T](using m: Monoid[T]): Monoid[T] = m

  given Monoid[Int] with
    extension (x: Int) def <>(y: Int): Int = x + y
    def empty: Int = 0

  given Monoid[String] with
    extension (x: String) def <>(y: String): String = x + y
    def empty: String = ""

  given [T]: Monoid[List[T]] with
    extension (x: List[T]) def <>(y: List[T]): List[T] = x ++ y
    def empty: List[T] = List.empty
}

/* usage */
def combineAll[T: Monoid](xs: List[T]): T =
  xs.foldLeft(Monoid[T].empty)(_ <> _)

@main def monoidMain: Unit =
  import Monoid.given
  println(Monoid[Int].empty)
  println(Monoid[List[String]].empty)

  println(combineAll(List(1, 2, 3)))
  println(combineAll(List("abc", "def")))
