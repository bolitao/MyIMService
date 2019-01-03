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

public class QuerySystemUserIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        System.out.println("query-id: " + username);

        PrintWriter writer = resp.getWriter();
        ResultSet idResult = null;
        Statement statement = null;
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myim?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8"
                    , "bolitao", "bolitao");
            statement = connection.createStatement();

            String queryId = "SELECT userSystemId, userTel, userMail, userCustomID, sex, registrationDate, userNickname, userCustomInfo FROM `user` WHERE userCustomID = " + "'" + username + "'";
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            if (username != null && !"".equals(username)) {
                idResult = statement.executeQuery(queryId);
                while (idResult.next()) {
                    System.out.println("idResult SysId: " + idResult.getString("userSystemId"));
                    jsonObject.put("userSystemId", idResult.getString("userSystemId"));
                    jsonObject.put("userTel", idResult.getString("userTel"));
                    jsonObject.put("userMail", idResult.getString("userMail"));
                    jsonObject.put("userCustomID", idResult.getString("userCustomID"));
                    jsonObject.put("sex", idResult.getString("sex"));
                    jsonObject.put("registrationDate", idResult.getString("registrationDate"));
                    jsonObject.put("userNickname", idResult.getString("userNickname"));
                    jsonObject.put("userCustomInfo", idResult.getString("userCustomInfo"));
                    jsonArray.add(jsonObject);
                }
            }
            if (!"".equals(jsonArray)) {
                writer.print(jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
