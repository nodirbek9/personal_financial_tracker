package services;

import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private User currentUser;
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionService(User user) {
        this.currentUser = user;
    }

    public TransactionService() {

    }

    public void addTransaction(String type, double amount, String category, String description) {
        Transaction transaction = new Transaction(amount, type, category, description);
        transactions.add(transaction);
        System.out.println("Транзакция добавлена: " + transaction);
    }

    public void updateTransaction(int id, double amount, String category, String description){
        for (Transaction t: transactions) {
            if (t.getId() == id){
                t.updateTransaction(amount, category, description);
                System.out.println("Транзакция обновлена: " + t);
                return;
            }
        }
        System.out.println("Транзакция не найдена!");
    }

    public void deleteTransaction(int id){
        transactions.removeIf(transaction -> transaction.getId() == id);
        System.out.println("Транзакция удалена.");
    }

    public void showTransactions(String filterType, String filterCategory, Date filterDate) {
        for (Transaction t : transactions) {
            if ((filterType == null || t.getType().equalsIgnoreCase(filterType)) &&
                (filterCategory == null || t.getCategory().equalsIgnoreCase(filterCategory)) &&
                (filterDate == null || t.getDate().equals(filterDate))){
                System.out.println(t);
            }
        }
    }

    // Метод для получения списка транзакций пользователя
    public List<Transaction> getUserTransactions(int userId) {
        return transactions.stream()
                .filter(transaction -> transaction.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}