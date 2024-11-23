import java.util.ArrayList;
import java.util.List;

class CoffeeOrderSystem {
    private List<Observer> customers = new ArrayList<>();

    public void addObserver(Observer observer) {
        customers.add(observer); // Add a customer (observer)
    }

    public void removeObserver(Observer observer) {
        customers.remove(observer); // Remove a customer (observer)
    }

    public void notifyObservers(String message) {
        // Notify all registered customers
        for (Observer customer : customers) {
            customer.update(message);
        }
    }
}
