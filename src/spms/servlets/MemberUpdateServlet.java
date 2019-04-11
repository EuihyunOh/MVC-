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

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ServletContext sc = this.getServletContext();			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/member/Error.jsp");
			rd.forward(request, response);
		}finally {
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = new Member();
			member.setEmail(request.getParameter("email"))
				  .setName(request.getParameter("name"))
				  .setNo(Integer.parseInt(request.getParameter("no")));
			
			memberDao.update(member);	
			
			response.sendRedirect("list");
		}catch(Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("/member/Error.jsp");
			rd.forward(request, response);
		}finally {
		}
	}
	
}
