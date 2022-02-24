package service;

import dao.ConsultationDao;
import entity.Consultation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyConsultationServlet")
public class modifyConsultationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String con_id = request.getParameter("con_id");
        String text_rec = request.getParameter("text_rec");
        ConsultationDao consultationDao = new ConsultationDao();
        Consultation consultation = consultationDao.findbycon_id(con_id);
        consultation.setText_rec(text_rec);
        response.setHeader("content-type","text/html;charset=UTF-8");
        if (consultationDao.update(consultation))
            request.getRequestDispatcher("/manageConsultationServlet").forward(request,response);
        else
            response.sendRedirect("modifyConsultation.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
