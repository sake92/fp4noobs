
# Writer ~= "WithLogger"

Functions:
- tell



Since in FP we don't have global state, or any kind of class field/property,  
we have to pass the logger value explicitly, or wrap it in a `Writer`.

That "logger" could be a `String`, but that's too simplistic and not possible in most of programs.  
So we use any `Monoid`, to which we append log values. :)











