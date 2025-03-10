package main;

import model.Goal;
import model.Transaction;
import model.User;
import services.*;

import java.util.List;
import java.util.Scanner;

// Главный класс
public class FinanceApp {
    private static FinanceStatistics transactionService;
    static AdminService adminService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        UserService userService = new UserService();
        BudgetService budgetService = new BudgetService();
        GoalService goalService = new GoalService();
        NotificationService notificationService = new NotificationService();

        User currentUser = null;

        while (true) {
            System.out.println("1. Регистрация\n2. Вход\n3. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                System.out.println("Введите имя:");
                String name = scanner.nextLine();
                currentUser = authService.register(email, password, name);
            } else if (choice == 2) {
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                currentUser = authService.login(email, password);

                if (currentUser != null) {
                    while (true) {
                        System.out.println("1. Управление пользователем\n" +
                                "2. Транзакции\n" +
                                "3. Бюджет\n" +
                                "4. Цели\n" +
                                "5. Аналитика\n" +
                                "6. Администрирование\n" +
                                "7. Выход");
                        int action = scanner.nextInt();
                        scanner.nextLine();

                        if (action == 1) {
                            System.out.println("1. Обновить профиль\n2. Удалить аккаунт");
                            int userAction = scanner.nextInt();
                            scanner.nextLine();
                            if (userAction == 1) {
                                System.out.println("Введите новый email:");
                                String newEmail = scanner.nextLine();
                                System.out.println("Введите новый пароль:");
                                String newPassword = scanner.nextLine();
                                System.out.println("Введите новое имя:");
                                String newName = scanner.nextLine();
                                userService.updateUser(currentUser.getId(), newEmail, newPassword, newName);
                            } else if (userAction == 2) {
                                userService.deleteUser(currentUser.getId());
                                currentUser = null;
                                break;
                            }
                        } else if (action == 2) {
                            // Операции с транзакциями
                            if (currentUser != null) {
                                System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
                            }
                            TransactionService fm = new TransactionService(currentUser);
                            while (true) {
                                System.out.println("1. Добавить транзакцию\n" +
                                        "2. Редактировать транзакцию" +
                                        "\n3. Удалить транзакцию" +
                                        "\n4. Просмотреть транзакции" +
                                        "\n5. Выйти");
                                int choice1 = scanner.nextInt();
                                scanner.nextLine();

                                if (choice1 == 1) {
                                    System.out.println("Тип (доход/расход):");
                                    String type = scanner.nextLine();
                                    System.out.println("Сумма:");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Категория:");
                                    String category = scanner.nextLine();
                                    System.out.println("Описание:");
                                    String description = scanner.nextLine();
                                    fm.addTransaction(type, amount, category, description);
                                } else if (choice1 == 2) {
                                    System.out.println("Введите ID транзакции:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Новая сумма:");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Новая категория:");
                                    String category = scanner.nextLine();
                                    System.out.println("Новое описание:");
                                    String description = scanner.nextLine();
                                    fm.updateTransaction(id, amount, category, description);
                                } else if (choice1 == 3) {
                                    System.out.println("Введите ID транзакции:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    fm.deleteTransaction(id);
                                } else if (choice1 == 4) {
                                    System.out.println("Фильтр по типу (доход/расход, оставьте пустым для всех):");
                                    String type = scanner.nextLine();
                                    System.out.println("Фильтр по категории (оставьте пустым для всех):");
                                    String category = scanner.nextLine();
                                    fm.showTransactions(type.isEmpty() ? null : type, category.isEmpty() ? null : category, null);
                                } else {
                                    break;
                                }
                            }
                        } else if (action == 3) {
                            // Управление бюджетом
                            System.out.println("1. Установить месячный бюджет\n2. Проверить превышение бюджета");
                            int budgetAction = scanner.nextInt();
                            scanner.nextLine();

                            if (budgetAction == 1) {
                                System.out.println("Введите сумму бюджета на месяц:");
                                double budgetAmount = scanner.nextDouble();
                                scanner.nextLine();
                                budgetService.setMonthlyBudget(budgetAmount);
                            } else if (budgetAction == 2) {
                                boolean exceeded = budgetService.checkBudgetExceeded();
                                if (exceeded) {
                                    System.out.println("Вы превысили месячный бюджет!");
                                    notificationService.sendBudgetLimitAlert(currentUser);
                                } else {
                                    System.out.println("Вы в пределах бюджета.");
                                }
                            }
                        } else if (action == 4) {
                            System.out.println("1. Установить новую цель\n2. Обновить прогресс\n3. Показать цели");
                            int goalAction = scanner.nextInt();
                            scanner.nextLine();

                            if (goalAction == 1) {
                                System.out.println("Введите сумму для накопления:");
                                double goalAmount = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Введите описание цели:");
                                String description = scanner.nextLine();
                                goalService.setGoal(currentUser, goalAmount, description);
                            } else if (goalAction == 2) {
                                System.out.println("Введите сумму внесенного вклада:");
                                double deposit = scanner.nextDouble();
                                scanner.nextLine();
                                goalService.updateGoalProgress(currentUser, deposit);
                            } else if (goalAction == 3) {
                                List<Goal> userGoals = goalService.getUserGoals(currentUser);
                                if (userGoals.isEmpty()) {
                                    System.out.println("У вас нет целей.");
                                } else {
                                    for (Goal goal : userGoals) {
                                        System.out.println("Цель: " + goal.getDescription() + " | Прогресс: " + goal.getProgress() + "/" + goal.getTargetAmount());
                                    }
                                }
                            }
                        } else if (action == 5) {
                            System.out.println("1. Текущий баланс");
                            System.out.println("2. Доходы и расходы за период");
                            System.out.println("3. Анализ расходов по категориям");
                            System.out.println("4. Сформировать отчёт");
                            int analyticsAction = scanner.nextInt();
                            scanner.nextLine();

                            if (analyticsAction == 1) {
                                double balance = transactionService.calculateBalance(currentUser);
                                System.out.println("Текущий баланс: " + balance);
                            } else if (analyticsAction == 2) {
                                System.out.println("Введите начальную дату (yyyy-MM-dd):");
                                String startDate = scanner.nextLine();
                                System.out.println("Введите конечную дату (yyyy-MM-dd):");
                                String endDate = scanner.nextLine();
                                System.out.println("Доход: ");
                                System.out.println("Расход: ");
                            } else if (analyticsAction == 3) {
                                transactionService.analyzeExpensesByCategory();
                            } else if (analyticsAction == 4) {
                                transactionService.generateFinancialReport(currentUser);
                            }
                        } else if (action == 6) {
                            if (!currentUser.isAdmin()) { // Проверяем, что пользователь - админ
                                System.out.println("У вас нет прав для выполнения этой операции.");
                                continue;
                            }

                            System.out.println("1. Просмотр всех пользователей\n2. Просмотр транзакций пользователя\n3. Блокировка пользователя\n4. Удаление пользователя");
                            int adminAction = scanner.nextInt();
                            scanner.nextLine();


                            if (adminAction == 1) {
                                List<User> users = adminService.getAllUsers();
                                users.forEach(user -> System.out.println("ID: " + user.getId() + ", Email: " + user.getEmail() + ", Имя: " + user.getName()));
                            } else if (adminAction == 2) {
                                System.out.println("Введите ID пользователя:");
                                String userId = scanner.nextLine();
                                List<Transaction> transactions = adminService.getUserTransactions(userId);
                                transactions.forEach(tx -> System.out.println(tx));
                            } else if (adminAction == 3) {
                                System.out.println("Введите ID пользователя для блокировки:");
                                String userId = scanner.nextLine();
                                adminService.blockUser(userId);
                                System.out.println("Пользователь заблокирован.");
                            } else if (adminAction == 4) {
                                System.out.println("Введите ID пользователя для удаления:");
                                String userId = scanner.nextLine();
                                adminService.deleteUser(userId);
                                System.out.println("Пользователь удален.");
                            }
                        } else {
                                break;
                            }
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Вы ввели неправилное содержание!");
            }
        }
        scanner.close();
    }
}