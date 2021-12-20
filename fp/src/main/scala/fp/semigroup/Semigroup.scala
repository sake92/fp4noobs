package fp.semigroup

trait Semigroup[T] {
  extension (x: T) def <>(y: T): T
}

object Semigroup {
  given Semigroup[Int] with
    extension (x: Int) def <>(y: Int): Int = x + y

  given Semigroup[String] with
    extension (x: String) def <>(y: String): String = x + y

  given [T]: Semigroup[List[T]] with
    extension (x: List[T]) def <>(y: List[T]): List[T] = x ++ y
}

/* usage */

@main def semigroupMain: Unit =
  import Semigroup.given
  println(1 <> 2)
  println("abc" <> "def" <> "xyz")
  println(List(1, 2, 3) <> List(4, 5, 6))
//println(true <> false)
