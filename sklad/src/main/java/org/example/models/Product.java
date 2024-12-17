package org.example.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Название товара
    private LocalDate manufactureDate; // Дата изготовления
    private int shelfLife; // Срок годности в днях
    private double price; // Цена за единицу
    private int quantity; // Количество на складе

    // Конструкторы
    public Product() {}

    public Product(String name, LocalDate manufactureDate, int shelfLife, double price, int quantity) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.shelfLife = shelfLife;
        this.price = price;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Метод для получения даты истечения срока годности
    public LocalDate getExpirationDate() {
        return manufactureDate.plusDays(shelfLife);
    }

    // Метод для проверки, что товар испортится в ближайшие "days" дней
    public boolean isExpiringSoon(int days) {
        LocalDate expirationDate = getExpirationDate();
        return expirationDate.isBefore(LocalDate.now().plusDays(days));
    }
}
