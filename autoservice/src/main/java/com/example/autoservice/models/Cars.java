package com.example.autoservice.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private int year;
    @Column
    private Double engine_volume;
    @Column
    private int mileage;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users users;

    public Cars() {
    }

    public Long getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public Double getEngine_volume() {
        return this.engine_volume;
    }

    public int getMileage() {
        return this.mileage;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEngine_volume(Double engine_volume) {
        this.engine_volume = engine_volume;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Cars other)) return false;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        final Object this$brand = this.getBrand();
        final Object other$brand = other.getBrand();
        if (!Objects.equals(this$brand, other$brand)) return false;
        final Object this$model = this.getModel();
        final Object other$model = other.getModel();
        if (!Objects.equals(this$model, other$model)) return false;
        if (this.getYear() != other.getYear()) return false;
        final Object this$engine_volume = this.getEngine_volume();
        final Object other$engine_volume = other.getEngine_volume();
        if (!Objects.equals(this$engine_volume, other$engine_volume))
            return false;
        if (this.getMileage() != other.getMileage()) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        return Objects.equals(this$users, other$users);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Cars;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $brand = this.getBrand();
        result = result * PRIME + ($brand == null ? 43 : $brand.hashCode());
        final Object $model = this.getModel();
        result = result * PRIME + ($model == null ? 43 : $model.hashCode());
        result = result * PRIME + this.getYear();
        final Object $engine_volume = this.getEngine_volume();
        result = result * PRIME + ($engine_volume == null ? 43 : $engine_volume.hashCode());
        result = result * PRIME + this.getMileage();
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        return result;
    }

    public String toString() {
        return "Cars(id=" + this.getId() + ", brand=" + this.getBrand() + ", model=" + this.getModel() + ", year=" + this.getYear() + ", engine_volume=" + this.getEngine_volume() + ", mileage=" + this.getMileage() + ", users=" + this.getUsers() + ")";
    }
}
