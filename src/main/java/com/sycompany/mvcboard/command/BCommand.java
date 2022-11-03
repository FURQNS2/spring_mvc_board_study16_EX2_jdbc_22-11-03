package com.sycompany.mvcboard.command;

import org.springframework.ui.Model;

public interface BCommand {
	
	public void execute(Model model);     // 추상메소드

}
