package account;

import dbService.DBException;
import dbService.DBService;
import dbService.DBServiceImp;
import dbService.dataSets.UsersDataSet;


public class AccountServiceImp implements AccountService {
    private DBService service;

    public AccountServiceImp() {
        this.service = new DBServiceImp();
    }

    public void addNewUser(String login, String password) {
        try {
            service.addUser(login, password);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            service.close();
        }
    }


    public UsersDataSet getUserByLogin(String login) {
        try {
            return service.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            service.close();
        }
        return null;
    }

}


