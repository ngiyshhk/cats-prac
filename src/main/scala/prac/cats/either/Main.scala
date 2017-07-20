package prac.cats.either

import cats._
import cats.implicits._

object Main extends App {
  val right: Either[String, Int] = Either.right(5)
  println(right.map(_ + 1))

  val left: Either[String, Int] = Either.left("abc")
  println(left.map(_ + 1))

  println(right.flatMap(x => Either.right(x + 1)))
  println(left.flatMap(x => Either.right(x + 1)))

  object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
      if (s.matches("-?[0-9]+")) Either.right(s.toInt)
      else Either.left(new NumberFormatException(s"${s} is not a valid integer."))

    def reciprocal(i: Int): Either[IllegalArgumentException, Double] =
      if (i == 0) Either.left(new IllegalArgumentException("Cannot take reciprocal of 0."))
      else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Exception, String] =
      parse(s).flatMap(reciprocal).map(stringify)
  }

  println(EitherStyle.parse("Not a number"))
  println(EitherStyle.parse("2"))

  {
    import EitherStyle._
    println(magic("0"))
    println(magic("1"))
    println(magic("Not a number"))

    val result = magic("2") match {
      case Left(_: NumberFormatException) ⇒ "Not a number!"
      case Left(_: IllegalArgumentException) ⇒ "Can't take reciprocal of 0!"
      case Left(_) ⇒ "Unknown error"
      case Right(r) ⇒ s"Got reciprocal: $r"
    }
    println(result)
  }

  object EitherStyleWithAdts {
    sealed abstract class Error
    final case class NotANumber(string: String) extends Error
    final case object NoZeroReciprocal extends Error

    def parse(s: String): Either[Error, Int] =
      if (s.matches("-?[0-9]+")) Either.right(s.toInt)
      else Either.left(NotANumber(s))

    def reciprocal(i: Int): Either[Error, Double] =
      if (i == 0) Either.left(NoZeroReciprocal)
      else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Error, String] =
      parse(s).flatMap(reciprocal).map(stringify)
  }

  {
    import EitherStyleWithAdts._
    val result = magic("2") match {
      case Left(NotANumber(_)) ⇒ "Not a number!"
      case Left(NoZeroReciprocal) ⇒ "Can't take reciprocal of 0!"
      case Right(r) ⇒ s"Got reciprocal: $r"
    }
    println(result)
  }

  println(left.leftMap(_.reverse))

  println(Either.catchOnly[NumberFormatException]("abc".toInt))
  println(Either.catchNonFatal(1 / 0))

  println(7.asRight[String])
  println("hello cats".asLeft[Int])

}
