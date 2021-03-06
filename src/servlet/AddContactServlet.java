package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddContactServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String friendId = req.getParameter("friendId");
        String userId = req.getParameter("userId");

        System.out.println("addContact 获得的参数：" + friendId + ", " + userId);
        String addContactSQL = "INSERT INTO contact(userId, friendId) VALUES('" + userId + "', '" + friendId + "');INSERT INTO contact(userId, friendId) VALUES('" + friendId + "', '" + userId + "')";

        Statement statement = null;
        Connection connection = null;
        PrintWriter writer = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8"
                    , "bolitao", "bolitao");
            statement = connection.createStatement();
            statement.executeUpdate(addContactSQL);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
