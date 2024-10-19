package org.example.splitwise.user;

import org.example.splitwise.expense.manager.ExpenseManager;

public class UserInitializer {
    public static void initializeUsers(ExpenseManager expenseManager) {
        expenseManager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));
    }
}
