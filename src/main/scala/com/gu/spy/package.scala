package com.gu

package object spy {
  implicit class SpyOps(obj: Any) {
    def spy: String = {
      val builder = new StringBuilder

      def loop(obj: Any, depth: Int, paramName: Option[String]): Unit = {
        val indent = "  " * depth
        val prettyName = paramName.fold("")(x => s"$x: ")
        val ptype = obj match { case _: Iterable[Any] => "" case obj: Product => obj.productPrefix case _ => obj.toString }

        builder append s"$indent$prettyName$ptype\n"

        obj match {
          case seq: Iterable[Any] =>
            seq.foreach(loop(_, depth + 1, None))
          case obj: Product =>
            (obj.productIterator zip obj.productElementNames)
              .foreach { case (subObj, paramName) => loop(subObj, depth + 1, Some(paramName)) }
          case _ =>
        }
      }

      loop(obj, 0, None)
      builder.toString
    }
  }
}
