package model;

public class Budget {
    private static int idCounter = 1;
    private int id;
    private  double amount;
    private double limit;

    public Budget(int id, double amount, double limit) {
        this.id = id;
        this.amount = amount;
        this.limit = limit;
    }

    public Budget() {

    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getLimit() {
        return limit;
    }
}
