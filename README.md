# fp4noobs

FP for beginners


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


