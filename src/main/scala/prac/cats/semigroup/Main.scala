package prac.cats.semigroup

import cats.implicits._
import cats.Semigroup

object Main extends App {
  println(Semigroup[Int].combine(1, 2))
  println(Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6)))
  println(Semigroup[Option[Int]].combine(Option(1), Option(2)))
  println(Semigroup[Option[Int]].combine(Option(1), None))
  println(Semigroup[Int => Int].combine(_ + 1, _ * 10).apply(6))
  println(Option(1).combine(Option(2)))

  println(Map("foo" -> Map("bar" -> 5)).combine(Map("foo" -> Map("bar" -> 6), "baz" -> Map())))
  println(Map("foo" -> List(1, 2)).combine(Map("foo" -> List(3, 4), "bar" -> List(42))))

  println(Map("foo" -> Map("bar" -> 5)) ++ Map("foo" -> Map("bar" -> 6), "baz" -> Map()))
  println(Map("foo" -> List(1, 2)) ++ Map("foo" -> List(3, 4), "bar" -> List(42)))

  println(Map("foo" -> Map("bar" -> List(5, 6))).combine(Map("foo" -> Map("bar" -> List(7, 8, 9)), "baz" -> Map())))

  val aMap = Map("foo" -> Map("bar" -> 5))
  val anotherMap = Map("foo" -> Map("bar" -> 6))
  val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)

  println(combinedMap.get("foo"))

  val one: Option[Int] = Option(1)
  val two: Option[Int] = Option(2)
  val n: Option[Int] = None

  println(one |+| two)
  println(two |+| one)
  println(one |+| n)
  println(n |+| two)
  println(n |+| n)
}
