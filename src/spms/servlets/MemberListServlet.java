package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			
			MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
			
		//	request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewUrl", "/member/MemberList.jsp");
			
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
	}
}
}
