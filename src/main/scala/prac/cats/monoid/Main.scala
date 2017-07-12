package prac.cats.monoid

import cats._
import cats.implicits._

object Main extends App {
  println(Monoid[String].empty)
  println(Monoid[String].combineAll(List("a", "b", "c")))
  println(Monoid[String].combineAll(List()))

  println(Monoid[Map[String, Int]].combineAll(List(Map("a" -> 1, "b" -> 2), Map("a" -> 3))))
  println(Monoid[Map[String, Int]].combineAll(List()))

  val l = List(1, 2, 3, 4, 5)
  println(l.foldMap(identity))
  println(l.foldMap(i => i * i))
  println(l.foldMap(i => i.toString))

  println(l.foldMap(i => (i, i.toString)))
}
