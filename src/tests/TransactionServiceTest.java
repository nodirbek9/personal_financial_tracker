package tests;

import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TransactionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionServiceTest {
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    void testAddTransaction() {
        transactionService.addTransaction("Income", 500, "network", "For internet");
        List<Transaction> transactions = transactionService.getUserTransactions(1);
        assertThat(transactions).hasSize(1);
    }

    @Test
    void testDeleteTransaction() {
        transactionService.addTransaction("Expense", 200, "network", "For internet");
        List<Transaction> transactions = transactionService.getUserTransactions(1);
        int transactionId = transactions.get(0).getId();

        transactionService.deleteTransaction(transactionId);
        assertThat(transactionService.getUserTransactions(1)).isEmpty();
    }
}
