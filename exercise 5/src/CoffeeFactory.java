class CoffeeFactory {
    public Coffee createCoffee(String type) {
        // Return specific coffee object based on type
        switch (type.toLowerCase()) {
            case "espresso":
                return new Espresso();
            case "latte":
                return new Latte();
            case "cappuccino":
                return new Cappuccino();
            default:
                throw new IllegalArgumentException("Unknown coffee type: " + type);
        }
    }
}


