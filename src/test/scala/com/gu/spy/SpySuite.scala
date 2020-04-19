package com.gu.spy

class SpySuite extends munit.FunSuite {
  case class Address(
    planet: String,
    city: String
  )
  case class User(
    name: String,
    age: Int,
    address: Address,
    species: Option[String] = None,
    connections: List[User] = Nil
  )

  test("spy") {
    val user = User(
      name = "Picard",
      age = 75,
      address = Address(
        "Earth",
        "San Francisco"
      ),
      species = None,
      connections = Nil
    )

    val expected =
      """
        |User
        |  name = Picard
        |  age = 75
        |  address: Address
        |    planet = Earth
        |    city = San Francisco
        |  species: None
        |  connections: Nil
        |""".stripMargin

    assertEquals(user.spy, expected)
  }

  test("Iterable") {
    val user = User(
      "Picard",
      75,
      Address("Earth", "San Francisco"),
      None,
      List(
        User(
          "Worf",
          67,
          Address("Kronos", "Capital City"),
        ),
        User(
          "Data",
          71,
          Address("Earth", "San Francisco"),
        )
      )
    )

    println(user.spy)

    val expected =
      """
        |User
        |  name = Picard
        |  age = 75
        |  address: Address
        |    planet = Earth
        |    city = San Francisco
        |  species: None
        |  connections: Iterable
        |    User
        |      name = Worf
        |      age = 67
        |      address: Address
        |        planet = Kronos
        |        city = Capital City
        |      species: None
        |      connections: Nil
        |    User
        |      name = Data
        |      age = 71
        |      address: Address
        |        planet = Earth
        |        city = San Francisco
        |      species: None
        |      connections: Nil
        |""".stripMargin

    assertEquals(user.spy, expected)
  }
}