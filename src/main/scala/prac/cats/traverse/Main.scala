package prac.cats.traverse

import cats._
import cats.data.{NonEmptyList, OneAnd, Validated, ValidatedNel}
import cats.implicits._

object Main extends App {

  def parseIntEither(s: String): Either[NumberFormatException, Int] =
    Either.catchOnly[NumberFormatException](s.toInt)
  def parseIntValidated(s: String): ValidatedNel[NumberFormatException, Int] =
    Validated.catchOnly[NumberFormatException](s.toInt).toValidatedNel
  println(List("1", "2", "3").traverseU(parseIntEither))
  println(List("1", "abc", "3").traverseU(parseIntEither))
  println(List("1", "abc", "def", "3").traverseU(parseIntEither))

  println(List("1", "2", "3").traverseU(parseIntValidated))
  println(List("1", "abc", "3").traverseU(parseIntValidated))
  println(List("1", "abc", "def", "3").traverseU(parseIntValidated))

  println(List(Option(1), Option(2), Option(3)).traverse(identity))
  println(List(Option(1), None, Option(3)).traverse(identity))

  println(List(Option(1), Option(2), Option(3)).sequence)
  println(List(Option(1), None, Option(3)).sequence)

  println(List(Option(1), Option(2), Option(3)).sequence_)
  println(List(Option(1), None, Option(3)).sequence_)

}
