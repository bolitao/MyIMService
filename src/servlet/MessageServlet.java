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

/**
 * 中转消息的 servlet
 * 将消息存入数据库
 *
 * @author Boli Tao
 * @date 2019/1/1
 */
public class MessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

//        String anyNewMessage = req.getParameter("anyNewMessage");
//        String newMessage = req.getParameter("newMessage");
        String sendId = req.getParameter("sendId");
        String receiveId = req.getParameter("receiveId");
        String messageStatus = req.getParameter("messageStatus");
        String messageContent = req.getParameter("messageContent");
        String time = req.getParameter("time");
        String messageType = req.getParameter("messageType");

        // 测试语句
        System.out.println("senderId: " + sendId + "\nreceiveId: " + receiveId + "\ntime: " + time + "\ncontent: "
                + messageContent + "\nstatus: " + messageStatus);

//        String addMessageSQL = "INSERT INTO `message_info`(senderId, receiverId, messageStatus) VALUES ('+" + sendId + "','" + receiveId + "','" + messageStatus + "');";
        String addMessageSQL = "INSERT INTO `message_info`(messageType, messageStatus, senderId, receiverId, messageContent, sentTime) VALUES ('" + messageType + "', '" + messageStatus + "', '+" + sendId + "', '" + receiveId + "', '" + messageContent + "', '" + time + "')";
//        PrintWriter writer = resp.getWriter();
        ResultSet idResult = null;
        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8"
                    , "bolitao", "bolitao");
            statement = connection.createStatement();
            if (statement.executeUpdate(addMessageSQL) > 0) {
                System.out.println("成功将消息存入数据库");
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
