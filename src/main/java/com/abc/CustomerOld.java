package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class CustomerOld {
    private String name;
    private List<AccountOld> accounts;

    public CustomerOld(String name) {
        this.name = name;
        this.accounts = new ArrayList<AccountOld>();
    }

    public String getName() {
        return name;
    }

    public CustomerOld openAccount(AccountOld account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (AccountOld a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (AccountOld a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(AccountOld a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case AccountOld.CHECKING:
                s += "Checking Account\n";
                break;
            case AccountOld.SAVINGS:
                s += "Savings Account\n";
                break;
            case AccountOld.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (TransactionOld t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}