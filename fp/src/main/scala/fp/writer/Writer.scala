package fp.writer

import fp.monad.Monad
import fp.monoid.Monoid

case class Writer[W: Monoid, T](value: T, log: W)

// just for readability
type Wr[W] = [T] =>> Writer[W, T]

given [W: Monoid]: Monad[Wr[W]] with {

  def pure[A](a: A): Writer[W, A] = Writer(a, Monoid[W].empty)

  extension [A](wa: Writer[W, A])
    def flatMap[B](f: A => Writer[W, B]): Writer[W, B] = {
      val wb = f(wa.value)
      Writer(wb.value, wa.log <> wb.log)
    }
}

/* usage */

@main def writerMain: Unit = {

  val res = for {
    v1 <- Writer(5, "Initialized. ")
    v2 <- Writer(v1 * 2, "Multipied by 2.")
  } yield v2

  println(res.value)
  println(res.log)
}
