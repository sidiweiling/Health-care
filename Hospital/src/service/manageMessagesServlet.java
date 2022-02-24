package service;

import dao.MessageDao;
import entity.Message;
import entity.Patient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "manageMessagesServlet")
public class manageMessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String user_mail = (String) request.getSession().getAttribute("user_mail");
        MessageDao messageDao = new MessageDao();
        List<Patient> patientList = messageDao.findmsgofpatient(user_mail);
        request.setAttribute("patientList",patientList);
        request.getRequestDispatcher("manageMessages.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
