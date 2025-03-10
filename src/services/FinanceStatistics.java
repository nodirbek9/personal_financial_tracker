package services;

import model.Transaction;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceStatistics {
    private TransactionService financeService;

    private List<Transaction> transactions;

    public FinanceStatistics(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Подсчет текущего баланса
    public double calculateBalance(User user) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("доход")) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }
//    public double getTotalIncome(String userId, Date startDate, Date endDate) {
//        return transactions.stream()
//                .filter(t -> t.getUserId().equals(userId) && t.getType().equals("доход") &&
//                        t.getDate().compareTo(startDate) >= 0 && t.getDate().compareTo(endDate) <= 0)
//                .mapToDouble(Transaction::getAmount)
//                .sum();
//    }
    // Анализ расходов по категориям
    public Map<String, Double> analyzeExpensesByCategory() {
        Map<String, Double> categoryExpenses = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("расход")) {
                categoryExpenses.put(transaction.getCategory(),
                        categoryExpenses.getOrDefault(transaction.getCategory(), 0.0) + transaction.getAmount());
            }
        }
        return categoryExpenses;
    }

    // Формирование отчета по финансовому состоянию
    public void generateFinancialReport(User user) {
        double balance = calculateBalance(user);
        System.out.println("===== Финансовый отчет =====");
        System.out.println("Текущий баланс: " + balance);
        analyzeExpensesByCategory();
    }
}
