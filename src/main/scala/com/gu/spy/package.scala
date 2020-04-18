package com.gu

package object spy {
  implicit class SpyOps(obj: Any) {
    def spy: String = {
      val builder = new StringBuilder

      def loop(value: Any, depth: Int, name: Option[String]): Unit = {
        val indent = "  " * depth
        val row = value match {
          case seq: Iterable[_] => name.fold("")(x => s"$x: ") + (if (seq.nonEmpty) "Iterable" else "Nil")  // name: Iterable
          case obj: Product     => name.fold("")(x => s"$x: ") + obj.productPrefix                          // nane: ProductType
          case _                => name.fold("")(x => s"$x = ") + value.toString                            // name = Value
        }

        builder append s"$indent$row\n"

        value match {
          case seq: Iterable[Any] =>
            seq.foreach(loop(_, depth + 1, None))
          case obj: Product =>
            (obj.productElementNames zip obj.productIterator)
              .foreach { case (name, value) => loop(value, depth + 1, Some(name)) }
          case _ =>
        }
      }

      loop(obj, 0, None)
      builder.toString
    }
  }
}
