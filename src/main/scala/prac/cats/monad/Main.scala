package prac.cats.monad

import cats._
import cats.implicits._

object Main extends App {
  println(Option(Option(1)).flatten)
  println(Option(None).flatten)
  println(List(List(1), List(2, 3)).flatten)

  println(Monad[Option].pure(42))

  println(Monad[List].flatMap(List(1, 2, 3))(x => List(x, x)))

  println(Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy")))
  println(Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4)))
}
