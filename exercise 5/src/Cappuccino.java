class Cappuccino extends Coffee {
    public Cappuccino() {
        name = "Cappuccino";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing a frothy Cappuccino with equal parts espresso, steamed milk, and foam...");
    }
}

