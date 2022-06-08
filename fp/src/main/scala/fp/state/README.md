
# State

In FP we don't use `var`s.  
We transform previous values and create new ones.  
But not all problems are "stateless".

We can pass around state manually, for example in a recursive function:
```scala
def filterOddNums(listToFilter: List[Int], acc: List[Int]): List[Int] = ...

val odds = filterOddNums(myList, List.empty)
```
In the `acc` variable we will store the final result, but we need to propagate it wherever it is used.  
This is very cumbersome if we have multiple of these state variables.

---
State monad will do this propagation automatically for us.

It encapsulates **functions that take a state and returns a pair of a value and a state**.  












