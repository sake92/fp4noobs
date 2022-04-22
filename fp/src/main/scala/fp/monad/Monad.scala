package fp.monad

trait Monad[F[_]] {
  def pure[A](a: A): F[A]
  extension [A](fa: F[A]) {
    def map[B](f: A => B): F[B] =
      flatMap(a => pure(f(a)))
    def flatMap[B](f: A => F[B]): F[B] // >>=
  }
}

object Monad {
  given Monad[Opt] with
    import Opt.*
    def pure[A](a: A): Opt[A] = Filled(a)
    extension [A](fa: Opt[A]) def flatMap[B](f: A => Opt[B]): Opt[B] = fa match
      case Empty => Empty
      case Filled(value1) => f(value1) match
        case Empty => Empty
        case Filled(value2) => Filled(value2)
}

// reinvent Option because it already has map/flatMap
enum Opt[+T]:
  case Empty extends Opt
  case Filled(value: T) extends Opt[T]

/* usage */

@main def monadMain: Unit =
  import Opt.*
  import Monad.given

  println(
    Filled(5).flatMap { v1 => 
      Filled(v1 * 2)
    }
  )

  println(
    for
      v1 <- Filled(5)
      v2 <- Filled(v1 * 2)
    yield v2
  )
