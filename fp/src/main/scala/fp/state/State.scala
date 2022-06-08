package fp.state

import fp.monad.Monad

case class State[S, T](run: S => (S, T))

type St[S] = [T] =>> State[S, T]

given [S]: Monad[St[S]] with {

  def pure[A](a: A): State[S, A] =
    State(s => (s, a))

  extension [A](st: State[S, A])
    def flatMap[B](f: A => State[S, B]): State[S, B] = State { prevState =>
      val (newState, a) = st.run(prevState)
      f(a).run(newState)
    }
}

/* usage */

type Stack = List[Int]

type StackState[T] = State[Stack, T]

def pop: StackState[Int] = State { st =>
  (st.tail, st.head)
}

def push(x: Int): StackState[Unit] = State { st =>
  (st.prepended(x), ())
}

@main def stateMain: Unit = {

  val statefulFunction = for {
    _ <- push(100)
    x1 <- pop
    x2 <- pop
  } yield x1 + x2

  val result = statefulFunction.run(
    List(1, 2, 3, 4, 5)
  )

  println(result)
}
