package enums;

public enum Vehicle {
    BIKE("bike"),
    CAR("car"),
    ELECTRIC("electric");

    private String value;

    Vehicle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
