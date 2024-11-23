import java.util.Scanner;

public class CoffeeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeShopManager manager = CoffeeShopManager.getInstance();
        CoffeeFactory coffeeFactory = new CoffeeFactory();
        CoffeeOrderSystem orderSystem = new CoffeeOrderSystem();

        System.out.println("Welcome to the Coffee Shop!");

        // Adding customers to the observer system
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");

        orderSystem.addObserver(customer1);
        orderSystem.addObserver(customer2);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Espresso");
            System.out.println("2. Latte");
            System.out.println("3. Cappuccino");
            System.out.println("4. Exit");
            System.out.print("Choose your coffee (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 4) {
                System.out.println("Thank you for visiting!");
                break;
            }

            // Map user choice to coffee type
            String coffeeType = switch (choice) {
                case 1 -> "Espresso";
                case 2 -> "Latte";
                case 3 -> "Cappuccino";
                default -> null;
            };

            if (coffeeType == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // Log the order
            manager.logMessage("Processing order for " + coffeeType);

            // Create and prepare the coffee
            Coffee coffee = coffeeFactory.createCoffee(coffeeType);
            coffee.prepare();

            // Notify all customers
            orderSystem.notifyObservers(coffee.getName() + " is ready!");
        }

        scanner.close();
    }
}
