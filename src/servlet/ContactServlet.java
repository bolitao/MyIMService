package servlet;

import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author Boli Tao
 * @date 2018/12/30
 */
public class ContactServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String userId = req.getParameter("username");
//        String queryUserContact = "SELECT contactId, userId, friendId, remark, becomeFriendDate FROM contact WHERE userId IN (SELECT userSystemId FROM `user` WHERE userCustomID = +'" + userId + "')";
        String queryUserContact = "SELECT contact.contactId, contact.userId, contact.friendId, contact.remark, contact.becomeFriendDate, user.userCustomID FROM `contact`, `user` WHERE (contact.userId = user.userSystemId) AND contact.userId IN (SELECT userSystemId FROM `user` WHERE userCustomID = +'" + userId + "')";
//        String queryCustomId = "";
//        String queryUserContact = "SELECT * FROM contact";

        PrintWriter writer = resp.getWriter();
        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                    "bolitao", "bolitao");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryUserContact);
//            ResultSet contactCustomIdResult = statement.executeQuery(queryCustomId);
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("becomeFriendDate", resultSet.getString("becomeFriendDate"));
                jsonObject.put("contactId", resultSet.getString("contactId"));
                jsonObject.put("userId", resultSet.getString("userId"));
                jsonObject.put("friendId", resultSet.getString("friendId"));
                jsonObject.put("remark", resultSet.getString("remark"));
                jsonObject.put("friendCustomId", resultSet.getString("userCustomId"));
                jsonArray.add(jsonObject);
            }
            writer.print(jsonArray);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
