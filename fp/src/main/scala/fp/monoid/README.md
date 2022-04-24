
# Monoid ~= "Combineable" + "Empty"

Functions:
- empty/pure
- append

Usual symbols and function names for append are: `<>`, `|+|`, `append` etc.

Usual symbols and function names for empty are: `empty`, `mempty`, `identity` etc.


Monoid extends Semigroup.  
It is also an *associative operation*, but it also has an **empty value**.

For example, `empty`:
- for `Int` (wrt sum) is `0`
- for `String` is `""`
- for `List[T]` is `List()`
- for `Option[T]` is `None`

Another typeclass which builds upon Monoid's power is Foldable.  
It provides handy utilities like `foldMap` (map+fold in one go) and many others.  
See https://typelevel.org/cats/typeclasses/foldable.html

--- 
In older Haskell versions Monoid did not extend Semigroup.  
It had `mappend` defined on itself.

