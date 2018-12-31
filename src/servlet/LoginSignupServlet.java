package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author Boli Tao
 * @date 2018/12/29
 */
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL" +
                    "=false", "bolitao", "bolitao");
            statement = connection.createStatement();
            String queryUserInfo;
            String createUesr;
            String checkIsRegisted;
            String checkPassword;
            // SQL 语句：从数据库查询传入帐号的信息
            queryUserInfo = "select * from user where userCustomID = '" + username + "'";
            // SQL 语句：新建用户
            createUesr = "INSERT INTO `user`(userCustomID, registrationDate, userPassword, isEnable) VALUES ('" +
                    username + "', CURRENT_TIMESTAMP, '" + password + "', 1)";
            // 查询用户信息
            userInfoResult = statement.executeQuery(queryUserInfo);
            if (username != null && (!"".equals(username)) && password != null && (!"".equals(password))) {
                // 如果该账号已在数据库中有记录，则验证其密码。否则新建帐号
                if (userInfoResult.next() != false) {
                    if (password.equals(userInfoResult.getString("userPassword"))) {
                        // 服务器返回 LOGIN_SUCCESS
                        writer.print("LOGIN_SUCCESS");
                        System.out.println("login SUCCESS");
                    } else {
                        // 如果密码错误，返回 WRONG_PASSWORD
                        writer.print("WRONG_PASSWORD");
                    }
                } else {
                    statement.executeUpdate(createUesr);
                    // 如果是新注册帐号，服务器返回 SIGN_UP_SUCCESS
                    writer.print("SIGN_UP_SUCCESS");
                    System.out.println("sign up success");
                }
            } else {
                System.out.println("没有传入用户名");
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
