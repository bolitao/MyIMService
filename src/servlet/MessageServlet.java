package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 中转消息的 servlet
 *
 * @author Boli Tao
 * @date 2019/1/1
 */
public class MessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String anyNewMessage = req.getParameter("anyNewMessage");
        String newMessage = req.getParameter("newMessage");

        String sendId = req.getParameter("sendId");
        String receiveId = req.getParameter("receiveId");
        String messageStatus = req.getParameter("messageStatus");
        String messageContent = req.getParameter("messageContent");
        String time = req.getParameter("time");

        System.out.println(sendId + "; " + receiveId + "; " + time + "; " + messageContent + "; " + messageStatus);

        String addMessageSQL = "";
        String receiveMessage;
        String responseMessageService;

        if (anyNewMessage == "anyNewMessage") {

        }

    }
}
