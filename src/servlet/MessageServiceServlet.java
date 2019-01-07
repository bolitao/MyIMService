package servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

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
 * 处理 Android 端 MessageService 请求的 Servlet
 *
 * @author Boli Tao
 * @date 2018/1/3
 */
public class MessageServiceServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userSystemId = req.getParameter("userSystemId");

//        System.out.println("userSystemId: " + userSystemId);

        String queryMessageSQL = "SELECT senderId, receiverId, messageStatus, messageContent, messageId FROM message_info WHERE messageStatus = 'IN_SERVER' AND receiverId = '" + userSystemId + "'";

        PrintWriter writer = resp.getWriter();
        ResultSet messageResultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8"
                    , "bolitao", "bolitao");
            statement = connection.createStatement();
            messageResultSet = statement.executeQuery(queryMessageSQL);
            JSONArray jsonArray = new JSONArray();
            while (messageResultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("senderId", messageResultSet.getString("senderId"));
                jsonObject.put("receiverId", messageResultSet.getString("receiverId"));
                jsonObject.put("messageStatus", messageResultSet.getString("messageStatus"));
                jsonObject.put("messageContent", messageResultSet.getString("messageContent"));
                jsonObject.put("messageId", messageResultSet.getString("messageId"));
                jsonArray.add(jsonObject);
            }
            writer.print(jsonArray);
            messageResultSet.close();
            statement.close();
            connection.close();
//            System.out.println("关闭所有连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
