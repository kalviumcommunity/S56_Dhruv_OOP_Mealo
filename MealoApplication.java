package com.example.mealo;


public class MealoApplication {
    class FoodItem {
        String itemName;
        double price;

        // Constructor
        FoodItem(String name, double price) {
            this.itemName = name;
            this.price = price;
        }

        // function to display item details
        void displayItemDetails() {
            System.out.println("Item: " + itemName + ", Price: " + price);
        }

        // function to get the price
        double getPrice() {
            return price;
        }
    }

    class Order {
        int orderNumber;
        double totalAmount;

        // Constructor
        Order(int number) {
            this.orderNumber = number;
            this.totalAmount = 0.0;
        }

        // function to add item to order
        void addItem(FoodItem item) {
            totalAmount += item.getPrice();
            System.out.println("Added: " + item.itemName + " to Order #" + orderNumber);
        }

        // function to display order summary
        void displayOrderSummary() {
            System.out.println("Order #" + orderNumber + " Total: " + totalAmount);
        }
    }

    public static void main(String[] args) {

            // Creating an instance of main class to access inner class
            MealoApplication app = new MealoApplication();

            // Creating food item 
            FoodItem burger = app.new FoodItem("Burger", 5.99);
            FoodItem pizza = app.new FoodItem("Pizza", 8.99);

            // Displaying item 
            burger.displayItemDetails();
            pizza.displayItemDetails();

            // Creating an order 
            Order order1 = app.new Order(101);

            // Adding items to the order
            order1.addItem(burger);
            order1.addItem(pizza);

            // Displaying summary
            order1.displayOrderSummary();
        }
    }



