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
        Transaction transaction = new Transaction("1", 500.0, "Rasxod", "produkt", true);
        transactionService.addTransaction("Income", transaction);
        List<Transaction> transactions = transactionService.getUserTransactions("example@gmail.com");
        assertThat(transactions).hasSize(0);
    }

    @Test
    void testDeleteTransaction() {
        Transaction transaction = new Transaction("1", 500.0, "Rasxod", "produkt", true);
        transactionService.addTransaction("Expense", transaction);
        List<Transaction> transactions = transactionService.getUserTransactions("example@com");


        transactionService.deleteTransaction("example@com", 0);
        assertThat(transactionService.getUserTransactions("example@com")).isEmpty();
    }
}
