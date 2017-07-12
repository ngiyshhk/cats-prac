package prac.cats.functor

import cats._
import cats.implicits._

object Main extends App {
  println(Option(1).map(_ + 1))
  println(List(1, 2, 3).map(_ + 1))
  println(Vector(1, 2, 3).map(_.toString))

  println(Functor[List].map(List("qwer", "adsfg"))(_.length))

  println(Functor[Option].map(Option("Hello"))(_.length))
  println(Functor[Option].map(None: Option[String])(_.length))

  val lenOption: Option[String] => Option[Int] = Functor[Option].lift(_.length)
  println(lenOption(Some("abcd")))

  val source = List("Cats", "is", "awesome")
  val product = Functor[List].fproduct(source)(_.length).toMap
  println(product.getOrElse("Cats", 0))
  println(product.getOrElse("is", 0))
  println(product.getOrElse("awesome", 0))

  val listOpt = Functor[List] compose Functor[Option]
  println(listOpt.map(List(Some(1), None, Some(3)))(_ + 1))
}
