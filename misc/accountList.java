package misc;

import java.util.ArrayList;
import java.util.stream.*;
import java.util.Iterator;

public class accountList {
    private ArrayList<Account> accounts = new ArrayList<>();


    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(String name) {
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }
        
        var remove = accounts.stream()
        .filter(a -> a.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Account not found!"));

        accounts.remove(remove);
        System.out.println("Account removed!");
    }

    public void showAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            Iterator<Account> listAccount = accounts.iterator();
            System.out.println("Accounts:");
            while (listAccount.hasNext()) {
                System.out.println(listAccount.next());
            }
        }
        
        
    }

}
