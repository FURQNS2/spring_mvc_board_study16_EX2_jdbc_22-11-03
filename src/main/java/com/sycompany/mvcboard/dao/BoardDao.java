package com.sycompany.mvcboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sycompany.mvcboard.dto.BoardDto;
import com.sycompany.mvcboard.util.Constant;

public class BoardDao {

	DataSource dataSource;  // 멤버변수 선언, 접속정보 모두가져오기
	
	JdbcTemplate template; //bean에서 선언한 이름과 동일한 이름으로 변수 만들기

	public BoardDao() {
		super();
		
		this.template = Constant.template;

	}
	
	public ArrayList<BoardDto> List() {   // 같은 패키지가 아니기 때문에 import 해줘야 함, 모든 값을 반환하기 때문에 매개변수를 넣어주지 않아도 됨.
		
		//JDBC template 이용
		String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC, bstep ASC";
		
		ArrayList<BoardDto> dtos = (ArrayList<BoardDto>) template.query(sql, new BeanPropertyRowMapper(BoardDto.class));
		// template에 담긴 정보 가져오기, qurey 묻는다, 
		// 만들고 싶은 sql문 넘고, 
		// BeanPropertyRowMapper-빈에 속성을 한줄씩 주소를 주겠다. 어디에 담을래?
		// BoardDto.class  Dto라는 클래스에 담아줘
		
		return dtos;

	}
	
	
	public void write(final String bname, final String btitle, final String bcontent) { // final해서 값 고정시킴
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, MVC_BOARD_SEQ.currval,0,0)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				
				
				return pstmt;
			}
		});
		
	}
	
	public BoardDto content_view(String cid) {
		
		upHit(cid);  // content_view 호출 될 때마다 조회수가 올라가게 만듦
			
		String sql = "SELECT * FROM mvc_board WHERE bid=" + cid; 
		
		BoardDto dto = template.queryForObject(sql, new BeanPropertyRowMapper(BoardDto.class));
		
		return dto;
		
	}
	
	
// 글 수정하기
	public void modify(final String bname, final String btitle, final String bcontent, final String bid) {
		
		String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				pstmt.setString(4, bid);
			}
		});
		
		
	}
	
	
	
// 작성한 글 지우기	
public void delete(final String bid) {
	
		String sql = "DELETE FROM mvc_board WHERE bid=?";
	
		this.template.update(sql, new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement pstmt) throws SQLException {
			// TODO Auto-generated method stub
			
			pstmt.setString(1, bid);
		}
	});

		
	}



// 클릭할 때마다 조회수 올리기
	public void upHit(final String bid) {
		
		String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
		
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setString(1, bid);
			}
		});
		
	}
	
	
	public int borad_count() {
		
		String sql = "SELECT * FROM mvc_board";
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();  //빈 리스트 선언
		
		int count = dtos.size();
		
		return count;
	
	}
	
	public void reply(String bid, final String bname, final String btitle, final String bcontent, String bhit, final String bgroup, final String bstep, final String bindent) {
		
		
		reply_sort(bgroup, bstep);   // 댓글 그룹 묶기 및 댓글의 순서
		
		String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, ?, ?, ?)";  	
		
		this.template.update(sql, new PreparedStatementSetter() {
				
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				pstmt.setString(4, bgroup);
				pstmt.setInt(5, Integer.parseInt(bstep)+1);
				pstmt.setInt(6, Integer.parseInt(bindent)+1);
			}
		});
		
	}
	
	
	public void reply_sort(final String bgroup, final String bstep) {
		

		
		String sql = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup=? and bstep>?"; 	 	
		
		this.template.update(sql, new PreparedStatementSetter() {
				
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				
				pstmt.setString(1, bgroup);
				pstmt.setString(2, bstep);
				
			}
		});
		
	}

}
