package machine

enum class CoffeeType(val requestedWater: Int, val requestedMilk: Int, val requestedBeans: Int, val price: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6),
    NULL(0,0,0,0)
}

class CoffeeMachine(
    private var totalAmountOfWater: Int,
    private var totalAmountOfMilk: Int,
    private var totalAmountOfBeans: Int,
    private var totalAmountOfCups: Int,
    private var totalAmountOfMoney: Int) {
    fun coffeeMachineInterface() {
        displayCurrentState()
        println()
        println("Write action (buy, fill, take): ")
        val stringForAction = readln()
        when (stringForAction.lowercase()) {
            "buy" -> buyOperation()
            "fill" -> fillOperation()
            "take" -> takeOperation()
            else -> println("Invalid action")
        }
    }

    private fun buyOperation() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
        val chosenCoffee = when (readln()) {
            "1" -> CoffeeType.ESPRESSO
            "2" -> CoffeeType.LATTE
            "3" -> CoffeeType.CAPPUCCINO
            else -> CoffeeType.NULL
        }
        if (chosenCoffee != CoffeeType.NULL) {
            if (totalAmountOfWater >= chosenCoffee.requestedWater && totalAmountOfMilk >= chosenCoffee.requestedMilk &&
                totalAmountOfBeans >= chosenCoffee.requestedBeans && totalAmountOfCups >= 1)
            {
                totalAmountOfWater -= chosenCoffee.requestedWater
                totalAmountOfMilk -= chosenCoffee.requestedMilk
                totalAmountOfBeans -= chosenCoffee.requestedBeans
                totalAmountOfCups -= 1
                totalAmountOfMoney += chosenCoffee.price

                println()
                displayCurrentState()
            }
        }
    }

    private fun fillOperation() {
        println("Write how many ml of water you want to add:")
        val amountOfWaterToAdd = readln().toInt()
        println("Write how many ml of milk you want to add:")
        val amountOfMilkToAdd = readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        val amountOfBeansToAdd = readln().toInt()
        println("Write how many disposable cups you want to add:")
        val amountOfCupsToAdd = readln().toInt()
        println()

        totalAmountOfWater += amountOfWaterToAdd
        totalAmountOfMilk += amountOfMilkToAdd
        totalAmountOfBeans += amountOfBeansToAdd
        totalAmountOfCups += amountOfCupsToAdd

        displayCurrentState()
    }

    private fun takeOperation() {
        println("I gave you \$$totalAmountOfMoney")
        totalAmountOfMoney = 0

        println()
        displayCurrentState()
    }

    private fun displayCurrentState() {
        println("""
            The coffee machine has:
            $totalAmountOfWater ml of water
            $totalAmountOfMilk ml of milk
            $totalAmountOfBeans g of coffee beans
            $totalAmountOfCups disposable cups
            ${'$'}$totalAmountOfMoney of money
        """.trimIndent())
    }
}


fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)

    coffeeMachine.coffeeMachineInterface()

    /*
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

    val totalRequestOfWater = waterForCup * valueOfCoffeeCups
    val totalRequestOfMilk = milkForCup * valueOfCoffeeCups
    val totalRequestOfBeans = coffeeBeansForCup * valueOfCoffeeCups

    if (numberOfRequestedCoffeeCups < maxOfCupsThatCanMake) {
        val difference = maxOfCupsThatCanMake - numberOfRequestedCoffeeCups
        println("Yes, I can make that amount of coffee (and even $difference more than that)")
    } else if (numberOfRequestedCoffeeCups == maxOfCupsThatCanMake) {
        println("Yes, I can make that amount of coffee")
    } else {
        println("No, I can make only $maxOfCupsThatCanMake cups of coffee")
    }

    println("""
        For $numberOfRequestedCoffeeCups cups of coffee you will need:
        $totalAmountOfWater ml of water
        $totalAmountOfMilk ml of milk
        $totalAmountOfBeans g of coffee beans
    """.trimIndent())
    */
}