package org.example.splitwise.expense.type;

import org.example.splitwise.expense.Expense;
import org.example.splitwise.expense.ExpenseMetadata;
import org.example.splitwise.split.Split;
import org.example.splitwise.split.type.EqualSplit;
import org.example.splitwise.user.User;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return true;
    }
}