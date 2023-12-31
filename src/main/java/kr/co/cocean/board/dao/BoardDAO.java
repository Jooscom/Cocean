package kr.co.cocean.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.co.cocean.board.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	ArrayList<BoardDTO> boardList(String category, int perPage, Integer offset);
	
	ArrayList<BoardDTO> boardList_pin(String category);

	void boardWrite(BoardDTO param);

	BoardDTO boardDetail(int boardID);

	HashMap<String, String> prevNext(int boardID, String category);

	ArrayList<BoardDTO> commentList(int boardID);

	void commentWrite(BoardDTO param);

	BoardDTO commentDetail(int commentID);

	int getTotalCount(String category);

}
