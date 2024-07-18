package Question.ComparotorSorting


trait Comparator[T] {
  def compare(o1: T, o2: T): Int
}

object GenericComparator {
  implicit def apply[T](implicit ord: Ordering[T]): Comparator[T] = new Comparator[T] {
    override def compare(o1: T, o2: T): Int = {
      if (ord.lt(o1, o2)) -1
      else if (ord.gt(o1, o2)) 1
      else 0
    }
  }
}

