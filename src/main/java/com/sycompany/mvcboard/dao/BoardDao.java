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
		
		
		
//		try {
//			
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");  //오라클 파일명을 가져와야 한다.
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
		
		
//		//JDBC 오리지널 내용들
//		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();  //빈 리스트 선언
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;  // 결과세트
//		
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC, bstep ASC";  
//			// 게시글 번호의 내림차순 정렬(최근 글이 가장 위로 오도록한다.)
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체 넣기
//			
//			rs = pstmt.executeQuery(); // sql을 실행하여 결과값을 반환 / select만 나머지는 업데이트이다.
//			
//			while(rs.next()) {
//			
//				int bid = rs.getInt("bid"); //오라클 필드명을 작성
//				String bname = rs.getString("bname");
//				String btitle = rs.getString("btitle");
//				String bcontent = rs.getString("bcontent");
//				Timestamp bdate = rs.getTimestamp("bdate");
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//				
//				BoardDto dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);	// while문에 값을 담아줌
//				dtos.add(dto);  // 담아놓은 것을 보여줌
//				
//				
////				dto.setBid(bid);
////				dto.setBname(bname);
////				dto.setBtitle(btitle);
////				dto.setBcontent(bcontent);
////				dto.setBdate(bdate);
////				dto.setBhit(bhit);
////				dto.setBgroup(bgroup);
////				dto.setBstep(bstep);
////				dto.setBindent(bindent);							
//			}
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//					if(rs != null) {
//						rs.close();
//					} 	
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
//		
//		return dtos;  // 값을 반환해줌
//		
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
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, MVC_BOARD_SEQ.currval,0,0)";  // 게시글 번호의 내림차순 정렬(최근 글이 가장 위로 오도록한다.)
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체 넣기
//			
//			pstmt.setString(1, bname);
//			pstmt.setString(2, btitle);
//			pstmt.setString(3, bcontent);
//			//sql문 순서대로 넣고 완성
//			
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
		
	}
	
	public BoardDto content_view(String cid) {
		
		upHit(cid);  // content_view 호출 될 때마다 조회수가 올라가게 만듦
			
		String sql = "SELECT * FROM mvc_board WHERE bid=" + cid; 
		
		BoardDto dto = template.queryForObject(sql, new BeanPropertyRowMapper(BoardDto.class));
		
		return dto;
		
//		BoardDto dto = null;   // 미리 선언해야 리턴값을 만들 수 있음
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;  // 결과세트
//		
//		
//		try {
//		
//		conn = dataSource.getConnection();
//		String sql = "SELECT * FROM mvc_board WHERE bid=?";  // bid 숫자를 기준으로 값을 불러드림
//		pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체 넣기
//		pstmt.setString(1,cid);   // 첫번째의 값 불러오기	
//		rs = pstmt.executeQuery(); // sql을 실행하여 결과값을 반환 / select만 나머지는 업데이트이다.
//		
//		if(rs.next()) { // 한번만 돌려서 bid값을 가져오기, if문 안에서는 선언해서 객체 만들면 안 됨 오류남
//		
//			int bid = rs.getInt("bid"); //오라클 필드명을 작성
//			String bname = rs.getString("bname");
//			String btitle = rs.getString("btitle");
//			String bcontent = rs.getString("bcontent");
//			Timestamp bdate = rs.getTimestamp("bdate");
//			int bhit = rs.getInt("bhit");
//			int bgroup = rs.getInt("bgroup");
//			int bstep = rs.getInt("bstep");
//			int bindent = rs.getInt("bindent");
//			
//			dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);	// while문에 값을 담아줌						
//		}
//			
//	} catch (Exception e) {
//		e.printStackTrace();
//		
//	}finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				} 	
//				if(conn != null) {
//					conn.close();
//				} 
//				if(pstmt != null) {
//					pstmt.close();
//				}
//			}catch (Exception e) {
//				
//				e.printStackTrace();
//		}
//	}
//		
//		return dto;
//		
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
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";  
//			// bid값으로 나머지 값을 수정해준다.
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체에 sql문 넣기
//			
//			pstmt.setString(1, bname);
//			pstmt.setString(2, btitle);
//			pstmt.setString(3, bcontent);
//			pstmt.setString(4, bid);
//			//sql문 순서대로 넣고 완성
//			
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
		
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
		
//		Connection conn = null;
//		
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "DELETE FROM mvc_board WHERE bid=?";  
//			// bid값으로 나머지 값을 수정해준다.
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체에 sql문 넣기
//			
//			pstmt.setString(1, bid);
//			//sql문 순서대로 넣고 완성
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
		
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
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";  
//			// bid값이 불릴때마다 bhit가 올라간다 
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체에 sql문 넣기
//			
//			pstmt.setString(1, bid);
//			//sql문 순서대로 넣고 완성
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
//		
	}
	
	
	public int borad_count() {
		
		String sql = "SELECT * FROM mvc_board";
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();  //빈 리스트 선언
		
		int count = dtos.size();
		
		return count;
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;  // 결과세트
//		
//		int count = 0;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "SELECT * FROM mvc_board";  // 
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체 넣기			
//			rs = pstmt.executeQuery(); // sql을 실행하여 결과값을 반환 / select만 나머지는 업데이트이다.
//	
//			while(rs.next()) {
//				count++;							
//			}
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//					if(rs != null) {
//						rs.close();
//					} 	
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
//		
//		return count;  // 값을 반환해줌
	
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
		
		
//		reply_sort(bgroup, bstep);   // 댓글 그룹 묶기 및 댓글의 순서
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, ?, ?, ?)";  // 게시글 번호의 내림차순 정렬(최근 글이 가장 위로 오도록한다.)
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체 넣기
//			
//			pstmt.setString(1, bname);
//			pstmt.setString(2, btitle);
//			pstmt.setString(3, bcontent);
//			pstmt.setString(4, bgroup);
//			pstmt.setInt(5, Integer.parseInt(bstep)+1);
//			pstmt.setInt(6, Integer.parseInt(bindent)+1);   // 문자열을 숫자열로 변환
//			
//			//sql문 순서대로 넣고 완성
//			
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
		
		
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
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup=? and bstep>?";  
//			// bid값이 불릴때마다 bhit가 올라간다 
//			pstmt = conn.prepareStatement(sql); // 위에 만들어두었던 객체에 sql문 넣기
//			
//			pstmt.setString(1, bgroup);
//			pstmt.setString(2, bstep);
//			//sql문 순서대로 넣고 완성
//			
//			pstmt.executeUpdate(); // sql문을 실행 / select 빼고 나머지는 업데이트이다.
//								
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally {
//				try {
//						
//					if(conn != null) {
//						conn.close();
//					} 
//					if(pstmt != null) {
//						pstmt.close();
//					}
//				}catch (Exception e) {
//					
//					e.printStackTrace();
//			}
//		}
//		
//		
//	}
}
