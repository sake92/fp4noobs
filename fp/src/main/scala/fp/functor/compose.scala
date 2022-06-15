package fp
package functor

given compose[F[_]: Functor, G[_]: Functor]: Functor[[A] =>> F[G[A]]] with {
  extension [A](fga: F[G[A]])
    def fmap[B](f: A => B): F[G[B]] =
      Functor[F].fmap(fga)(ga => ga.fmap(f))
}

@main def functorCompose: Unit =

  val res = List(
    Option(6),
    None,
    Option(342)
  ).fmap(_ + 1)

  println(res)
