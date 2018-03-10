package com.lebaoxun.bbs.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lebaoxun.bbs.core.dao.IPasteMapper;
import com.lebaoxun.bbs.core.dao.IPastePostMapper;
import com.lebaoxun.bbs.core.dao.IPasteReplyMapper;
import com.lebaoxun.bbs.core.dao.IPraiseLogMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.PastePost;
import com.lebaoxun.bbs.core.entity.PasteReply;
import com.lebaoxun.bbs.core.entity.PraiseLog;
import com.lebaoxun.bbs.core.enums.PraiseLogType;
import com.lebaoxun.bbs.core.service.IPraiseService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 点赞
 * @author Caiqianyi
 *
 */
@Service
public class PraiseServiceImpl implements IPraiseService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IPraiseLogMapper praiseLogMapper;
	
	@Resource
	private IPasteMapper pasteMapper;
	
	@Resource
	private IPastePostMapper pastePostMapper;
	
	@Resource
	private IPasteReplyMapper pasteReplyMapper;

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public PraiseLog praise(Integer userId, String hostIp, String source,
			Integer pasteId, String recordId, PraiseLogType logType) {
		// TODO Auto-generated method stub
		logger.info("pasteId={}",pasteId);
		
		Paste paste = pasteMapper.findById(pasteId);
		if(paste == null){
			throw new I18nMessageException("-1","贴子不存在，操作失败！");
		}
		
		String submeter = PraiseLog.modulo(userId);
		
		int count = praiseLogMapper.countByUser(submeter, logType.name(), recordId, userId);
		if(count != 0){
			throw new I18nMessageException("-1","您已点过赞了！");
		}
		
		PraiseLog log = new PraiseLog();
		log.setCreateTime(new Date());
		log.setHostIp(hostIp);
		log.setLogType(logType.name());
		log.setRecordId(recordId);
		log.setSource(source);
		log.setUserId(userId);
		int id = praiseLogMapper.save(log);
		log.setId(id);
		
		/**
		 * 增加点赞数
		 */
		if(logType.equals(PraiseLogType.PASTE)){
			Paste update = new Paste();
			update.setId(pasteId);
			update.setPraiseCount(paste.getPraiseCount()+1);
			pasteMapper.updateBy(update);
		}else if(logType.equals(PraiseLogType.POST)){
			PastePost post = pastePostMapper.findPostById(PastePost.modulo(pasteId), Integer.parseInt(recordId));
			if(post == null){
				throw new I18nMessageException("-1","帖子已被删除");
			}
			PastePost update = new PastePost();
			update.setId(post.getId());
			update.setPraiseCount(post.getPraiseCount()+1);
			update.setPasteId(pasteId);
			pastePostMapper.updateBy(update);
		}else if(logType.equals(PraiseLogType.REPLY)){
			PasteReply reply = pasteReplyMapper.findBy(PasteReply.modulo(pasteId), Integer.parseInt(recordId));
			if(reply == null){
				throw new I18nMessageException("-1","评论已被删除");
			}
			PasteReply update = new PasteReply();
			update.setId(reply.getId());
			update.setPasteId(pasteId);
			update.setPraiseCount(reply.getPraiseCount()+1);
			pasteReplyMapper.updateBy(update);
		}
		return log;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public int praiseCancel(Integer userId, Integer pasteId, String recordId, PraiseLogType logType) {
		String submeter = PraiseLog.modulo(userId);
		
		int count = praiseLogMapper.countByUser(submeter, logType.name(), recordId, userId);
		if(count == 0){
			return 1;
		}
		
		int row = praiseLogMapper.deleteBy(submeter, logType.name(), recordId, userId);
		Paste paste = pasteMapper.findById(pasteId);
		if(row > 0 && paste != null){
			
			/**
			 * 减少点赞数
			 */
			if(logType.equals(PraiseLogType.PASTE)){
				Paste update = new Paste();
				update.setId(pasteId);
				update.setPraiseCount(paste.getPraiseCount()-1);
				pasteMapper.updateBy(update);
			}else if(logType.equals(PraiseLogType.POST)){
				PastePost post = pastePostMapper.findPostById(PastePost.modulo(pasteId), Integer.parseInt(recordId));
				if(post != null){
					PastePost update = new PastePost();
					update.setId(post.getId());
					update.setPraiseCount(post.getPraiseCount()-1);
					update.setPasteId(pasteId);
					pastePostMapper.updateBy(update);
				}
			}else if(logType.equals(PraiseLogType.REPLY)){
				PasteReply reply = pasteReplyMapper.findBy(PasteReply.modulo(pasteId), Integer.parseInt(recordId));
				if(reply != null){
					PasteReply update = new PasteReply();
					update.setId(reply.getId());
					update.setPraiseCount(reply.getPraiseCount()-1);
					update.setPasteId(pasteId);
					pasteReplyMapper.updateBy(update);
				}
			}
		}
		return row;
	}

	@Override
	public int countByUser(PraiseLogType logType, String recordId,
			Integer userId) {
		// TODO Auto-generated method stub
		return praiseLogMapper.countByUser(PraiseLog.modulo(userId), logType.name(), recordId, userId);
	}

	
}
