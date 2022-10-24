package machine

const val waterForCup = 200
const val milkForCup = 50
const val coffeeBeansForCup = 15

fun main() {
    println("Write how many cups of coffee you will need:")
    val valueOfCoffeeCups = readln().toInt()
    val totalAmountOfWater = waterForCup * valueOfCoffeeCups
    val totalAmountOfMilk = milkForCup * valueOfCoffeeCups
    val totalAmountOfBeans = coffeeBeansForCup * valueOfCoffeeCups

    println("""
        For $valueOfCoffeeCups cups of coffee you will need:
        $totalAmountOfWater ml of water
        $totalAmountOfMilk ml of milk
        $totalAmountOfBeans g of coffee beans
    """.trimIndent())
}