package org.example.service;

import org.example.models.Product;
import org.example.models.TranType;
import org.example.models.Transaction;
import org.example.repository.ProductRepository;
import org.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public ReportService(TransactionRepository transactionRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    // Генерация отчета о продажах за период
    public String generateSalesReport(LocalDate start, LocalDate end) {
        List<Transaction> sales = transactionRepository.findByDateBetweenAndType(start, end, TranType.SALE);
        StringBuilder report = new StringBuilder("Отчет о продажах:\n");

        sales.forEach(sale -> report.append(String.format(
                "Товар: %s, Количество: %d, Выручка: %.2f\n",
                sale.getProduct().getName(),
                sale.getQuantity(),
                sale.getCost()
        )));

        return report.toString();
    }

    // Генерация отчета о прибыли с учетом издержек
    public String generateProfitReport(LocalDate start, LocalDate end) {
        List<Transaction> sales = transactionRepository.findByDateBetweenAndType(start, end, TranType.SALE);

        double totalProfit = sales.stream()
                .mapToDouble(sale -> {
                    double purchaseCost = sale.getProduct().getPrice() * sale.getQuantity();
                    return sale.getCost() - purchaseCost;
                })
                .sum();

        return String.format("Общая прибыль за период с %s по %s: %.2f", start, end, totalProfit);
    }

    // Список товаров, которые испортятся в ближайшие N дней
    public List<Product> listExpiringProducts(int days) {
        LocalDate thresholdDate = LocalDate.now().plusDays(days);

        return productRepository.findAll().stream()
                .filter(product -> product.getExpirationDate().isBefore(thresholdDate))
                .collect(Collectors.toList());
    }

    // Список списанных товаров по причине истечения срока годности
    public String generateExpiredProductsReport(LocalDate start, LocalDate end) {
        List<Transaction> expiredTransactions = transactionRepository.findByDateBetweenAndType(start, end, TranType.EXPIRATION);

        StringBuilder report = new StringBuilder("Списанные товары по истечению срока годности:\n");
        expiredTransactions.forEach(transaction -> report.append(String.format(
                "Товар: %s, Количество: %d, Дата списания: %s\n",
                transaction.getProduct().getName(),
                transaction.getQuantity(),
                transaction.getDate()
        )));

        return report.toString();
    }
}
