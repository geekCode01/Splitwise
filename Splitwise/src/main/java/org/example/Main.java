package org.example;

import org.example.splitwise.command.CommandHandler;
import org.example.splitwise.expense.manager.ExpenseManager;
import org.example.splitwise.user.UserInitializer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        UserInitializer.initializeUsers(expenseManager);

        CommandHandler commandHandler = new CommandHandler(expenseManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];
            if (commandType.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting the application.");
                break;
            }

            commandHandler.handleCommand(commandType, commands);
        }
    }
}