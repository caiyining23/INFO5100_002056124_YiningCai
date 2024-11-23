abstract class Coffee {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract void prepare(); // Abstract method, implemented by subclasses
}
