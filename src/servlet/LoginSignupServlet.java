package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginSignupServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username + "\npassword:" + password);
        PrintWriter writer = resp.getWriter();
        ResultSet userInfoResult = null;
        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false", "bolitao", "bolitao");
            statement = connection.createStatement();
            String queryUserInfo;
            String createUesr;
            String checkIsRegisted;
            String checkPassword;
            queryUserInfo = "select * from user where userCustomID = '" + username + "'";
            createUesr = "INSERT INTO `user`(userCustomID, registrationDate, userPassword, isEnable) VALUES ('" + username + "', CURRENT_TIMESTAMP, '" + password + "', 1)";
            userInfoResult = statement.executeQuery(queryUserInfo);
            if (username != null && (!"".equals(username))) {
                if (userInfoResult.next() != false) {
                    if (username.equals(userInfoResult.getString("userPassword"))) {
                        writer.print("success");
                        System.out.println("SUCCESS");
                    }
                } else {
                    statement.executeUpdate(createUesr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (userInfoResult != null) {
                try {
                    userInfoResult.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
