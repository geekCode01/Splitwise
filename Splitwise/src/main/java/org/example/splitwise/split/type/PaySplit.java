package org.example.splitwise.split.type;

import org.example.splitwise.split.Split;
import org.example.splitwise.user.User;

public class PaySplit extends Split {
    double amount;

    public PaySplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
