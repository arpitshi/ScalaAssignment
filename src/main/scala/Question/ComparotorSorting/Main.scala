package Question.ComparotorSorting

object Main extends App{
  val myList = Cons(1, Cons(2, Cons(3, MyNil)))
  println(s"Size of myList: ${ListUtils.size(myList)}")

  val intComp = GenericComparator[Int]
  println(intComp.compare(3, 4)) // Output: -1
  println(intComp.compare(4, 4)) // Output: 0
  println(intComp.compare(5, 4))
  // Example usage of sort() method with IntComparator
  val intList = List(5, 2, 8, 1, 3)
  val sortedIntList = Bubblesort.sort(intList, GenericComparator[Int])
  println(s"Sorted intList: ${sortedIntList.mkString(", ")}")
}
