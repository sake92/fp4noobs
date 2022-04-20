
# Applicative Functor ~= "Monoidal Functor"

Usual symbols and function names are: `ap`(apply), `<*>` etc.  


Applicative is a Functor, but the difference is that the function *itself is also trapped* inside a container/context.

The `pure` function is simple, it lifts a pure value to a context.

Examples:
- apply a list of functions to a list of values, pairwise




