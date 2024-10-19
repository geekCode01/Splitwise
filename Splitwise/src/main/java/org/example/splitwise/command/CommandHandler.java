package org.example.splitwise.command;

import org.example.splitwise.expense.manager.ExpenseManager;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler(ExpenseManager expenseManager) {
        commandMap.put("SHOW", new ShowCommand(expenseManager));
        commandMap.put("PAY", new PayCommand(expenseManager));
        commandMap.put("EXPENSE", new ExpenseCommand(expenseManager));
    }

    public void handleCommand(String commandType, String[] commands) {
        Command command = commandMap.get(commandType); // Use commandMap instead of commands
        if (command != null) {
            command.execute(commands);
        } else {
            System.out.println("Invalid command. Please try again.");
        }
    }
}
