package com.gu.spy

class SpySuite extends munit.FunSuite {
  test("spy") {
    case class Address(planet: String, city: String)
    case class User(name: String, age: Int, address: Address)
    val user = User("Picard", 75, Address("Earth", "San Francisco"))

    val expected =
      """
        |User
        |  name: Picard
        |  age: 75
        |  address: Address
        |    planet: Earth
        |    city: San Francisco
        |""".stripMargin

    assertEquals(user.spy, expected)
  }
}