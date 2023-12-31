package kr.co.cocean.mypage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.cocean.mypage.dto.LoginDTO;
import kr.co.cocean.mypage.dto.MypageDTO;

@Mapper
public interface MypageDAO {
	//비밀번호 수정
	
	String selectEncpw(int employeeID);
	
	void changePw(String newPass, int employeeID);

	//마이페이지 리스트
	List<HashMap<String, Object>> mypagedetail(int userId);

	
	
	
	

}
