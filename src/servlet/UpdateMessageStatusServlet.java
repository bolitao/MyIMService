package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateMessageStatusServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String messageId = req.getParameter("messageId");
        System.out.println("接收到 update_message 请求");
        System.out.println("update messageId" + messageId);

        String updateMessageStatusSQL = "UPDATE message_info SET messageStatus = 'DELIVERED' WHERE messageStatus = 'IN_SERVER' AND messageId = '" + messageId + "' ";

        Statement statement = null;
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8"
                    , "bolitao", "bolitao");
            statement = connection.createStatement();
            statement.executeUpdate(updateMessageStatusSQL);
            System.out.println("成功更新消息状态");
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
