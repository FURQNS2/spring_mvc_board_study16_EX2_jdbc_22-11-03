package com.sycompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sycompany.mvcboard.dao.BoardDao;

public class BwriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();  // model객체에서 리퀘스는 매핑
	
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// 매핑된 리퀘스트를 밖으로 꺼내기
		
		String bnamd =request.getParameter("bname");
		String btitle =request.getParameter("btitle");
		String bcontent =request.getParameter("bcontent");
		
		BoardDao dao =new BoardDao();
		dao.write(bnamd, btitle, bcontent);  // 내가 만든 메소드를 불러서 넣어줌
		
		
	}

}
