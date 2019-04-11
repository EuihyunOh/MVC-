package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;


@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{response.setContentType("text/html; charset=UTF-8");
	
	RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
	rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			ServletContext sc= this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = new Member();
			member.setEmail(request.getParameter("email"))
				  .setName(request.getParameter("name"))
				  .setPassword(request.getParameter("password"));
			
			memberDao.insert(member);
			
			response.sendRedirect("list");
		}catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("member/Error.jsp");
			rd.forward(request, response);
		}finally {
		}
	}
}