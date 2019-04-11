// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg1: Any): String {
    when(arg1) {
        "Hello" -> return "world"
        "Howdy", "Bonjour" -> return "Say what?"
        0 -> return "zero"
        1 -> return "one"
        5, 9 -> return "low number"
        else -> return "I don't understand" 
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(arg1:Int, arg2:Int): Int {
    return arg1 + arg2;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(arg1:Int, arg2:Int): Int {
    return arg1 - arg2;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(arg1:Int, arg2:Int, body:(arg1:Int,arg2:Int) -> (Int)): Int {
    return body(arg1, arg2)
}

// write a class "Person" with first name, last name and age
class Person(firstName:String, lastName:String, age:Int) {
   
    public var firstName:String = firstName
    public var lastName:String = lastName
    public var age:Int  = age
        set(value) {
            field = value
            debugString = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
        }
    public var debugString: String = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"

    fun equals(otherPerson: Person): Boolean {
        return (otherPerson.hashCode() == this.hashCode())
    }
    
    override fun hashCode():Int {
        var code = 17
        code = 31 * code + firstName.hashCode()
        code = 31 * code + lastName.hashCode()
        code = 31 * code + age
        return code
    }
}

// write a class "Money"
class Money(amount:Int, currency:String) {

    var amount:Int = amount
        set(value) {
            println("Setting amount..")
            if (value >= 0) {
                field = value
            }
    }
    var currency:String = currency
        set(value) {
            if (value in listOf("USD", "EUR", "CAN", "GBP")) {
                field = value
            }
        }
    val converter: Map<String, Double> = hashMapOf("USD" to 10.0, "GBP" to 5.0, "EUR" to 15.0, "CAN" to 12.5)

    fun convert(otherCurrency:String): Money {
        if (otherCurrency in listOf("USD", "EUR", "CAN", "GBP")) {
            val newAmount = amount * (converter.get(otherCurrency)!!/ converter.get(currency)!!)
            val finalAmount = newAmount.toInt()
            return Money(finalAmount, otherCurrency)
        }
        return Money(amount, currency)
    }

    operator fun plus(other: Money): Money {
        val convertedMoney:Money = other.convert(currency)
        val sum:Int = convertedMoney.amount + amount
        return Money(sum, currency) 
    }
}


// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")


print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")

