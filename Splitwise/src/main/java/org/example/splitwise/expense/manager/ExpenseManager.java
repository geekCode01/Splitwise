package org.example.splitwise.expense.manager;

import org.example.splitwise.expense.Expense;
import org.example.splitwise.expense.ExpenseMetadata;
import org.example.splitwise.expense.ExpenseService;
import org.example.splitwise.expense.ExpenseType;
import org.example.splitwise.split.Split;
import org.example.splitwise.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    public Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetadata);
        if (expense.validate()) {
            expenses.add(expense);
            for (Split split : expense.getSplits()) {
                String paidTo = split.getUser().getId();
                Map<String, Double> balances = balanceSheet.get(paidBy);
                balances.put(paidTo, balances.getOrDefault(paidTo, 0.0) + split.getAmount());

                balances = balanceSheet.get(paidTo);
                balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) - split.getAmount());
            }
        } else {
            System.out.println("Invalid expense");
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() != 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

    public void pay(String paidBy, String paidTo, double amount) {
        if (!userMap.containsKey(paidBy) || !userMap.containsKey(paidTo)) {
            System.out.println("Invalid user IDs provided.");
            return;
        }

        Map<String, Double> balancesPaidBy = balanceSheet.get(paidBy);
        Map<String, Double> balancesPaidTo = balanceSheet.get(paidTo);

        balancesPaidBy.put(paidTo, balancesPaidBy.getOrDefault(paidTo, 0.0) + amount);
        balancesPaidTo.put(paidBy, balancesPaidTo.getOrDefault(paidBy, 0.0) - amount);

        System.out.println(userMap.get(paidBy).getName() + " paid " + amount + " to " + userMap.get(paidTo).getName());

        if (balancesPaidBy.get(paidTo) == 0.0 && balancesPaidTo.get(paidBy) == 0.0) {
            System.out.println("All balances between " + userMap.get(paidBy).getName() + " and " + userMap.get(paidTo).getName() + " are clear.");
        }
    }
}