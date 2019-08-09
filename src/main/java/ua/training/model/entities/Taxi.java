package ua.training.model.entities;


import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

public class Taxi {
    private Long id;
    private Street locationStreet;
    private int locationHouse;
    private String driverName;
    private String carNumber;
    private boolean busy;
    private Type type;

    public Type getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public boolean getBusy() {
        return busy;
    }

    public static Builder newBuilder() {
        return new Taxi().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }

    public int getLocationHouse() {
        return locationHouse;
    }

    public Street getLocationStreet() {
        return locationStreet;
    }

    public class Builder {
        private Builder() {
        }


        public Builder id(Long id) {
            Taxi.this.id = id;
            return this;
        }


        public Builder locationStreet(Street locationStreet) {
            Taxi.this.locationStreet = locationStreet;
            return this;
        }

        public Builder locationHouse(int locationHouse) {
            Taxi.this.locationHouse = locationHouse;
            return this;
        }

        public Builder driverName(String driverName) {
            Taxi.this.driverName = driverName;
            return this;
        }

        public Builder carNumber(String carNumber) {
            Taxi.this.carNumber = carNumber;
            return this;
        }

        public Builder busy(boolean busy) {
            Taxi.this.busy = busy;
            return this;
        }

        public Builder type(Type type) {
            Taxi.this.type = type;
            return this;
        }

        public Taxi build() {
            return Taxi.this;
        }

    }
}
