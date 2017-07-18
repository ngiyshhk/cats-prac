package prac.cats.foldable

import cats._
import cats.implicits._

object Main extends App {
  println(Foldable[List].foldLeft(List(1, 2, 3), 0)(_ + _))
  println(Foldable[List].foldLeft(List("a", "b", "c"), "")(_ + _))

  val lazyResult =
    Foldable[List].foldRight(List(1, 2, 3), Now(0))((x, rest) => Later(x + rest.value))
  println(lazyResult.value)

  println(Foldable[List].fold(List("a", "b", "c")))
  println(Foldable[List].fold(List(1, 2, 3)))

  println(Foldable[List].foldMap(List("a", "b", "c"))(_.length))
  println(Foldable[List].foldMap(List(1, 2, 3))(_.toString))

  println(Foldable[List].foldK(List(List(1, 2), List(3, 4, 5))))
  println(Foldable[List].foldK(List(None, Option("two"), Option("three"))))

  println(Foldable[List].find(List(1, 2, 3))(_ > 2))
  println(Foldable[List].find(List(1, 2, 3))(_ > 5))

  println(Foldable[List].exists(List(1, 2, 3))(_ > 2))
  println(Foldable[List].exists(List(1, 2, 3))(_ > 5))

  println(Foldable[List].forall(List(1, 2, 3))(_ <= 3))
  println(Foldable[List].forall(List(1, 2, 3))(_ < 3))

  println(Foldable[List].toList(List(1, 2, 3)))
  println(Foldable[Option].toList(Option(42)))
  println(Foldable[Option].toList(None))

  println(Foldable[List].filter_(List(1, 2, 3))(_ < 3))
  println(Foldable[Option].filter_(Option(42))(_ != 42))

  def parseInt(s: String): Option[Int] =
    Either.catchOnly[NumberFormatException](s.toInt).toOption
  println(Foldable[List].traverse_(List("1", "2", "3"))(parseInt))
  println(Foldable[List].traverse_(List("a", "b", "c"))(parseInt))

  val FoldableListOption = Foldable[List].compose[Option]
  println(FoldableListOption.fold(List(Option(1), Option(2), Option(3), Option(4))))
  println(FoldableListOption.fold(List(Option("1"), Option("2"), Option("3"))))

  println(Foldable[List].isEmpty(List(1, 2, 3)))
  println(Foldable[List].dropWhile_(List(1, 2, 3))(_ < 2))
  println(Foldable[List].takeWhile_(List(1, 2, 3))(_ < 2))
}
