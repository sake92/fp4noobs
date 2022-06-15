package fp

// reinvent Option because it already has map/flatMap
enum Opt[+T]:
  case Empty extends Opt
  case Filled(value: T) extends Opt[T]
