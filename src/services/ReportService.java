package services;

import model.Transaction;
import model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для генерации отчетов о финансах пользователя.
 */
public class ReportService {

    /**
     * Генерирует отчет о доходах и расходах пользователя.
     * @param user Пользователь, для которого создается отчет.
     * @param transactions Список всех транзакций пользователя.
     * @return Строка с отчетом.
     */
    public String generateReport(User user, List<Transaction> transactions) {
        double totalIncome = transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Map<String, Double> categorySummary = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)));

        StringBuilder report = new StringBuilder();
        report.append("Финансовый отчет для: ").append(user.getEmail()).append("\n");
        report.append("Общий доход: ").append(totalIncome).append("\n");
        report.append("Общие расходы: ").append(totalExpenses).append("\n");
        report.append("Анализ по категориям:\n");

        for (var entry : categorySummary.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return report.toString();
    }
}
