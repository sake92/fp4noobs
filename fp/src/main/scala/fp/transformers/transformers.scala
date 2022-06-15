package fp
package transformers

import fp.monad.Monad
import Monad.given

// Hypothetical example. This won't actually compile !
// It is impossible to write a general definition of flatMap without knowing something about M1 or M2.
// However, if we do know something about one or other monad, we can typically complete this code.
def compose[M1[_]: Monad, M2[_]: Monad] = {
  type Composed[A] = M1[M2[A]]

  new Monad[Composed] {
    def pure[A](a: A): Composed[A] =
      Monad[M1].pure(Monad[M2].pure(a))

    extension [A](fa: Composed[A]) {
      def flatMap[B](f: A => Composed[B]): Composed[B] = {
        // cannot use map here, because it is implemented in terms of THIS FLATMAP
        // cannot use flatMap, because we are just defining it here... obviously
        ???
      }
    }
  }
}
