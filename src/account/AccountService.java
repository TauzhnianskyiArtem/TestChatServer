package account;

import dbService.dataSets.UsersDataSet;

public interface AccountService {

    void addNewUser(String login, String password);

    UsersDataSet getUserByLogin(String login);

}
