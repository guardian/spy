# spy

Library for Scala 2.13+ projects for pretty printing case classes with 
field names in hierarchical tree format. Useful for debugging purposes as it
gives human readable representation of case classes. Provides `.spy` extension
method for any object.

```
libraryDependencies += "com.gu" %% "spy" % "0.1.1"
```

Example usage

```scala
import com.gu.spy._
case class Address(street: String, city: String, planet: String, country: String)
case class User(name: String, age: Int, address: Address)
val user = User("Jean-Luc Picard", 79, Address("Starfleet", "San Francisco", "Earth", "United Federation of Planets"))
println(user.spy)
```


#### `println(user)`

```
User(Jean-Luc Picard,79,Address(Starfleet,San Francisco,Earth,United Federation of Planets))
```

#### `println(user.spy)`

```
User
  name: Jean-Luc Picard
  age: 79
  address: Address
    street: Starfleet
    city: San Francisco
    planet: Earth
    country: United Federation of Planets
```

## Credits

* Based on Xavier Guihot's StackOverflow [answer](https://stackoverflow.com/a/55032051/5205022). 
* [Case Class toString new behavior proposal](https://contributors.scala-lang.org/t/case-class-tostring-new-behavior-proposal-with-implementation/2056/44?u=mario-galic)


