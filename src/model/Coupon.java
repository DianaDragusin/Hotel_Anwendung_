package model;

public class Coupon {


    private int code;
    private int percentage;
    public Coupon(int code, int percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getCode() {
        return code;
    }

    public int getPercentage() {
        return percentage;
    }
    public String toString()
    {

        return "model.Coupon( " + this.getCode() + " mit " + this.getPercentage() + "%)";
    }
}
