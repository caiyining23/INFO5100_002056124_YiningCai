class Espresso extends Coffee {
    public Espresso() {
        name = "Espresso";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing a strong and rich Espresso...");
    }
}
