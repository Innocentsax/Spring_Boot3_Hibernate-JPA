package dev.Innocent.SpringAOP.DAO;

import dev.Innocent.SpringAOP.Model.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();
}
