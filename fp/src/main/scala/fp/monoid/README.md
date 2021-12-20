
# Monoid ~= "Combineable" + "Empty"

Usual symbols and function names are: `empty`, `mempty`, `identity` etc.


Monoid extends Semigroup.  
It is also an *associative operation*, but it also has an **empty value**.

For example, `empty`:
- for `Int` (wrt sum) is `0`
- for `String` is `""`
- for `List[T]` is `List()`
- for `Option[T]` is `None`


--- 
In older Haskell versions Monoid did not extend Semigroup.  
It had `mappend` defined on itself.

