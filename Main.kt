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
        while (true) {
            println("Write action (buy, fill, take, remaining, exit): ")
            val stringForAction = readln()
            println()
            when (stringForAction.lowercase()) {
                "buy" -> buyOperation()
                "fill" -> fillOperation()
                "take" -> takeOperation()
                "remaining" -> displayCurrentState()
                "exit" -> break
                else -> println("Invalid action")
            }
            println()
        }
    }

    private fun buyOperation() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val chosenCoffee = when (readln()) {
            "1" -> CoffeeType.ESPRESSO
            "2" -> CoffeeType.LATTE
            "3" -> CoffeeType.CAPPUCCINO
            "back" -> return
            else -> CoffeeType.NULL
        }
        if (chosenCoffee != CoffeeType.NULL) {
            if (totalAmountOfWater >= chosenCoffee.requestedWater) {
                if (totalAmountOfMilk >= chosenCoffee.requestedMilk) {
                    if (totalAmountOfBeans >= chosenCoffee.requestedBeans) {
                        if (totalAmountOfCups >= 1) {
                            // Actual buying
                            println("I have enough resources, making you a coffee!")
                            totalAmountOfWater -= chosenCoffee.requestedWater
                            totalAmountOfMilk -= chosenCoffee.requestedMilk
                            totalAmountOfBeans -= chosenCoffee.requestedBeans
                            totalAmountOfCups -= 1
                            totalAmountOfMoney += chosenCoffee.price
                        } else println("Sorry, not enough disposable cups")
                    } else println("Sorry, not enough coffee beans!")
                } else println("Sorry, not enough milk!")
            } else println("Sorry, not enough water!")
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

        totalAmountOfWater += amountOfWaterToAdd
        totalAmountOfMilk += amountOfMilkToAdd
        totalAmountOfBeans += amountOfBeansToAdd
        totalAmountOfCups += amountOfCupsToAdd
    }

    private fun takeOperation() {
        println("I gave you \$$totalAmountOfMoney")
        totalAmountOfMoney = 0
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
}