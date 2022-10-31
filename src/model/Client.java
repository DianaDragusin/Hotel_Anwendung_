package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<Coupon> couponList= new ArrayList<>();

    public Client(String firstName, String lastName) {
        super(firstName, lastName);
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
   public String toStringClient()
   {
       return "model.Client (" + Person.getId() + ", " + getFirstName() + ", " + getLastName() + ", \nCoupons: " + Coupons() + " )";
   }
}
