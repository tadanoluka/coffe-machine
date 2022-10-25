package machine

class CoffeeMachine(water: Int, milk: Int, coffeeBeans: Int, disposableCups: Int, money: Int) {
    private var totalAmountOfWater = water
    private var totalAmountOfMilk = milk
    private var totalAmountOfBeans = coffeeBeans
    private var totalAmountOfCups = disposableCups
    private var totalAmountOfMoney = money

    private var currentState = CoffeeMachineState.CHOOSING_ACTION

    enum class CoffeeType(val requestedWater: Int, val requestedMilk: Int, val requestedBeans: Int, val price: Int) {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6),
        NULL(0,0,0,0)
    }

    enum class CoffeeMachineState {
        CHOOSING_ACTION,
        CHOOSING_TYPE_OF_COFFEE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_COFFEE_BEAMS,
        FILLING_DISPOSABLE_CUPS
    }

    init {
        printActionMenu()
    }

    private fun printActionMenu() {
        println("\nWrite action (buy, fill, take, remaining, exit):")
    }

    private fun printChoseCoffeeMenu() {
        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    }

    fun inputInterface(inputString: String) {
        when (currentState) {
            CoffeeMachineState.CHOOSING_ACTION -> {
                when (inputString.lowercase()) {
                    "buy" -> {
                        printChoseCoffeeMenu()
                        currentState = CoffeeMachineState.CHOOSING_TYPE_OF_COFFEE
                    }
                    "fill" -> {
                        println("Write how many ml of water you want to add:")
                        currentState = CoffeeMachineState.FILLING_WATER
                    }
                    "take" -> {
                        takeOperation()
                        printActionMenu()
                    }
                    "remaining" -> {
                        displayStocks()
                        printActionMenu()
                    }
                    else -> {
                        println("Invalid input for action.")
                        printActionMenu()
                    }
                }
            }
            CoffeeMachineState.CHOOSING_TYPE_OF_COFFEE -> {
                buyOperation(inputString.lowercase())

                printActionMenu()
                currentState = CoffeeMachineState.CHOOSING_ACTION
            }
            CoffeeMachineState.FILLING_WATER -> {
                fillWater(inputString.lowercase())

                println("Write how many ml of milk you want to add:")
                currentState = CoffeeMachineState.FILLING_MILK
            }
            CoffeeMachineState.FILLING_MILK -> {
                fillMilk(inputString.lowercase())

                println("Write how many grams of coffee beans you want to add:")
                currentState = CoffeeMachineState.FILLING_COFFEE_BEAMS
            }
            CoffeeMachineState.FILLING_COFFEE_BEAMS -> {
                fillBeans(inputString.lowercase())

                println("Write how many disposable cups you want to add:")
                currentState = CoffeeMachineState.FILLING_DISPOSABLE_CUPS
            }
            CoffeeMachineState.FILLING_DISPOSABLE_CUPS -> {
                fillCups(inputString.lowercase())

                printActionMenu()
                currentState = CoffeeMachineState.CHOOSING_ACTION
            }
        }
    }

    private fun buyOperation(inputString: String) {
        val chosenCoffee = when (inputString) {
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
        } else println("Sorry, not valid input for choosing coffee.")
    }

    private fun fillWater(inputString: String) {
        totalAmountOfWater += inputString.toInt()
    }

    private fun fillMilk(inputString: String) {
        totalAmountOfMilk += inputString.toInt()
    }

    private fun fillBeans(inputString: String) {
        totalAmountOfBeans += inputString.toInt()
    }

    private fun fillCups(inputString: String) {
        totalAmountOfCups += inputString.toInt()
    }

    private fun takeOperation() {
        println("I gave you \$$totalAmountOfMoney")
        totalAmountOfMoney = 0
    }

    private fun displayStocks() {
        println()
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
    while (true) {
        val userInput = readln()
        if (userInput.lowercase() == "exit") break
        else coffeeMachine.inputInterface(userInput)
    }

}