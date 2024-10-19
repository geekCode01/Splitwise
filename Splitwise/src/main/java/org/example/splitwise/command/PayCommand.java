package org.example.splitwise.command;

import org.example.splitwise.expense.manager.ExpenseManager;

public class PayCommand implements Command {
    private ExpenseManager expenseManager;

    public PayCommand(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute(String[] commands) {
        String payer = commands[1];
        String payee = commands[2];
        double amount = Double.parseDouble(commands[3]);
        expenseManager.pay(payer, payee, amount);
    }
}
