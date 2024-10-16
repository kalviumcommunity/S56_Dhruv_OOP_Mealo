package com.example.mealo;

public class MealoApplication {

    // Static variables (protected for access within subclasses)
    protected static int totalOrders = 0;  // Keeping track of total orders
    protected static double serviceCharge = 2.50;  // Service charge applied to all orders

    // Food Item (Abstract Base class)
    abstract class FoodItem {
        // Private data members (encapsulation)
        private String itemName;
        private double price;

        // Constructor (public for external access)
        public FoodItem(String name, double price) {
            this.itemName = name;
            this.price = price;
        }

        // Overloaded constructor for FoodItem (demonstrating polymorphism)
        public FoodItem(String name) {
            this.itemName = name;
            this.price = 0.0;  // Default price
        }

        // Public accessor (getter) for itemName (encapsulation)
        public String getItemName() {
            return itemName;
        }

        // Public mutator (setter) for itemName (encapsulation)
        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        // Public accessor (getter) for price (encapsulation)
        public double getPrice() {
            return price;
        }

        // Public mutator (setter) for price (encapsulation)
        public void setPrice(double price) {
            if (price > 0) {  // Validation to ensure the price is positive
                this.price = price;
            } else {
                System.out.println("Price must be positive.");
            }
        }

        // Abstract method (virtual function) to display item details
        public abstract void displayItemDetails();
    }

    // Single Inheritance: BeverageItem class inherits from FoodItem class
    class BeverageItem extends FoodItem {
        private boolean isCold;

        // Constructor for BeverageItem
        public BeverageItem(String name, double price, boolean isCold) {
            super(name, price);  // Call parent class (FoodItem) constructor
            this.isCold = isCold;
        }

        // Implement the abstract method (overriding the virtual function)
        @Override
        public void displayItemDetails() {
            System.out.println("Item: " + getItemName() + ", Price: " + getPrice() + ", Is Cold: " + isCold);
        }
    }

    // Order class (Base class)
    class Order {

        // Private data members (encapsulation)
        private int orderNumber;
        private double totalAmount;

        // Constructor (public for external access)
        public Order(int number) {
            this.orderNumber = number;
            this.totalAmount = 0.0;
            totalOrders++;  // Increment total orders whenever an order is created
        }

        // Accessor (getter) for orderNumber (encapsulation)
        public int getOrderNumber() {
            return orderNumber;
        }

        // Accessor (getter) for totalAmount (encapsulation)
        public double getTotalAmount() {
            return totalAmount;
        }

        // Private method to calculate total with service charge (abstraction)
        private double calculateTotalWithServiceCharge() {
            return totalAmount + serviceCharge;
        }

        // Public method to get final total (abstraction)
        public double getFinalTotal() {
            return calculateTotalWithServiceCharge();
        }

        // Public method to add item to order (abstraction)
        public void addItem(FoodItem item) {
            totalAmount += item.getPrice();
            System.out.println("Added: " + item.getItemName() + " to Order #" + orderNumber);
        }

        // Public method to display order summary (abstraction)
        public void displayOrderSummary() {
            System.out.println("Order #" + orderNumber + " Total: " + totalAmount);
        }
    }

    // Multilevel Inheritance: PremiumOrder inherits from Order
    class PremiumOrder extends Order {
        private double discount;  // Additional attribute for premium orders

        // Constructor
        public PremiumOrder(int number, double discount) {
            super(number);  // Call parent class (Order) constructor
            this.discount = discount;
        }

        // Override to apply discount
        @Override
        public double getFinalTotal() {
            double totalWithService = super.getFinalTotal();  // Call parent class method
            return totalWithService - discount;
        }
    }

    // Further Multilevel Inheritance: SpecialOrder inherits from PremiumOrder
    class SpecialOrder extends PremiumOrder {

        // Constructor
        public SpecialOrder(int number, double discount) {
            super(number, discount);  // Call parent class (PremiumOrder) constructor
        }

        // Additional functionality for special orders
        public void addSpecialGift() {
            System.out.println("Special gift added to Order #" + getOrderNumber());
        }
    }

    public static void main(String[] args) {
        // Create an instance of the main class to access the inner classes
        MealoApplication app = new MealoApplication();

        // Demonstrate constructor overloading (polymorphism)
        FoodItem defaultItem = app.new BeverageItem("Pasta", 0.0, false);  // Constructor with only name
        defaultItem.displayItemDetails();  // Default price is 0

        FoodItem pricedItem = app.new BeverageItem("Burger", 5.99, true);  // Constructor with name and price
        pricedItem.displayItemDetails();  // Displays price with 5.99

        // Creating an array of FoodItem objects
        FoodItem[] menu = new FoodItem[3];
        menu[0] = pricedItem;  // Already created burger item
        menu[1] = app.new BeverageItem("Coke", 2.99, true);  // Using BeverageItem
        menu[2] = app.new BeverageItem("Salad", 4.99, false);

        // Displaying all menu items using the array
        for (FoodItem item : menu) {
            item.displayItemDetails();
        }

        // Creating an order object
        Order order1 = app.new Order(101);
        order1.addItem(menu[0]);  // Adding Burger
        order1.addItem(menu[1]);  // Adding Coke (from BeverageItem)

        // Displaying order summary
        order1.displayOrderSummary();

        // Display final total with service charge
        System.out.println("Final Total with Service Charge: " + order1.getFinalTotal());

        // Total orders created
        System.out.println("Total Orders Created: " + MealoApplication.totalOrders);

        // Demonstrate PremiumOrder with discount (Multilevel inheritance)
        SpecialOrder specialOrder = app.new SpecialOrder(102, 3.0);  // Create a SpecialOrder
        specialOrder.addItem(menu[2]);  // Adding Salad to the order
        specialOrder.displayOrderSummary();
        System.out.println("Final Total with Discount: " + specialOrder.getFinalTotal());
        specialOrder.addSpecialGift();  // Adding a special gift
    }
}
