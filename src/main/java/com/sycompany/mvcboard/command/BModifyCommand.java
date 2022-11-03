package com.sycompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sycompany.mvcboard.dao.BoardDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();  // model객체에서 리퀘스는 매핑
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// 매핑된 리퀘스트를 밖으로 꺼내기
		
		String bnamd =request.getParameter("bname");
		String btitle =request.getParameter("btitle");
		String bcontent =request.getParameter("bcontent");
		String bid =request.getParameter("bid");
		
		
		BoardDao dao =new BoardDao();
		dao.modify(bnamd, btitle, bcontent, bid);  // 내가 만든 메소드에 매개변수를 순서대로 넣어줌

	}

}
