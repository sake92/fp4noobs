package fp.functor

trait Functor[F[_]] {
  extension [A, B](x: F[A]) def map(f: A => B): F[B]
}

object Functor {
  given Functor[List] with
    extension [A, B](x: List[A]) def map(f: A => B): List[B] = x.map(f)
  
  given Functor[Option] with
    extension [A, B](x: Option[A]) def map(f: A => B): Option[B] = x.map(f)

}

/* usage */

@main def functorMain: Unit =
  import Functor.given

  println(List(1, 2, 3).map(_ + 1))
  println(genericPlus1(List(1, 2, 3)))

  println(genericPlus1(Option(3)))
  println(genericPlus1(Option.empty))

// independent of which Functor is used
def genericPlus1[F[_] : Functor](functor: F[Int]): F[Int] =
  functor.map(_ + 1)

