
# Semigroup ~= "Combineable"

Functions:
- append

Usual symbols and function names are: `<>`, `|+|`, `sappend` etc.

It represents an *associative operation*:  
`(a + b) + c ==  a + (b + c) == a + b + c`  

That means that **ordering of operations does not matter**.  
You can choose whichever order you prefer, you will always get *correct result*.  
In simpler terms, we can *put parens wherever we like*.

---
For example, to get better performance you could parallelize some operations:
```scala
a+b+c+d == (a+b)+(c+d)
```
This means we can calculate `a+b` and `c+d` in parallel.  
That is possible only because the operation is **associative**.

---

Semigroup(and Monoid) is one of rare typeclasses that can have *many valid implementations* for some type.  
For example, for `Int` type, both `+` and `*` are valid semigroups.  
Also, for `Boolean` type, both `||` and `&&` are valid semigroups.  

For that reason Haskell has `newtype`s (wrapper types) like `Sum` and `Product` for `Integer`s,  
and `All` and `Any` for `Bool`s.  
Similar concept to `newtype` in Scala 3 is `opaque type`.  
https://docs.scala-lang.org/scala3/book/types-opaque-types.html#opaque-types




