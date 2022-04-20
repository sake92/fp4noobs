
# Functor ~= "Mappable"

Usual symbols and function names are: `map`, `fmap`, `<$>` etc.  
Notice that  `<$>` looks like `$` (function application),  
because func-tor *is function application* but within a container/context.

It represents a *mapping function over a container*.  
Functor is using higher-kinded types so it needs some time to sink in.

The `map` operation does not change the structure itself,  
it will only update the inner(wrapped) value.  



