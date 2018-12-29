import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String username = "bolitao";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC", "bolitao", "bolitao");
            Statement statement = connection.createStatement();
            String queryUserInfo;
            String createUesr;
            String checkIsRegisted;
            String checkPassword;
            queryUserInfo = "select * from user where userCustomID = '" + username + "'";
            ResultSet userInfoResult = statement.executeQuery(queryUserInfo);
            if (userInfoResult.next() != false) {
                System.out.println("not empty");
            } else {
                System.out.println("empty");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
