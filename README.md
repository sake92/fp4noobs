# FP for beginners, in Scala 3

Order of reading:  
1. semigroup
1. monoid
1. functor

## Running

```bash
.\millw.bat fp.runMain fp.functor.functorMain
```

---
## Nicer Syntax
We usually want a nice "operator-ish" syntax like `1 + 3`.  
Scala has "infix notation" for functions.  
But those have to be defined on an object. 

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

So we "fix" the first example by extending the type using "extension functions":
```scala
extension (x: Int)
  def f(y: Int): Int = x + y
```



---

## Types
This is mostly a rewording of Adriaan's answer from SO: https://stackoverflow.com/a/6427289/4496364  
He uses an analogy with values:
- a **proper value** is "immediately usable" and "concrete", like `1`, `true`, an object etc. 
- **value constructor**(function) is NOT "immediatelly usable", you need to give it a value first
This is "first order" value constructor, one level deep.
- **value constructor constructor** is NOT "immediatelly usable". If you give it a value, it will return another value constructor. This is called a **higher order** abstraction.   
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
| types           | String  | ({type λ[x] = x})#λ |({type λ[F[x]] = F[String]})#λ |



![](https://i.stack.imgur.com/K0dwL.jpg)


https://adriaanm.github.io/files/higher.pdf



