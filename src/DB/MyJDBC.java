package DB;

import constants.CommonConstants;

import javax.xml.stream.events.EntityReference;
import java.sql.*;

public class MyJDBC {
    public static boolean register(String username, String password) {
        try {
            // first check if the username already exists in the db
            if (!checkUser(username)) {
                // connection to the db
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD);

                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME +
                                "(username , password)" + "VALUES(?, ?)"
                );

                // insert parameters in the insert query
                insertUser.setString(1, username );
                insertUser.setString(2, password );

                // update db with new user
                insertUser.executeUpdate();
                return true;

            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //
    //
    public static boolean checkUser(String username) {
        try {
            Connection connection = DriverManager.getConnection(
                    CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );

            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            // check if the result set is empty if yes => there is no data in there

            if (!resultSet.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    // validate login credentials by checking to see if username/password pair exists in the db
    public static boolean validateLogin( String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(
                                    CommonConstants.DB_URL,
                                    CommonConstants.DB_USERNAME,
                                    CommonConstants.DB_PASSWORD
                    );

            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? AND  PASSWORD = ?"
            );
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if ( !resultSet.isBeforeFirst() ) {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
