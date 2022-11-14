import model.Client;
import model.Coupon;
import repository.fileRepo.FileClientRepo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      Client c = new Client("Ana", "Maria","anamaria20","1234");
      List<Coupon> l = new ArrayList<>();
      Coupon cou = new Coupon(1234,20);
      Coupon cou2 = new Coupon(123434,60);
      Coupon cou3 = new Coupon(12834,90);

      l.add(cou); l.add(cou2); l.add(cou3);
      c.setCouponList(l);
      System.out.println(c);

      FileClientRepo fcr = new FileClientRepo();

    }
}