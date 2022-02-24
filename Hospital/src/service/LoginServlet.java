package service;

import dao.DoctorDao;
import dao.PatientDao;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "service.LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        int status = Integer.parseInt(request.getParameter("status"));
        UserDao userDao = new UserDao();
        User user = userDao.findbyusername(id);
        /*
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.getWriter().println(status);
        response.getWriter().println(password);
        response.getWriter().println(user.getPassword());
         */
        if (status==user.getPlayer()){
            if (password.equals(user.getPassword())){
                if (status==0) {
                	request.getSession().setAttribute("user_mail",id);
                	request.getSession().setAttribute("patient_id",userDao.findbyusername(id).getPatient_id());
                	request.getSession().setAttribute("msg",null);
                	request.getRequestDispatcher("/navigate.jsp").forward(request, response);
                }
                else {
                	request.getSession().setAttribute("user_mail",id);
                    request.getSession().setAttribute("doctor_id",userDao.findbyusername(id).getDoctor_id());
                    request.getSession().setAttribute("loginError",null);
                    request.getRequestDispatcher("/doctorInfo.jsp").forward(request,response);
                }
            }
            else
                response.sendRedirect("login.jsp");
        }
        else{
            response.sendRedirect("login.jsp");
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
