package fp.writer

import fp.monad.Monad
import fp.monoid.Monoid

case class Writer[L: Monoid, T](log: L, value: T) {
  def tell(logValue: L): Writer[L, T] =
    Writer(log <> logValue, value)
}

// just for readability
type Wr[L] = [T] =>> Writer[L, T]

given [L: Monoid]: Monad[Wr[L]] with {

  def pure[A](a: A): Writer[L, A] =
    Writer(Monoid[L].empty, a)

  extension [A](wa: Writer[L, A])
    def flatMap[B](f: A => Writer[L, B]): Writer[L, B] = {
      val wb = f(wa.value)
      Writer(wa.log <> wb.log, wb.value)
    }
}

/* usage */

@main def writerMain: Unit = {

  val res = for {
    v1 <- Writer("Initialized. ", 5).tell("Loggggg... ")
    v2 <- Writer("Multipied by 2.", v1 * 2)
  } yield v2

  println(res.value)
  println(res.log)
}
