package prac.cats.identity

import cats._
import cats.implicits._

object Main extends App {
  type Id[A] = A
  val anId: Id[Int] = 42
  println(anId)

  val one: Int = 1
  println(Functor[Id].map(one)(_ + 1))

  println(Applicative[Id].pure(42))

  println(Monad[Id].map(one)(_ + 1))
  println(Monad[Id].flatMap(one)(_ + 1))

  val fortytwo: Int = 42
  println(Comonad[Id].coflatMap(fortytwo)(_ + 1))
}
