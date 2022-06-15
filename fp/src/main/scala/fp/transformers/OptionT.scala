package fp.transformers

import fp.monad.Monad
import Monad.given

case class OptionT[F[_], T](value: F[Option[T]])

given [F[_]](using M: Monad[F]): Monad[[T] =>> OptionT[F, T]] with
  def pure[A](a: A): OptionT[F, A] = OptionT(M.pure(Some(a)))
  extension [A](fa: OptionT[F, A])
    def flatMap[B](f: A => OptionT[F, B]): OptionT[F, B] =
      OptionT {
        fa.value.flatMap { opt =>
          // need to return a F[Option[B]]
          opt.map(f).map(_.value).getOrElse(M.pure(None))
        }
      }

@main def optionTMain: Unit =

  val listOpts = List(Option(1), None, Option(3))
  val listOptsT = OptionT(listOpts)
  val res = listOptsT.map(_ + 1)
  println(res.value)

  // when flatmapping we have to wrap it inside OptionT-s...
  val res2 = for {
    x <- listOptsT
    y <- OptionT(List.fill(3)(Some(x)))
  } yield x + y
  println(res2.value)
