package com.example.mealo;

public class MealoApplication {

    // Static variables
    static int totalOrders = 0;  // Keeps track of total orders
    static double serviceCharge = 2.50;  // service charge applied to all orders

    class FoodItem {
        String itemName;
        double price;

        // Constructor
        FoodItem(String name, double price) {
            this.itemName = name;
            this.price = price;
        }

        // Function to display item details
        void displayItemDetails() {
            System.out.println("Item: " + itemName + ", Price: " + price);
        }

        // Function to get the price
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
            totalOrders++;  // Increment total orders whenever an order is created
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

    // Static method to calculate the final total with the service charge
    static double calculateFinalTotal(double totalAmount) {
        return totalAmount + serviceCharge;
    }

    public static void main(String[] args) {

        // Create an instance of the main class to access the inner classes
        MealoApplication app = new MealoApplication();

        // Creating an array of FoodItem objects
        FoodItem[] menu = new FoodItem[3];
        menu[0] = app.new FoodItem("Burger", 5.99);
        menu[1] = app.new FoodItem("Pizza", 8.99);
        menu[2] = app.new FoodItem("Salad", 4.99);

        // Displaying all menu items using the array
        for (FoodItem item : menu) {
            item.displayItemDetails();
        }

        // Creating an order object
        Order order1 = app.new Order(101);

        // Adding items from the array to the order
        order1.addItem(menu[0]);  // Adding Burger
        order1.addItem(menu[1]);  // Adding Pizza

        // Displaying order summary
        order1.displayOrderSummary();

        // Final total with service charge
        double finalTotal = MealoApplication.calculateFinalTotal(order1.totalAmount);
        System.out.println("Final Total with Service Charge: " + finalTotal);

        // Total orders created
        System.out.println("Total Orders Created: " + MealoApplication.totalOrders);
    }
}
