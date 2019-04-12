package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public Object[] getDataBinders() {		
		return new Object[]{"no",Integer.class,"member",spms.vo.Member.class};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");

		
		if(member.getEmail()==null) {		
		Integer no = (Integer)model.get("no");
		model.put("member", memberDao.selectOne(no));
		return "/member/MemberUpdateForm.jsp";
		
		}else {
		memberDao.update(member);
		return "redirect:list.do";		
		}
	
	}	
}
