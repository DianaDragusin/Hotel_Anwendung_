package views;

import service.CleanerController;

public class CleanerView {
    private CleanerController cleanercontroller;
    private void registerStatus(String firstName,String lastName,String username,String password,int salary)
    {
        String status = cleanercontroller.register(firstName, lastName,username,password);
        System.out.println(status);
    }
    private void loginStatus(String username, String password)
    {
        boolean status = cleanercontroller.login(username,password);
        if (status )
        {
            System.out.println("Cleaner " + username + " is logged in" );
        }else {
            System.out.println("Invalid credentials" );
        }


    }
    private void changePasswordStatus(String username,String password)
    {
        String status = cleanercontroller.changePassword(username,password);
        System.out.println(status);
    }
    private  void changeDetailsStatus (String newfirstName,String newlastName,String username)
    {
        String status = cleanercontroller.changeDetails(newfirstName, newlastName,username);
        System.out.println(status);
    }
}
