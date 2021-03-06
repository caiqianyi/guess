package com.lebaoxun.guess.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lebaoxun.guess.entity.GuessClubMember;

@Mapper
public interface IGuessClubMemberMapper {
	
	Integer applyJoin(GuessClubMember member);
	
	Integer addMember(GuessClubMember member);
	
	int join(@Param("memberId")Integer memberId,@Param("isCheck") Integer isCheck);
	
	int applyQuit(@Param("memberId")Integer memberId);
	
	int quit(@Param("memberId")Integer memberId,@Param("isCheck") Integer isCheck);
	
	int update(GuessClubMember member);
	
	int addWinCount(@Param("members")List<Integer> members);
	
	GuessClubMember findById(@Param("id")Integer id);
	
	GuessClubMember findByClubAndUserId(@Param("clubId")Integer clubId,@Param("userId")Integer userId);
	
	GuessClubMember findByClubAndId(@Param("clubId")Integer clubId,@Param("id")Integer id);
	
	List<GuessClubMember> findByClubIdForMember(@Param("clubId")Integer clubId);
	
	List<GuessClubMember> findByUserIdForMember(@Param("userId")Integer userId);
	
	List<GuessClubMember> findByClubId(@Param("clubId")Integer clubId,@Param("orderBy") String orderBy);
}
