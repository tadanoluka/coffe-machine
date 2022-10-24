package machine

const val waterForCup = 200
const val milkForCup = 50
const val coffeeBeansForCup = 15

fun main() {
    println("Write how many ml of water the coffee machine has:")
    val totalAmountOfWater = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val totalAmountOfMilk = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val totalAmountOfBeans = readln().toInt()
    println("Write how many cups of coffee you will need:")
    val numberOfRequestedCoffeeCups: Int = readln().toInt()

    val maxOfCupsThatCanMake = listOf(
        totalAmountOfWater / waterForCup,
        totalAmountOfMilk / milkForCup,
        totalAmountOfBeans / coffeeBeansForCup).minOrNull()!!

    // val totalRequestOfWater = waterForCup * valueOfCoffeeCups
    // val totalRequestOfMilk = milkForCup * valueOfCoffeeCups
    // val totalRequestOfBeans = coffeeBeansForCup * valueOfCoffeeCups

    if (numberOfRequestedCoffeeCups < maxOfCupsThatCanMake) {
        val difference = maxOfCupsThatCanMake - numberOfRequestedCoffeeCups
        println("Yes, I can make that amount of coffee (and even $difference more than that)")
    } else if (numberOfRequestedCoffeeCups == maxOfCupsThatCanMake) {
        println("Yes, I can make that amount of coffee")
    } else {
        println("No, I can make only $maxOfCupsThatCanMake cups of coffee")
    }

    /* println("""
        For $numberOfRequestedCoffeeCups cups of coffee you will need:
        $totalAmountOfWater ml of water
        $totalAmountOfMilk ml of milk
        $totalAmountOfBeans g of coffee beans
    """.trimIndent()) */
}