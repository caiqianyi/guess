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
import com.lebaoxun.bbs.core.dao.IThemeMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.PastePost;
import com.lebaoxun.bbs.core.entity.Theme;
import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPastePostService;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 回帖
 * @author Caiqianyi
 *
 */
@Service
public class PastePostServiceImpl implements IPastePostService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IPastePostMapper pastePostMapper;
	
	@Resource
	private IPasteMapper pasteMapper;
	
	@Resource
	private IThemeMapper themeMapper;
	
	@Resource
	private IAccountService accountService;
	
	@Resource
	private IPraiseService praiseService;

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public PastePost replyPaste(String content, String pictures,
			Integer userId, Integer pasteId, String source) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(pasteId);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		String submeter = PastePost.modulo(pasteId);
		Integer tier = pastePostMapper.findTier(submeter, pasteId) + 2;
		PastePost pastePost = new PastePost();
		pastePost.setContent(content);
		pastePost.setPictures(pictures);
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
		
		/**
		 * 修改贴吧相关数据
		 */
		Theme theme = themeMapper.findById(paste.getPlateId());
		Theme updateTheme = new Theme();
		updateTheme.setId(paste.getPlateId());
		updateTheme.setPasteCount(theme.getPasteCount()+1);
		updateTheme.setLastPublishTime(new Date());
		themeMapper.updateBy(updateTheme);
		
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
			
			/**
			 * 修改贴吧相关数据
			 */
			Theme theme = themeMapper.findById(paste.getPlateId());
			Theme updateTheme = new Theme();
			updateTheme.setId(paste.getPlateId());
			updateTheme.setPasteCount(theme.getPasteCount()-1);
			
			themeMapper.updateBy(updateTheme);
			pasteMapper.updateBy(updatePaste);
		}
		return row;
	}

	@Override
	public List<PastePost> findByPasteId(Integer userId, Integer flag, Integer pasteId, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		String orderBy = "order by tier, createTime";
		Boolean isCreator = null;
		if(flag != null){
			if(1 == flag){
				orderBy = "order by tier desc, createTime desc";
			}
			if(2 == flag){
				isCreator = true;
			}
		}
		logger.info("orderBy={},isCreator={}",orderBy,isCreator);
		List<PastePost> list = pastePostMapper.findByPasteId(PastePost.modulo(pasteId), pasteId, orderBy, isCreator, size, offset);
		for(PastePost post : list){
			User user = accountService.findCacheInfoByUserId(userId);
			boolean praise = false;
			if(userId != null){
				int count = praiseService.countByUser(PraiseLogType.POST, post.getId()+"" , userId);
				praise = count > 0;
			}
			post.setPraise(praise);
			if(user != null){
				post.setNickName(user.getNickname());
				post.setHeadimgurl(user.getHeadimgurl());
			}
		}
		return list;
	}
	
	@Override
	public PastePost findById(Integer userId, Integer pasteId,
			Integer id) {
		// TODO Auto-generated method stub
		PastePost post = pastePostMapper.findPostById(PastePost.modulo(pasteId), id);
		if(post != null){
			User user = accountService.findCacheInfoByUserId(userId);
			boolean praise = false;
			if(userId != null){
				int count = praiseService.countByUser(PraiseLogType.POST, post.getId()+"" , userId);
				praise = count > 0;
			}
			post.setPraise(praise);
			if(user != null){
				post.setNickName(user.getNickname());
				post.setHeadimgurl(user.getHeadimgurl());
			}
		}
		return post;
	}

}