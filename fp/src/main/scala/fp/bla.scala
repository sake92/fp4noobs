package fp

import fp.functor.Functor.given
import fp.applicative.Applicative.given

case class MyData(x: Int, s: String)

@main def functorAndApplicative(): Unit = {

  val intOpt = Option(5)
  val strOpt = Option("str")

  val myDataOpt1: Option[MyData] = for {
    x <- intOpt
    s <- strOpt
  } yield MyData(x, s)

  val myDataOpt2: Option[MyData] =
    strOpt.ap(
      intOpt.fmap(MyData.apply.curried)
    )

  println(myDataOpt1)
  println(myDataOpt2)
}

/* haskell

data MyData = MyData Int String  deriving (Show)

intOpt = Just 5
strOpt = Just "str"

myDataOpt :: Maybe MyData
myDataOpt = MyData <$> intOpt <*> strOpt

*/