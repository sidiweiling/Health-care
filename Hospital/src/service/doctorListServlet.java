package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DoctorDao;
import entity.Doctor;

@WebServlet(name = "doctorListServlet")
public class doctorListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String department = request.getParameter("de");
        String keyword = request.getParameter("keyword");
        DoctorDao doctorDao = new DoctorDao();
        if(keyword==null) {
        	List<Doctor> doctorList;
        	if ("all".equals(department) || department==null)
        		doctorList = doctorDao.findall();
        	else
        		doctorList = doctorDao.findbydepartment(department);
        	request.setAttribute("doctorList",doctorList);
        	request.getRequestDispatcher("doctorList.jsp").forward(request,response);
        }
        else {
        	List<Doctor> doctorList;
        	doctorList = doctorDao.findbykeyword(keyword);
        	if(doctorList == null) {
        		doctorList = doctorDao.findall();
        		request.setAttribute("flag", "null");
        	}
        	else {
        		request.setAttribute("flag", "found");
        	}
        	request.setAttribute("doctorList",doctorList);
        	request.getRequestDispatcher("doctorList.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
