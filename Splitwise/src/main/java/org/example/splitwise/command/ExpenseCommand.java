package org.example.splitwise.command;

import org.example.splitwise.expense.ExpenseType;
import org.example.splitwise.expense.manager.ExpenseManager;
import org.example.splitwise.split.Split;
import org.example.splitwise.split.type.EqualSplit;
import org.example.splitwise.split.type.ExactSplit;
import org.example.splitwise.split.type.PercentSplit;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCommand implements Command {
    private ExpenseManager expenseManager;

    public ExpenseCommand(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute(String[] commands) {
        String paidBy = commands[1];
        double amount = Double.parseDouble(commands[2]);
        int noOfUsers = Integer.parseInt(commands[3]);
        String expenseType = commands[4 + noOfUsers];
        List<Split> splits = new ArrayList<>();

        switch (expenseType) {
            case "EQUAL":
                for (int i = 0; i < noOfUsers; i++) {
                    splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
                }
                expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                break;
            case "EXACT":
                for (int i = 0; i < noOfUsers; i++) {
                    splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                }
                expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                break;
            case "PERCENT":
                for (int i = 0; i < noOfUsers; i++) {
                    splits.add(new PercentSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                }
                expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                break;
            default:
                System.out.println("Invalid expense type.");
        }
    }
}