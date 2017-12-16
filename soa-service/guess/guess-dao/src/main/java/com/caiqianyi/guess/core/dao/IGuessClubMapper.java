package com.caiqianyi.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessClub;

@Mapper
public interface IGuessClubMapper {

	int createClub(GuessClub club);
	
	int update(GuessClub club);
	
	GuessClub findById(@Param("clubId") Integer clubId);
	
	GuessClub findByIdForAll(@Param("clubId") Integer clubId);
	
	List<GuessClub> findByUserId(@Param("userId") Integer userId);
	
}
