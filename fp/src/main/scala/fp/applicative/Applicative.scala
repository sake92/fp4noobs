package fp.applicative

import fp.functor.Functor

trait Applicative[F[_]] {

  def pure[A](a: A): F[A]

  extension [A](fa: F[A]) {

    def ap[B](ff: F[A => B]): F[B] // <*>

    def map[B](f: A => B): F[B] = ap(pure(f)) // from Functor
  }
}

object Applicative {

  given Applicative[Option] with {
    def pure[A](a: A): Option[A] = Option(a)

    extension [A](fa: Option[A]) {
      def ap[B](ff: Option[A => B]): Option[B] =
        (ff, fa) match
          case (Some(f), Some(a)) => Some(f(a))
          case _                  => None
    }
  }

  given Applicative[List] with {
    def pure[A](a: A): List[A] = List(a)

    extension [A](fa: List[A]) {
      def ap[B](ff: List[A => B]): List[B] =
        ff.zip(fa).map { (f, a) => f(a) }
    }
  }
}

/* usage */

@main def applicativeMain: Unit =
  import Applicative.given

  println(Option(10).ap(Option(_ + 1)))

  println(List(10, 11, 12).ap(List(_ + 1, _ + 5, _ + 10)))
