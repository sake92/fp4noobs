package fp
package monad

trait Monad[F[_]] {

  def pure[A](a: A): F[A]

  extension [A](fa: F[A]) {

    def map[B](f: A => B): F[B] =
      flatMap(a => pure(f(a)))

    def flatMap[B](f: A => F[B]): F[B] // >>=
  }
}

object Monad {

  def apply[F[_]](using F: Monad[F]) = F

  given Monad[List] with
    def pure[A](a: A): List[A] = List(a)
    extension [A](fa: List[A])
      def flatMap[B](f: A => List[B]): List[B] = fa.flatMap(f)

  given Monad[Opt] with
    import Opt.*
    def pure[A](a: A): Opt[A] = Filled(a)
    extension [A](fa: Opt[A])
      def flatMap[B](f: A => Opt[B]): Opt[B] = fa match
        case Empty => Empty
        case Filled(value1) =>
          f(value1) match
            case Empty          => Empty
            case Filled(value2) => Filled(value2)
}

/* usage */

@main def monadMain: Unit =
  import Opt.*
  import Monad.given

  println(
    Filled(5).flatMap { v1 =>
      Filled(2).map { v2 =>
        v1 * v2
      }
    }
  )

  println(
    for
      v1 <- Filled(5)
      v2 <- Filled(2)
    yield v1 * v2
  )

  println(
    multiplyMonads(Filled(5), Filled(2))
  )

def multiplyMonads[M[_]](m1: M[Int], m2: M[Int])(using m: Monad[M]): M[Int] =
  for
    v1 <- m1
    v2 <- m2
  yield v1 * v2
