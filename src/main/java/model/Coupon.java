package model;

public class Coupon {
    private int code;
    private int percentage;

    public Coupon(int code, int percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public int getCode() {
        return code;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Coupon( " + code + " mit " + percentage + "%)";
    }
}
