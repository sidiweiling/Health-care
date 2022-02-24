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

@WebServlet(name = "service.RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int status = Integer.parseInt(request.getParameter("status"));
        String user_mail = request.getParameter("user_mail");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        UserDao userDao = new UserDao();
        //医生注册
        if (status==1){
            String department = request.getParameter("department");
            DoctorDao doctorDao = new DoctorDao();
            if (doctorDao.add(name,department,telephone)){
                String id = doctorDao.findlastdoctor_id().getDoctor_id();
                User user = new User(user_mail,password,id,null,1);
                if (userDao.add(user))
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                else
                    response.sendRedirect("register.jsp");
            }
            else
                response.sendRedirect("register.jsp");
        }
        //病人注册
        else{
            int sex = Integer.parseInt(request.getParameter("sex"));
            String address = request.getParameter("address");
            PatientDao patientDao = new PatientDao();
            if (patientDao.add(name,sex,address,telephone)){
                String id = patientDao.findlastpatient_id().getPatient_id();
                User user = new User(user_mail,password,null,id,0);
                if (userDao.add(user))
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                else
                    response.sendRedirect("register.jsp");
            }
            else
                response.sendRedirect("register.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
