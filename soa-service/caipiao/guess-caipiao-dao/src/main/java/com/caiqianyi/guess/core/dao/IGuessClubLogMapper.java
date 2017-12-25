package com.caiqianyi.guess.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessClubLog;

@Mapper
public interface IGuessClubLogMapper {
	
	int writerLog(GuessClubLog log);
	
	int count(@Param("clubId") Integer clubId,@Param("seq") String seq);
}
