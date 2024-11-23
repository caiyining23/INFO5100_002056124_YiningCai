class CoffeeShopManager {
    private static CoffeeShopManager instance;

    private CoffeeShopManager() {} // Private constructor to prevent external instantiation

    public static CoffeeShopManager getInstance() {
        // Ensure only one instance is created
        if (instance == null) {
            instance = new CoffeeShopManager();
        }
        return instance;
    }

    public void logMessage(String message) {
        System.out.println("[Manager]: " + message);
    }
}
