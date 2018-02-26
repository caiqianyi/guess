package com.lebaoxun.bbs.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lebaoxun.bbs.core.dao.IPasteMapper;
import com.lebaoxun.bbs.core.dao.IThemeMapper;
import com.lebaoxun.bbs.core.entity.Paste;
import com.lebaoxun.bbs.core.entity.Theme;
import com.lebaoxun.bbs.core.service.IPasteService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 发帖
 * @author Caiqianyi
 *
 */
@Service
public class PasteServiceImpl implements IPasteService {

	@Resource
	private IPasteMapper pasteMapper;
	
	@Resource
	private IThemeMapper themeMapper;
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public Paste createPaste(String title, String content, String pictures,
			String source, Integer userId, Integer plateId, boolean top,
			boolean highlight) {
		// TODO Auto-generated method stub
		Theme theme = themeMapper.findById(plateId);
		if(theme == null){
			throw new I18nMessageException("","");
		}
		Paste paste = new Paste();
		paste.setCollectCount(0);
		paste.setContent(content);
		paste.setCreateTime(new Date());
		paste.setDeleted(false);
		paste.setHighlight(highlight);
		paste.setLastUpdateTime(new Date());
		paste.setPictures(pictures);
		paste.setPlateId(plateId);
		paste.setPraiseCount(0);
		paste.setReplyPasteCount(0);
		paste.setScanCount(0);
		paste.setSource(source);
		paste.setTitle(title);
		paste.setTop(top);
		paste.setTranspondCount(0);
		paste.setUserId(userId);
		int id = pasteMapper.save(paste);
		paste.setId(id);
		
		Theme updateTheme = new Theme();
		updateTheme.setId(theme.getId());
		updateTheme.setPasteCount(theme.getPasteCount()+1);
		updateTheme.setLastPublishTime(new Date());
		themeMapper.updateBy(updateTheme);
		
		return paste;
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public Paste setHighlight(Integer id, Integer userId, boolean highlight) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(id);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		paste.setHighlight(highlight);
		Paste update = new Paste();
		update.setId(id);
		update.setHighlight(highlight);
		pasteMapper.updateBy(update);
		return paste;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public Paste setTop(Integer id, Integer userId, boolean top) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(id);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		paste.setTop(top);
		Paste update = new Paste();
		update.setId(id);
		update.setTop(top);
		pasteMapper.updateBy(update);
		return paste;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public Integer deleteBy(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		Paste paste = pasteMapper.findById(id);
		if(paste == null){
			throw new I18nMessageException("","");
		}
		paste.setDeleted(true);
		Paste update = new Paste();
		update.setId(id);
		update.setDeleted(true);
		
		int row = pasteMapper.updateBy(update);
		
		Theme theme = themeMapper.findById(paste.getPlateId());
		
		Theme updateTheme = new Theme();
		updateTheme.setId(paste.getPlateId());
		updateTheme.setPasteCount(theme.getPasteCount()-1);
		//updateTheme.setLastPublishTime(new Date());
		themeMapper.updateBy(updateTheme);
		return row;
	}

	@Override
	public List<Paste> findByUserId(Integer userId, String orderBy,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return pasteMapper.findByUserId(userId, orderBy, size, offset);
	}

	@Override
	public List<Paste> findByPlateId(Integer plateId, Integer size,
			Integer offset) {
		// TODO Auto-generated method stub
		return pasteMapper.findByPlateId(plateId, size, offset);
	}

	@Override
	public Paste findById(Integer id) {
		// TODO Auto-generated method stub
		return pasteMapper.findById(id);
	}

}