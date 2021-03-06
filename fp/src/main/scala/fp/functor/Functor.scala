package fp.functor

trait Functor[F[_]] {
  extension [A](fa: F[A]) def fmap[B](f: A => B): F[B] // <$>
}

object Functor {

  def apply[F[_]](using F: Functor[F]) = F

  given Functor[List] with
    extension [A](fa: List[A]) def fmap[B](f: A => B): List[B] = fa.map(f)

  given Functor[Option] with
    extension [A](fa: Option[A]) def fmap[B](f: A => B): Option[B] = fa.map(f)

}

/* usage */

@main def functorMain: Unit =
  import Functor.given

  println(List(1, 2, 3).fmap(_ + 1))
  println(genericPlus1(List(1, 2, 3)))

  println(genericPlus1(Option(3)))
  println(genericPlus1(Option.empty))

// independent of which Functor is used
def genericPlus1[F[_]: Functor](functor: F[Int]): F[Int] =
  functor.fmap(_ + 1)
