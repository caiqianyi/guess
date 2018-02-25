package com.lebaoxun.bbs.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lebaoxun.bbs.core.dao.IPasteMapper;
import com.lebaoxun.bbs.core.dao.IPastePostMapper;
import com.lebaoxun.bbs.core.dao.IPasteReplyMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.PastePost;
import com.lebaoxun.bbs.core.entity.PasteReply;
import com.lebaoxun.bbs.core.service.IPasteReplyService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 回帖回复
 * @author DELL
 *
 */
@Service
public class PasteReplyServiceImpl implements IPasteReplyService {

	@Resource
	private IPasteReplyMapper pasteReplyMapper;
	
	@Resource
	private IPastePostMapper pastePostMapper;
	
	@Resource
	private IPasteMapper pasteMapper;
	
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
	public List<PasteReply> findByPasteId(Integer pasteId, Integer postId,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return pasteReplyMapper.findByPasteId(PasteReply.modulo(pasteId),
				pasteId, postId, size, offset);
	}

	@Override
	public List<PasteReply> findByPasteIdForTops(Integer pasteId,
			Integer[] postIds, Integer replySize, Integer size) {
		// TODO Auto-generated method stub
		return pasteReplyMapper.findByPasteIdForTops(
				PasteReply.modulo(pasteId), pasteId, postIds, replySize, size);
	}

}