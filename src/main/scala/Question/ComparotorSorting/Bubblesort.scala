package Question.ComparotorSorting
object Bubblesort {
  def sort[T](list: List[T], comparator: Comparator[T]): List[T] = {
    def bubbleSort(inputList: List[T]): List[T] = {
      var elements = inputList
      for {
        i <- 0 until elements.length - 1
        j <- 0 until elements.length - i - 1
      } {
        if (comparator.compare(elements(j), elements(j + 1)) > 0) {
          val temp = elements(j)
          elements = elements.updated(j, elements(j + 1))
          elements = elements.updated(j + 1, temp)
        }
      }
      elements
    }

    bubbleSort(list)
  }
}