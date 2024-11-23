class Latte extends Coffee {
    public Latte() {
        name = "Latte";
    }

    @Override
    public void prepare() {
        System.out.println("Steaming milk and mixing with Espresso for a creamy Latte...");
    }
}
