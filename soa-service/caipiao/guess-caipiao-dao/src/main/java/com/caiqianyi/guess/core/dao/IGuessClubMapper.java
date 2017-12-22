package com.caiqianyi.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessClub;

@Mapper
public interface IGuessClubMapper {

	int createClub(GuessClub club);
	
	int update(GuessClub club);
	
	GuessClub findById(@Param("createId") Integer createId,@Param("clubId") Integer clubId);
	
	GuessClub findByIdForAll(@Param("createId") Integer createId, @Param("clubId") Integer clubId);
	
	List<GuessClub> findByCreateId(@Param("createId") Integer createId,@Param("kindOf") String kindOf);
	
	int countByCreateId(@Param("createId") Integer createId,@Param("kindOf") String kindOf);
	
}
