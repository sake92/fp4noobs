# FP for beginners, in Scala 3

> A change in perspective is worth 80 IQ points ~ Alan Kay


Disclaimer:  
This is by no means a thorough introduction to FP concepts.  
It contains lots of my *personal* opinions/views.  
But it could help beginners to understand faster some of this mathy stuff.

The basics of FP are usually simple to understand:
- immutability
- tuples
- ADTs: `case class`es and `enum`s
- recursion
- collections functions like `map`, `fold` etc


Where it gets challenging is the maths(category theory) terminology and advanced types (higher kinded types).  
This repo tries to simplify that learning path.  
It introduces minimal syntax and some examples of usage for each concept.  
We don't bother with *laws* here, we just consider the intuition and usage.  

Each of these concepts is using the [typeclass pattern](https://docs.scala-lang.org/scala3/book/ca-type-classes.html) which you need to know already.

Order of reading:  
1. [semigroup](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/semigroup)
1. [monoid](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/monoid)
1. [functor](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/functor)
1. [applicative](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/applicative)
1. [monad](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/monad)
1. [writer](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/writer)
1. [reader](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/reader)
1. [state](https://github.com/sake92/fp4noobs/tree/main/fp/src/main/scala/fp/state)

Resources:
- https://www.youtube.com/playlist?list=PLFrwDVdSrYE6dy14XCmUtRAJuhCxuzJp0
- https://www.scalawithcats.com/dist/scala-with-cats.html

Highly recommended exercises:
- https://www.scala-exercises.org/fp_in_scala
- https://www.scala-exercises.org/cats

## Running examples

```bash
.\millw.bat fp.runMain fp.functor.functorMain
```

---
## Nicer Syntax
We usually want a nice "operator-ish" syntax like `1 + 3`.  
Scala has *infix notation* for functions.  
But those have to be called on an object. 

If we have:
```scala
def f(a: Int, b: Int): Int = a + b

f(1, 2)
1 f 2   // does not compile
```
But this works:
```scala
class MyClass(x: Int):
  def f(y: Int): Int = x + y

val myCls = MyClass(1)
myCls.f(2)
myCls f 2   // works!
```

So we fix the first example by extending the type using "extension function":
```scala
extension (x: Int)
  def f(y: Int): Int = x + y

1 f 2   // works, yay!
```



---

## Advanced types
This is mostly a rewording of Adriaan's answer from SO: https://stackoverflow.com/a/6427289/4496364  
He uses an analogy with values:
- a **proper value** is "immediately usable" and "concrete", like `1`, `true`, an object etc. 
- **value constructor**(function) is NOT "immediately usable", you need to give it a value first
This is "first order" value constructor, one level deep.
- **value constructor constructor** is NOT "immediately usable". If you give it a value, it will return another value constructor. This is a **higher order** abstraction.   
"higher-order abstraction abstracts over something that abstracts over something" - Adriaan   
This reminds me of OOP abstract factory design pattern (factory of factories). :)


If we have these:
```scala
class String
class List[T]
class Functor[F[_]]
```

you can refer to this table:
| Syntax          | proper  | first-order         | higher-order                  |
| --------------- | ------- | ------------------- |----------------------------   |
| values          | 10      | (x: Int) => x       |(f: (Int => Int)) => f(10)     |
| types (classes) | String  | List                |Functor                        |
| types           | String  | type F = [X] =>> R  |type FF = [X] =>> [Y] =>> R    |


![](https://i.stack.imgur.com/K0dwL.jpg)


https://adriaanm.github.io/files/higher.pdf



