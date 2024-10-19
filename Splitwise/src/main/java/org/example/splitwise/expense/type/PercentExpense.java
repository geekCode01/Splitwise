package org.example.splitwise.expense.type;

import org.example.splitwise.expense.Expense;
import org.example.splitwise.expense.ExpenseMetadata;
import org.example.splitwise.split.Split;
import org.example.splitwise.split.type.PercentSplit;
import org.example.splitwise.user.User;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
        }

        double totalPercent = 100;
        double sumSplitPercent = 0;
        for (Split split : getSplits()) {
            PercentSplit percentSplit = (PercentSplit) split;
            sumSplitPercent += percentSplit.getPercent();
        }

        return totalPercent == sumSplitPercent;
    }
}