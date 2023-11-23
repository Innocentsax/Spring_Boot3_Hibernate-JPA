package dev.Innocent.SpringAOP.DAO.DAOImpl;

import dev.Innocent.SpringAOP.DAO.AccountDAO;
import dev.Innocent.SpringAOP.Model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ":  doWork");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ":  getName");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ":  setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ":  getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ":  setServiceCode");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // For academic purpose ... simulate an exception
        if(tripWire){
            throw new RuntimeException("No Going home for you");
        }

        List<Account> myAccounts = new ArrayList<>();

        // Create sample accounts
        Account temp1 = new Account("Innocent Udo", "Continental Tech Lead");
        Account temp2 = new Account("Ekemini mbok", "Student");
        Account temp3 = new Account("Ayo Emma", "Teacher");

        // Add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }
}
