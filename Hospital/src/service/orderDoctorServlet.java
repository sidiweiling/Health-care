package service;

import dao.AppointmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "orderDoctorServlet")
public class orderDoctorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String doctor_id = request.getParameter("doctor_id");
        String department = request.getParameter("department");
        String patient_id = request.getSession().getAttribute("patient_id").toString();
        AppointmentDao appointmentDao = new AppointmentDao();
        if (appointmentDao.add(department,doctor_id,patient_id))
            request.getRequestDispatcher("doctorListServlet").forward(request,response);
        else{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("无法预约该医生");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
