package com.lebaoxun.bbs.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.account.service.IAccountService;
import com.lebaoxun.bbs.core.dao.IPasteMapper;
import com.lebaoxun.bbs.core.dao.IPastePostMapper;
import com.lebaoxun.bbs.core.dao.IPasteReplyMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.PastePost;
import com.lebaoxun.bbs.core.entity.PasteReply;
import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPasteReplyService;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 回帖回复
 * @author DELL
 *
 */
@Service
public class PasteReplyServiceImpl implements IPasteReplyService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IPasteReplyMapper pasteReplyMapper;
	
	@Resource
	private IPastePostMapper pastePostMapper;
	
	@Resource
	private IPasteMapper pasteMapper;
	
	@Resource
	private IAccountService accountService;
	
	@Resource
	private IPraiseService praiseService;
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public PasteReply save(String content, Integer userId, Integer pasteId,
			Integer postId, Integer toReplyId, String source) {
		// TODO Auto-generated method stub
		PastePost post = pastePostMapper.findPostById(PastePost.modulo(pasteId), postId);
		if(post == null){
			throw new I18nMessageException("","");
		}
		
		PasteReply reply = new PasteReply();
		reply.setContent(content);
		reply.setPasteId(pasteId);
		reply.setPostId(postId);
		reply.setPraiseCount(0);
		reply.setSource(source);
		reply.setToReplyId(toReplyId);
		reply.setUserId(userId);
		
		int id = pasteReplyMapper.save(reply);
		
		PastePost updatePost = new PastePost();
		updatePost.setId(post.getId());
		updatePost.setLastReplyId(id);
		updatePost.setLastReplyTime(new Date());
		updatePost.setPasteId(pasteId);
		updatePost.setReplyCount(post.getReplyCount()+1);
		
		Paste updatePaste = new Paste();
		updatePaste.setId(post.getPasteId());
		updatePaste.setLastReplyTime(new Date());
		updatePaste.setLastUpdateTime(new Date());
		
		pasteMapper.updateBy(updatePaste);
		pastePostMapper.updateBy(updatePost);
		return reply;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public int deleteBy(Integer id, Integer pasteId, Integer userId) {
		String submeter = PastePost.modulo(pasteId);
		PasteReply reply = pasteReplyMapper.findBy(submeter, id);
		if(reply == null){
			throw new I18nMessageException("","");
		}
		int row = pasteReplyMapper.deleteBy(submeter, id, userId);
		if(row > 0){
			PastePost post = pastePostMapper.findPostById(PastePost.modulo(pasteId), reply.getPostId());
			
			PastePost updatePost = new PastePost();
			updatePost.setId(post.getId());
			updatePost.setReplyCount(post.getReplyCount()-11);
			updatePost.setPasteId(pasteId);
			
			if(id.equals(post.getLastReplyId())){//是否最后一条
				PasteReply prev = pasteReplyMapper.findByPrev(submeter, post.getId(), id);
				if(prev != null){
					updatePost.setLastReplyTime(prev.getCreateTime());
					updatePost.setLastReplyId(prev.getId());
					
					Paste updatePaste = new Paste();
					updatePaste.setId(post.getPasteId());
					updatePaste.setLastReplyTime(prev.getCreateTime());
					pasteMapper.updateBy(updatePaste);
				}
			}
			pastePostMapper.updateBy(updatePost);
		}
		return row;
	}

	@Override
	public List<PasteReply> findByPasteId(
			Integer userId, Integer pasteId, 
			Integer postId, Integer flag,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		String orderBy = "order by createTime desc";
		if(flag != null && 1 == flag){
			orderBy = "order by praiseCount desc";
		}
		logger.debug("flag={},orderBy={}",flag,orderBy);
		List<PasteReply> list = pasteReplyMapper.findByPasteId(PasteReply.modulo(pasteId),
				pasteId, postId, orderBy, size, offset);
		for(PasteReply reply : list){
			User user = accountService.findCacheInfoByUserId(userId);
			boolean praise = false;
			if(userId != null){
				int count = praiseService.countByUser(PraiseLogType.REPLY, reply.getId()+"" , userId);
				praise = count > 0;
			}
			reply.setPraise(praise);
			if(user != null){
				reply.setNickName(user.getNickname());
				reply.setHeadimgurl(user.getHeadimgurl());
			}
		}
		return list;
	}

	@Override
	public List<PasteReply> findByPasteIdForTops(Integer pasteId,
			Integer[] postIds, Integer replySize, Integer size) {
		// TODO Auto-generated method stub
		return pasteReplyMapper.findByPasteIdForTops(
				PasteReply.modulo(pasteId), pasteId, postIds, replySize, size);
	}

}