package ua.training.model.entities;

public class Coupon {
    private Long id;
    private double discountPercent;
    private Order order;
    private User User;

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public User getUser() {
        return User;
    }

    public Long getId() {
        return id;
    }

    public static Builder newBuilder() {
        return new Coupon().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }


    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Coupon.this.id = id;
            return this;
        }

        public Builder discountPercent(double discountPercent) {
            Coupon.this.discountPercent = discountPercent;
            return this;
        }

        public Builder order(Order order) {
            Coupon.this.order = order;
            return this;
        }

        public Builder user(User user) {
            Coupon.this.User = user;
            return this;
        }


        public Coupon build() {
            return Coupon.this;
        }
    }


}
