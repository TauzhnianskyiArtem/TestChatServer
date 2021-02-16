package account;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;


public class AccountService {
    private DBService service;

    public AccountService() {
        this.service = new DBService();
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
    public boolean isCorrectUsersByLogin(String login, String password) {
        UsersDataSet profile = getUserByLogin(login);
        if (profile == null || !profile.getPassword().equals(password))
            return false;
        return true;
    }
    public boolean isCorrectUsersBySessionId(String sessionId) {
        if (getUserBySessionId(sessionId) == null)
            return false;
        return true;
    }
    private UsersDataSet getUserByLogin(String login) {
        try {
            return service.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            service.close();
        }
        return null;
    }
    private UsersDataSet getUserBySessionId(String sessionId) { return null;}
}


