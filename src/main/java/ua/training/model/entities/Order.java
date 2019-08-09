package ua.training.model.entities;


import ua.training.model.entities.enums.Status;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long cost;
    private Street startStreet;
    private int startHouse;
    private Street endStreet;
    private int endHouse;
    private int distance;
    private double waitingTime;
    private double drivingTime;
    private LocalDateTime orderDate;
    private Type type;
    private Status status;
    private Long idCoupon;
    private User User;
    private Taxi Taxi;

    public void setId(Long id) {
        this.id = id;
    }

    public static Builder newBuilder() {
        return new Order().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Long getIdCoupon() {
        return idCoupon;
    }

    public Long getCost() {
        return cost;
    }

    public Taxi getTaxi() {
        return Taxi;
    }

    public int getEndHouse() {
        return endHouse;
    }

    public Street getEndStreet() {
        return endStreet;
    }

    public Type getType() {
        return type;
    }

    public int getStartHouse() {
        return startHouse;
    }

    public Street getStartStreet() {
        return startStreet;
    }

    public Status getStatus() {
        return status;
    }

    public double getDrivingTime() {
        return drivingTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public int getDistance() {
        return distance;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public ua.training.model.entities.User getUser() {
        return User;
    }

    public class Builder {
        private Builder() {
        }


        public Builder id(Long id) {
            Order.this.id = id;
            return this;
        }

        public Builder cost(Long cost) {
            Order.this.cost = cost;
            return this;
        }

        public Builder startStreet(Street startStreet) {
            Order.this.startStreet = startStreet;
            return this;
        }

        public Builder startHouse(int startHouse) {
            Order.this.startHouse = startHouse;
            return this;
        }

        public Builder endStreet(Street endStreet) {
            Order.this.endStreet = endStreet;
            return this;
        }

        public Builder endHouse(int endHouse) {
            Order.this.endHouse = endHouse;
            return this;
        }

        public Builder distance(int distance) {
            Order.this.distance = distance;
            return this;
        }

        public Builder waitingTime(double waitingTime) {
            Order.this.waitingTime = waitingTime;
            return this;
        }

        public Builder drivingTime(double drivingTime) {
            Order.this.drivingTime = drivingTime;
            return this;
        }

        public Builder orderDate(LocalDateTime orderDate) {
            Order.this.orderDate = orderDate;
            return this;
        }

        public Builder type(Type type) {
            Order.this.type = type;
            return this;
        }

        public Builder status(Status status) {
            Order.this.status = status;
            return this;
        }

        public Builder idCoupon(Long idCoupon) {
            Order.this.idCoupon = idCoupon;
            return this;
        }

        public Builder User(User idUser) {
            Order.this.User = idUser;
            return this;
        }

        public Builder Taxi(Taxi idTaxi) {
            Order.this.Taxi = idTaxi;
            return this;
        }

        public Order build() {
            return Order.this;
        }

    }

}
