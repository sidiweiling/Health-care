package service;

import dao.DoctorDao;
import dao.PatientDao;
import dao.UserDao;
import entity.Doctor;
import entity.Patient;
import entity.User;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyInfoServlet")
public class modifyInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int status = Integer.parseInt(request.getParameter("status"));
        String user_mail = request.getParameter("user_mail");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        UserDao userDao = new UserDao();
        if (status==1){
            String department = request.getParameter("department");
            String id = userDao.findbyusername(user_mail).getDoctor_id();
            DoctorDao doctorDao = new DoctorDao();
            Doctor doctor = new Doctor(id,name,department,telephone);
            if (doctorDao.update(doctor)){
                User user = new User(user_mail,password,id,null,1);
                if (userDao.updatepassword(user,password))
                    request.getRequestDispatcher("/doctorInfo.jsp").forward(request,response);
                else
                    response.sendRedirect("modifyInfo.jsp");
            }
            else
                response.sendRedirect("modifyInfo.jsp");
        }
        else{
            String address = request.getParameter("address");
            int sex = Integer.parseInt(request.getParameter("sex"));
            String id = userDao.findbyusername(user_mail).getPatient_id();
            PatientDao patientDao = new PatientDao();
            Patient patient = new Patient(id,name,sex,address,telephone);
            if (patientDao.update(patient)){
                User user = new User(user_mail,password,null,id,0);
                if (userDao.updatepassword(user,password))
                    request.getRequestDispatcher("/patientInfo.jsp").forward(request,response);
                else
                    response.sendRedirect("modifyInfo.jsp");
            }
            else
                response.sendRedirect("modifyInfo.jsp");
        }
        /*
        if (status=="doctor"){
            String department = request.getParameter("department");
            Doctor doctor = new Doctor(id,password,name,department,telephone);
            DoctorDao doctorDao = new DoctorDao();
            if (doctorDao.update(doctor)){
                request.getRequestDispatcher("/doctorInfo.jsp").forward(request,response);
            }
            else{
                response.sendRedirect("/modifyInfo.jsp");
            }
        }
        else{
            String address = request.getParameter("address");
            int sex = Integer.parseInt(request.getParameter("sex"));
            Patient patient = new Patient(id,password,name,sex,address,telephone);
            PatientDao patientDao = new PatientDao();
            if (patientDao.update(patient)){
                request.getRequestDispatcher("/patientInfo.jsp");
            }
            else{
                response.sendRedirect("/modifyInfo.jsp");
            }
        }

         */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
