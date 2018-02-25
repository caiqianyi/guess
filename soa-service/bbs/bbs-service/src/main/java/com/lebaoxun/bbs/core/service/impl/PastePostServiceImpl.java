package com.lebaoxun.bbs.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lebaoxun.bbs.core.dao.IPasteMapper;
import com.lebaoxun.bbs.core.dao.IPastePostMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.PastePost;
import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 回帖
 * @author Caiqianyi
 *
 */
@Service
public class PastePostServiceImpl implements IPastePostService {
	
	@Resource
	private IPastePostMapper pastePostMapper;
	
	@Resource
	private IPasteMapper pasteMapper;

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public PastePost replyPaste(String content, Integer userId,
			Integer pasteId, String source) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(pasteId);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		String submeter = PastePost.modulo(pasteId);
		Integer tier = pastePostMapper.findTier(submeter, pasteId) + 2;
		PastePost pastePost = new PastePost();
		pastePost.setContent(content);
		pastePost.setIsCreator(paste.getUserId().equals(userId));
		pastePost.setPasteId(pasteId);
		pastePost.setSource(source);
		pastePost.setTier(tier);
		pastePost.setUserId(userId);
		int lastPostId = pastePostMapper.save(pastePost);
		pastePost.setId(lastPostId);
		
		/**
		 * 修改回帖数，最后修改时间，最后回帖时间，最后回帖ID
		 */
		Paste updatePaste = new Paste();
		updatePaste.setId(paste.getId());
		updatePaste.setReplyPasteCount(paste.getReplyPasteCount()+1);
		updatePaste.setLastUpdateTime(new Date());
		updatePaste.setLastPostTime(new Date());
		updatePaste.setLastPostId(lastPostId);
		pasteMapper.updateBy(updatePaste);
		
		return pastePost;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public int deleteBy(Integer id, Integer pasteId, Integer userId) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(pasteId);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		String submeter = PastePost.modulo(pasteId);
		PastePost pastePost = pastePostMapper.findPostById(submeter, id);
		if(pastePost == null){
			throw new I18nMessageException("","");
		}
		int row = pastePostMapper.deleteBy(submeter, id, userId);
		if(row > 0){
			Paste updatePaste = new Paste();
			updatePaste.setId(paste.getId());
			updatePaste.setReplyPasteCount(paste.getReplyPasteCount()-1);
			if(id.equals(updatePaste.getLastPostId())){//是否为最后一条记录
				PastePost lastPost = pastePostMapper.findPostByPasteAndTier(submeter, pastePost.getTier() - 1, pasteId);
				if(lastPost != null){
					updatePaste.setLastPostTime(lastPost.getCreateTime());
					updatePaste.setLastPostId(lastPost.getId());
				}
			}
			pasteMapper.updateBy(updatePaste);
		}
		return row;
	}

	@Override
	public List<PastePost> findByPasteId(Integer pasteId, String orderBy,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return pastePostMapper.findByPasteId(PastePost.modulo(pasteId), pasteId, orderBy, size, offset);
	}

}