
# Semigroup - "Combineable"

It represents an *associative operation*:  
`(a + b) + c ==  a + (b + c) == a + b + c`  

That means that **ordering of operations does not matter**.  
You can choose whichever order you prefer, you will always get correct result.  
In simpler terms, we can *put parens wherever we like*.

---
For example, to get better performance you could parallelize some operations:
```scala
a+b+c+d == (a+b)+(c+d)
```
This means we can calculate `a+b` and `c+d` independently, in parallel.  
That is only possible because the operation is **associative**.

