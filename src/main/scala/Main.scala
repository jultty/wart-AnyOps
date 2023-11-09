import scala.collection.mutable.ListBuffer

@main def hello: Unit =
  println(numbers)
  println(f_numbers)
  val jane = p("Jane", "Doe")
  println(jane.name)

  // wtfs
  println(3 + 'a') // 100
  // println(3 + " concat") // 3 concatted
  println('a' == 97) // true

  val explicit_char: Char = 'a'
  val explicit_int: Int = '3'

  println(3 + explicit_char) // 100
  // println(explicit_int + " concat") // 51 concatted
  println(explicit_char == 97) // true


// imperative
def double(ints: List[Int]): List[Int] =
  val buffer = new ListBuffer[Int]()
  for i <- ints do
    buffer += i * 2
  buffer.toList

val old_numbers = List(1, 2, 3)
val numbers = double(old_numbers)

// functional
val f_numbers = old_numbers.map(_ * 2)

case class Person(
  name: String,
  surname: String,
)

def p(name: String, surname: String): Person =
  Person(name, surname)

implicit val reverseOrdering: Ordering[Int] = Ordering.Int.reverse
// List(1, 2, 3).sorted // List(3, 2, 1)

def isTruthy(a: Matchable) = a match
  case 0 | "" => false
  case _ => true

def morePatternMatching =
  val i = 3
  val numAsString = i match
  case 1 | 3 | 5 | 7 | 9 => "odd"
  case 2 | 4 | 6 | 8 | 10 => "even"
  case _ => "too big"

