package services;

import model.Transaction;

import java.util.*;

public class TransactionService {
    private final Map<String, List<Transaction>> transactions = new HashMap<>();

    // Добавление транзакции
    public void addTransaction(String userEmail, Transaction transaction) {
        transactions.computeIfAbsent(userEmail, k -> new ArrayList<>()).add(transaction);
        System.out.println("Транзакция добавлена.");
    }

    // Удаление транзакции по ID
    public boolean deleteTransaction(String userEmail, int id) {
        List<Transaction> userTransactions = transactions.get(userEmail);
        if (userTransactions != null) {
            System.out.println("Транзакция удалена");
            return userTransactions.removeIf(t -> t.getId().equals(id));

        }
        return false;
    }

    // Обновление транзакции
    public boolean updateTransaction(String userEmail, int id, double newAmount, String newCategory, String newDescription) {
        List<Transaction> userTransactions = transactions.get(userEmail);
        if (userTransactions != null) {
            for (Transaction t : userTransactions) {
                if (t.getId().equals(id)) {
                    t.setAmount(newAmount);
                    t.setCategory(newCategory);
                    t.setDescription(newDescription);
                    System.out.println("Транзакция обновлена.");
                    return true;
                }
            }
        }
        System.out.println("Транзакция не найдена.");
        return false;
    }

    // Получение транзакций пользователя без getUserEmail()
    public List<Transaction> getUserTransactions(String userEmail) {
        return transactions.getOrDefault(userEmail, Collections.emptyList());
    }

    }