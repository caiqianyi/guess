package com.caiqianyi.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.caiqianyi.guess.entity.GuessClubMember;

@Mapper
public interface IGuessClubMemberMapper {
	
	int join(GuessClubMember member);
	
	int quit(@Param("memberId")Integer memberId);
	
	int update(GuessClubMember member);
	
	GuessClubMember findById(@Param("id")Integer id);
	
	GuessClubMember findByClubAndUserId(@Param("clubId")Integer clubId,@Param("userId")Integer userId);
	
	List<GuessClubMember> findByUserId(@Param("userId")Integer userId);
	
	List<GuessClubMember> findByClubId(@Param("clubId")Integer clubId);
}
