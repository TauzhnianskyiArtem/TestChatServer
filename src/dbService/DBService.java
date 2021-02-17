package dbService;

import dbService.dataSets.UsersDataSet;

public interface DBService {

    UsersDataSet getUser(long id) throws DBException;

    UsersDataSet getUserByLogin(String login) throws DBException;

    long addUser(String login, String password) throws DBException;

    void close();
}
