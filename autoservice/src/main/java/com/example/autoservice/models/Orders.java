package com.example.autoservice.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services services;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars cars;

    @Column
    @DateTimeFormat(pattern = "HH:mm") // Для времени, формат: часы:минуты
    private LocalTime order_time;

    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy") // Для даты, формат: день/месяц/год
    private LocalDate order_date;

    // Добавляем новое поле, которое будет отвечать за выполнение заказа
    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public Services getServices() {
        return services;
    }

    public Cars getCars() {
        return cars;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public LocalTime getOrder_time() {
        return order_time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public void setOrder_time(LocalTime order_time) {
        this.order_time = order_time;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Orders)) return false;
        final Orders other = (Orders) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$services = this.getServices();
        final Object other$services = other.getServices();
        if (this$services == null ? other$services != null : !this$services.equals(other$services)) return false;
        final Object this$cars = this.getCars();
        final Object other$cars = other.getCars();
        if (this$cars == null ? other$cars != null : !this$cars.equals(other$cars)) return false;
        final Object this$order_time = this.getOrder_time();
        final Object other$order_time = other.getOrder_time();
        if (this$order_time == null ? other$order_time != null : !this$order_time.equals(other$order_time))
            return false;
        final Object this$order_date = this.getOrder_date();
        final Object other$order_date = other.getOrder_date();
        if (this$order_date == null ? other$order_date != null : !this$order_date.equals(other$order_date))
            return false;
        if (this.isCompleted() != other.isCompleted()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Orders;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $services = this.getServices();
        result = result * PRIME + ($services == null ? 43 : $services.hashCode());
        final Object $cars = this.getCars();
        result = result * PRIME + ($cars == null ? 43 : $cars.hashCode());
        final Object $order_time = this.getOrder_time();
        result = result * PRIME + ($order_time == null ? 43 : $order_time.hashCode());
        final Object $order_date = this.getOrder_date();
        result = result * PRIME + ($order_date == null ? 43 : $order_date.hashCode());
        result = result * PRIME + (this.isCompleted() ? 79 : 97);
        return result;
    }
}
