package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AppointmentDao;
import dao.ConsultationDao;
import dao.PatientDao;
import entity.Appointment;
import entity.Consultation;
import entity.Patient;
import javafx.util.Pair;

@WebServlet(name = "manageMessageServlet")
public class manageConsultationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String doctor_id = (String) request.getSession().getAttribute("doctor_id");
        /*
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.getWriter().println(doctor_id);
         */
        AppointmentDao appointmentDao = new AppointmentDao();
        //找到该医生所收到的所有预约
        List<Appointment> appointmentList = appointmentDao.findbydoctor_id(doctor_id);
        ConsultationDao consultationDao = new ConsultationDao();
        List<Pair<Consultation,Patient>> consultations = new ArrayList<>();
        PatientDao patientDao = new PatientDao();
        if (appointmentList!=null) {
        	for (int i = 0;i<appointmentList.size();i++){
            //找到与这个预约相对应的咨询记录
            Consultation consultation = consultationDao.findbyapp_id(appointmentList.get(i).getApp_id());
            //找到咨询的病人
            if(consultation != null) {
            	Patient patient = patientDao.findbypatient_id(appointmentList.get(i).getPatient_id());
                consultations.add(new Pair(consultation,patient));
            }
        	}
        }
        
        request.setAttribute("consultations",consultations);
        request.getRequestDispatcher("/manageConsultation.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
