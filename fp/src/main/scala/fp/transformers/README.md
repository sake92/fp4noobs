
# Monad Transformers

You can do `Functor[List] compose Functor[Option]` and get a `Functor[List[Option]]`.  
When you do `map` over that new functor, you'd use the innermost value inside the `Option`.

You cannot do `Monad[List] compose Monad[Option]` to get a `Monad[List[Option]]`!
See https://www.scalawithcats.com/dist/scala-with-cats.html#exercise-composing-monads









