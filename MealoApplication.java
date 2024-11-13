package com.example.mealo;

public class MealoApplication {

    // Static variables (protected for access within subclasses)
    protected static int totalOrders = 0;
    protected static double serviceCharge = 2.50;

    // Food Item (Abstract Base class)
    abstract class FoodItem {
        private String itemName;
        private double price;

        // Constructor
        public FoodItem(String name, double price) {
            this.itemName = name;
            this.price = price;
        }

        // Overloaded constructor (polymorphism)
        public FoodItem(String name) {
            this.itemName = name;
            this.price = 0.0;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (price > 0) {
                this.price = price;
            } else {
                System.out.println("Price must be positive.");
            }
        }

        // Abstract method for displaying item details
        public abstract void displayItemDetails();
    }

    // BeverageItem class following SRP
    class BeverageItem extends FoodItem {
        private boolean isCold;

        public BeverageItem(String name, double price, boolean isCold) {
            super(name, price);
            this.isCold = isCold;
        }

        @Override
        public void displayItemDetails() {
            System.out.println("Item: " + getItemName() + ", Price: " + getPrice() + ", Is Cold: " + isCold);
        }
    }

    // Order class (Adheres to SRP)
    class Order {
        private int orderNumber;
        private double totalAmount;

        public Order(int number) {
            this.orderNumber = number;
            this.totalAmount = 0.0;
            totalOrders++;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        // Method to add item price to total amount
        public void addItem(FoodItem item) {
            totalAmount += item.getPrice();
            System.out.println("Added: " + item.getItemName() + " to Order #" + orderNumber);
        }

        // Method to calculate total with service charge
        public double getFinalTotal() {
            return totalAmount + serviceCharge;
        }

        public void displayOrderSummary() {
            System.out.println("Order #" + orderNumber + " Total: " + totalAmount);
        }
    }

    // PremiumOrder class following OCP
    class PremiumOrder extends Order {
        private double discount;

        public PremiumOrder(int number, double discount) {
            super(number);
            this.discount = discount;
        }

        @Override
        public double getFinalTotal() {
            double totalWithService = super.getFinalTotal();
            return totalWithService - discount;
        }
    }

    // SpecialOrder class adhering to LSP
    class SpecialOrder extends PremiumOrder {

        public SpecialOrder(int number, double discount) {
            super(number, discount);
        }

        public void addSpecialGift() {
            System.out.println("Special gift added to Order #" + getOrderNumber());
        }
    }

    // Separate class to handle order processing (SRP)
    class OrderProcessor {
        public void processOrder(Order order) {
            System.out.println("Processing Order #" + order.getOrderNumber());
            order.displayOrderSummary();
            System.out.println("Final Total: " + order.getFinalTotal());
        }
    }

    public static void main(String[] args) {
        MealoApplication app = new MealoApplication();

        // Using BeverageItem class (SRP)
        FoodItem burger = app.new BeverageItem("Burger", 5.99, true);
        burger.displayItemDetails();

        // Create an order and add items (SRP)
        Order order1 = app.new Order(101);
        order1.addItem(burger);

        // Display order summary
        order1.displayOrderSummary();
        System.out.println("Final Total with Service Charge: " + order1.getFinalTotal());

        // Create a PremiumOrder (OCP)
        PremiumOrder premiumOrder = app.new PremiumOrder(102, 3.0);
        premiumOrder.addItem(burger);
        System.out.println("Final Total with Discount: " + premiumOrder.getFinalTotal());

        // Create a SpecialOrder (LSP)
        SpecialOrder specialOrder = app.new SpecialOrder(103, 2.0);
        specialOrder.addItem(burger);
        specialOrder.addSpecialGift();
        System.out.println("Final Total for Special Order: " + specialOrder.getFinalTotal());

        // Using OrderProcessor class (SRP)
        OrderProcessor processor = app.new OrderProcessor();
        processor.processOrder(order1);
        processor.processOrder(premiumOrder);
        processor.processOrder(specialOrder);

        // Total orders created
        System.out.println("Total Orders Created: " + MealoApplication.totalOrders);
    }
}
