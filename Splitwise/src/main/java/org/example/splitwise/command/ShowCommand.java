package org.example.splitwise.command;

import org.example.splitwise.expense.manager.ExpenseManager;

public class ShowCommand implements Command {
    private ExpenseManager expenseManager;

    public ShowCommand(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            expenseManager.showBalances();
        } else {
            expenseManager.showBalance(commands[1]);
        }
    }
}
