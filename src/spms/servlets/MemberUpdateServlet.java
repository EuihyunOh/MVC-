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
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ServletContext sc = this.getServletContext();			
			MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");

			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
			
		}catch(Exception e){
			throw new ServletException(e);
		}finally {
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
			
			Member member = (Member)request.getAttribute("member");
			
			memberDao.update(member);	
			
			request.setAttribute("viewUrl", "redirect:list.do");
		}catch(Exception e){
			throw new ServletException(e);
		}finally {
		}
	}
	
}
