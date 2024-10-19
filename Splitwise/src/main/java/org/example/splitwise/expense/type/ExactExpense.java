package org.example.splitwise.expense.type;

import org.example.splitwise.expense.Expense;
import org.example.splitwise.expense.ExpenseMetadata;
import org.example.splitwise.split.Split;
import org.example.splitwise.split.type.ExactSplit;
import org.example.splitwise.user.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        return totalAmount == sumSplitAmount;
    }
}