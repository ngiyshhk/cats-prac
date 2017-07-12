package prac.cats.applicative

import cats._
import cats.implicits._

object Main extends App {

  println(Applicative[Option].pure(1))
  println(Applicative[List].pure(1))

  println((Applicative[List] compose Applicative[Option]).pure(1))

  println(Monad[Option].pure(1))
  println(Applicative[Option].pure(1))
}
