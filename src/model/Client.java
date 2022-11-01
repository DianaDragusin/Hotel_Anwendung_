package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<Coupon> couponList;

    public Client(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
        this.couponList = new ArrayList<>();
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    private String Coupons ()
    {
        String couponString = "";
        for (Coupon c : this.getCouponList() )
        {
            couponString = couponString.concat("\n");
            couponString = couponString.concat(c.toString());
        }

        return  couponString;
    }

    @Override
    public String toString() {
        return "Client (" + super.toString() + ", \nCoupons: " + Coupons() + " )";
    }

}
