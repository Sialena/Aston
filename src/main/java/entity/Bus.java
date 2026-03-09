package entity;

import java.util.Objects;

public class Bus implements Comparable<Bus>{
    private String busNumber;
    //Так как тип модели поменялся на ENUM - надо хорошенько пересмотреть чтобы работали хэшкоды, иквалсы, и т.д.
    private Models model;
    private int mileage;

    public Bus(Builder builder) {
        this.busNumber = builder.busNumber;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public Bus(String busNumber, Models model, int mileage) {
        this.busNumber = busNumber;
        this.model = model;
        this.mileage = mileage;
    }

    public Bus() {}

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Models getModel() {
        return model;
    }

    public void setModel(Models model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busNumber='" + busNumber + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return mileage == bus.mileage && Objects.equals(busNumber, bus.busNumber) && Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busNumber, model, mileage);
    }

    @Override
    public int compareTo(Bus o) {
        int numberCompare = this.busNumber.compareTo(o.busNumber);
        if (numberCompare != 0) return numberCompare;

        int modelCompare = this.model.compareTo(o.model);
        if (modelCompare != 0) return modelCompare;

        return Integer.compare(this.mileage, o.mileage);
    }

    public static class Builder {
        private String busNumber = "";
        private Models model = null;
        private int mileage = 0;

        public Builder busNumber(String busNumber) {
            this.busNumber = busNumber;
            return this;
        }

        public Builder model(Models model) {
            this.model = model;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
