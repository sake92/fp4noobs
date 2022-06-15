package fp.reader

import fp.monad.Monad
import scala.util.Random

// - function wrapper
// - T depends on "config"/"dependency" C
case class Reader[C, T](action: C => T) {
  def run(c: C): T = action(c)
}

object Reader {
  def ask[T]: Reader[T, T] = Reader(identity)
}

type Rd[C] = [T] =>> Reader[C, T]

given [C]: Monad[Rd[C]] with {

  def pure[A](a: A): Reader[C, A] =
    Reader(_ => a)

  extension [A](ra: Reader[C, A])
    def flatMap[B](f: A => Reader[C, B]): Reader[C, B] = Reader { c =>
      val a = ra.run(c)
      f(a).run(c)
    }
}

/* usage */

class Connection() {
  def withLogSQL(yes: Boolean): Unit = ()
  def select(): String = Random.shuffle('a' to 'z').mkString
  def update(str: String): Unit = println(s"Update '$str'")
}

type DbAction[T] = Reader[Connection, T]

def getStuff: DbAction[String] = Reader { c =>
  c.select()
}

def updateStuff(str: String): DbAction[Unit] = Reader { c =>
  c.update(str)
}

@main def readerMain: Unit = {

  // just to showcase "ask"
  val myUpdatedConn = for {
    conn <- Reader.ask[Connection]
  } yield conn.withLogSQL(true)

  val connectionUsingFunction = for {
    _ <- myUpdatedConn // using "ask"-ed connection
    str <- getStuff
    _ <- updateStuff(str + "_udpated")
  } yield ()

  connectionUsingFunction.run(
    new Connection()
  )
}
