package com.sycompany.mvcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sycompany.mvcboard.dao.BoardDao;
import com.sycompany.mvcboard.dto.BoardDto;

public class BContentViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();  // model객체에서 request 위치 추적
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// model객체에서 추적된 request의 객체를 밖으로 꺼내기
		
		String bid = request.getParameter("bid");  // request 안에 있는 DB에 저장된 bid 불러오기
		
		BoardDao dao = new BoardDao();   // BoardDao 클래스를 쓸 수 있는 dao 객체생성
		BoardDto dto = dao.content_view(bid);  
		// BoardDto 클래스에 객체를 사용해서 dao 업캐스팅해서 자식클래스인 dao의 매소드를 사용
		
		model.addAttribute("content", dto);  
		// 매개변수 model에 제공하는 이름(content)에 제공하는 속성(dto)을 더한다.
		
		}

}
