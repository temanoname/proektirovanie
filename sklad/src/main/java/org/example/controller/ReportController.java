package org.example.controller;

import org.example.models.Product;
import org.example.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Генерация отчета о продажах за период
    @GetMapping("/sales")
    public ResponseEntity<String> getSalesReport(@RequestParam("start") String startDate,
                                                 @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        String report = reportService.generateSalesReport(start, end);
        return ResponseEntity.ok(report);
    }

    // Генерация отчета о прибыли за период
    @GetMapping("/profit")
    public ResponseEntity<String> getProfitReport(@RequestParam("start") String startDate,
                                                  @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        String report = reportService.generateProfitReport(start, end);
        return ResponseEntity.ok(report);
    }

    // Список товаров, которые скоро испортятся (в ближайшие N дней)
    @GetMapping("/expiring-products")
    public ResponseEntity<List<Product>> getExpiringProducts(@RequestParam("days") int days) {
        List<Product> products = reportService.listExpiringProducts(days);
        return ResponseEntity.ok(products);
    }

    // Генерация отчета о списанных товарах за период
    @GetMapping("/expired-products")
    public ResponseEntity<String> getExpiredProductsReport(@RequestParam("start") String startDate,
                                                           @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        String report = reportService.generateExpiredProductsReport(start, end);
        return ResponseEntity.ok(report);
    }
}
