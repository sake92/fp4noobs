
# Applicative Functor ~= "Lifted-function Mappable"

Functions:
- empty/pure
- ap

The `pure` function is simple, it lifts a pure value to a context.

Usual symbols and function names for ap are: `ap`(apply), `<*>` etc.  

Applicative is a Functor, but the difference is that the function *itself is also wrapped* inside a container/context.



Examples:
- apply a list of functions to a list of values, pairwise
- apply an Option function to an Option value




