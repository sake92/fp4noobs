
# Monad ~= "FlatMappable"

Functions:
- empty/pure/return/unit
- map
- flatMap

We already know `pure` and `map`.

Usual symbols and function names for flatMap are: `flatMap`, `bind`, `>>=` etc.  

---
When we `map` (Functor) over a `List[T]`, and produce another `List[T]` for each element, we get a `List[List[T]]`.  
But if we do `flatMap` instead, we get a  **flattened** `List[T]`.  
That's why it's called FLAT-MAP, you do **`map`+`flatten`** in one go.  
Since we do `map` we can say that Monad is also a Functor.


The nice thing about Monad is that it works for any `F[F[T]]`, not only `List[List[T]]`.  
But notice that it needs to be the same HKT: `F == F`, which is why monads are a bit *limited* in their power.  
We also say that "monads don't compose (well)".





---
## Syntax sugar - for comprehension
Suppose we have these:
```scala
def parseInt(str: String): Option[Int] = scala.util.Try(str.toInt).toOption

def divide(a: Int, b: Int): Option[Int] = if(b == 0) None else Some(a / b)
```

Then this:
```scala
for {
    aNum <- parseInt("10")
    bNum <- parseInt("2")
    ans  <- divide(aNum, bNum)
} yield ans
```

is the same as this:
```scala
parseInt("10").flatMap { aNum =>
    parseInt("2").flatMap { bNum =>
        divide(aNum, bNum)
    }
}
```
Compiler converts the `for` comprehension into a series of `flatMap`-s.  
You can try it in [Ammonite REPL](https://ammonite.io/#desugar) with `desugar` and see for yourself.  
We can say that `aNum` is "pulled" from `Option[T]`.  

> In Haskell there is `do` notation which is basically the same thing

If we had `Future[T]`-s instead, the code looks like sequential code!  
We can read it from top to bottom, without having to chase callbacks.  

There is a similar concept in C#, JavaScript, Kotlin etc, called `async`-`await`.  
It is specialized to make async code *look like it is sequential*.  
One thing that is missing in JS though is the static type checking, 
so we can easily forget one `await` and suddenly our code does not behave like it should.. In scala we would get a compile error, so we are safe! :)









