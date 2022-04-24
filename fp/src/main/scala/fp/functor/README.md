
# Functor ~= "Mappable"

Functions:
- map

Usual symbols and function names are: `map`, `fmap`, `<$>` etc.  


It represents *mapping function over a container*.  
Functor is using higher-kinded types so it needs some time to sink in.

The `map` operation does not change the structure itself,  
it will only update the inner(wrapped) value.  

---
Notice that  `<$>` looks like `$` (function application),  
because func-tor *is function application* but within a container/context.

And just as we can `compose` functions, we can compose Functors too!  
E.g. if we compose `Functor[List] and Functor[Option]` we get a `Functor[List[Option]]`.


