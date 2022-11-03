package com.sycompany.mvcboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sycompany.mvcboard.command.BCommand;
import com.sycompany.mvcboard.command.BContentViewCommand;
import com.sycompany.mvcboard.command.BDeleteCommand;
import com.sycompany.mvcboard.command.BListCommand;
import com.sycompany.mvcboard.command.BModifyCommand;
import com.sycompany.mvcboard.command.BReplyWrite;
import com.sycompany.mvcboard.command.BwriteCommand;
import com.sycompany.mvcboard.util.Constant;


@Controller
public class BoardController {

	BCommand command = null;  //부모클래스 호출
	
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
		
	}

	
	@RequestMapping("/")
	public String goList() {   //재요청 시 자동으로 list파일로 이동한다.
		return "redirect:list";	
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
	
		command = new BListCommand();   // 부모클래스는 자식 클래스를 받을 수 있음
		command.execute(model);
				
		return "list";
	}
	
	@RequestMapping("/write_form")
	public String write_form() {
		
		return "write_form";
	}
	
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {		
		
		model.addAttribute("request", request);
		
		command = new BwriteCommand();
		command.execute(model);
		
		return "redirect:list";   // 바로 이동할 수 있게 리다이렉트
	}
	
	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentViewCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentViewCommand();
		command.execute(model);
		
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BModifyCommand();
		command.execute(model);
		
		
		return "redirect:list"; 
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {   // delete의 command를 불러옴
		
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list"; 
	}
	
	@RequestMapping("/reply_write")
	public String reply_write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentViewCommand();   //원본 내용 불러오기
		command.execute(model);
		
		return "reply_write";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BReplyWrite();  // 새로 기입하는 댓글 내용
		command.execute(model);
		
		return "redirect:list"; 
	}
	
}



