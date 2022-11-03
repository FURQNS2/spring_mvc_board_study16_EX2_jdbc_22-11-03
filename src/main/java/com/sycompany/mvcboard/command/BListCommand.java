package com.sycompany.mvcboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.sycompany.mvcboard.dao.BoardDao;
import com.sycompany.mvcboard.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {

		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> dtos = dao.List();
		//DB에서 가져온 글 목록을 모두 가지고 있는 Arraylist 자료구조의 dtos를 저장
		
		int count = dao.borad_count();  // 모든 게시글의 수를 반환, 나의 메소드 호출
		
		model.addAttribute("boardCount", count);
		
		model.addAttribute("list",dtos);  // 글 목록을 Arrylist를 모델객체 저장, dtos값의 이름을 list로 설정
		
	}

}
