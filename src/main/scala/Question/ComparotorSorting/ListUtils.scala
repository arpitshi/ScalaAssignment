package Question.ComparotorSorting
sealed trait MyList[+T]

case object MyNil extends MyList[Nothing]

case class Cons[+T](head: T, tail: MyList[T]) extends MyList[T]

object ListUtils {
  def size[T](list: MyList[T]): Int = list match {
    case MyNil => 0
    case Cons(_, tail) => 1 + size(tail)
  }
}



//class GenericComparator {
//  implicit def apply[T](implicit ord: Ordering[T]): Comparator[T] = new Comparator[T] {
//    override def compare(o1: T, o2: T): Int = {
//      if (ord.lt(o1, o2)) -1
//      else if (ord.gt(o1, o2)) 1
//      else  0
//    }
//  }
//}














//object ListExtensions {
//  implicit class SortableList[T](l: List[T]) {
//    def sort(comparator: Comparator[T]): List[T] = {
//      val buffer = ListBuffer(l: _*)
//      val n = buffer.length
//
//      for (i <- 0 until n) {
//        for (j <- 0 until n - 1 - i) {
//          if (comparator.compare(buffer(j), buffer(j + 1)) > 0) {
//            val temp = buffer(j)
//            buffer(j) = buffer(j + 1)
//            buffer(j + 1) = temp
//          }
//        }
//      }
//      buffer.toList
//    }
//  }
//}
























//object StringComparator extends Comparator[String] {
//  override def compare(o1: String, o2: String): Int = {
//    o1.compareTo(o2)
//  }
//}