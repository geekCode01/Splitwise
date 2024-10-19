package org.example.splitwise.split.type;

import org.example.splitwise.split.Split;
import org.example.splitwise.user.User;

public class ExactSplit extends Split {
    double amount;
    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}
