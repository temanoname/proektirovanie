package org.example.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Supplier supplier; // Поставщик для транзакции

    private int quantity;
    private double cost;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TranType type;

    // Конструкторы
    public Transaction() {}

    public Transaction(Product product, Supplier supplier, int quantity, double cost, LocalDate date, TranType type) {
        this.product = product;
        this.supplier = supplier;
        this.quantity = quantity;
        this.cost = cost;
        this.date = date;
        this.type = type;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TranType getType() {
        return type;
    }

    public void setType(TranType type) {
        this.type = type;
    }
}
