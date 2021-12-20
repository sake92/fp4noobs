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

  // makes no sense to "combine Options" themselves,
  // so we combine the types it wraps
  given [T](using m: Monoid[T]): Monoid[Option[T]] with
    extension (x: Option[T])
      def <>(y: Option[T]): Option[T] = x match
        case None => y
        case Some(a) =>
          y match
            case None    => x
            case Some(b) => Some(a <> b)

    def empty: Option[T] = Option.empty
}

/* usage */
def combineAll[T: Monoid](xs: List[T]): T =
  xs.foldLeft(Monoid[T].empty)(_ <> _)

@main def monoidMain: Unit =
  import Monoid.given
  
  println(Monoid[Int].empty)
  println(Monoid[List[String]].empty)

  println(Option("aaa") <> None <> Option("ccc"))

  println(combineAll(List(1, 2, 3)))
  println(combineAll(List("abc", "def")))
