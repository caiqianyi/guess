package com.lebaoxun.bbs.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lebaoxun.bbs.core.dao.ISubscriberMapper;
import com.lebaoxun.bbs.core.dao.IThemeMapper;
import com.lebaoxun.bbs.core.entity.Subscriber;
import com.lebaoxun.bbs.core.entity.Theme;
import com.lebaoxun.bbs.core.service.IThemeService;
import com.lebaoxun.commons.exception.I18nMessageException;

/**
 * 主题管理
 * @author Caiqianyi
 *
 */
@Service
public class ThemeServiceImpl implements IThemeService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IThemeMapper themeMapper;
	
	@Resource
	private ISubscriberMapper subscriberMapper;
	
	private final static String SUBSCRIBER_TYPE = "THEME"; 

	@Override
	public int create(String kw, String descr, Integer flag, String kindOf, String lables,
			String logo, Integer creator, Integer owner) {
		
		int count = countByKw(kw);
		if(count != 0){
			throw new I18nMessageException("-1","创建失败，贴吧已存在！");
		}
		// TODO Auto-generated method stub
		Theme theme = new Theme();
		theme.setCreateTime(new Date());
		theme.setCreator(creator);
		theme.setDescr(descr);
		theme.setFlag(flag);
		theme.setKindOf(kindOf);
		theme.setKw(kw);
		theme.setLables(lables);
		theme.setLogo(logo);
		theme.setOwner(owner);
		theme.setPasteCount(0);
		theme.setSubscribes(0);
		return themeMapper.save(theme);
	}

	@Override
	public int updateBy(String kw, String descr, String kindOf, String lables,
			String logo, Integer owner) {
		// TODO Auto-generated method stub
		Theme theme = themeMapper.findByKw(kw);
		if(theme == null){
			throw new I18nMessageException("-1","贴吧不存在");
		}
		Theme update = new Theme();
		update.setId(theme.getId());
		update.setDescr(descr);
		update.setKindOf(kindOf);
		update.setLables(lables);
		update.setLogo(logo);
		update.setOwner(owner);
		return themeMapper.updateBy(update);
	}

	@Override
	public Subscriber subscribe(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		Theme theme = themeMapper.findById(themeId);
		if(theme == null){
			throw new I18nMessageException("-1","贴吧不存在");
		}
		
		boolean isSubscribe = true;
		Subscriber subscriber = subscriberMapper.findByUserId(SUBSCRIBER_TYPE, themeId, userId);
		if(subscriber == null){
			subscriber = new Subscriber();
			subscriber.setCreateTime(new Date());
			subscriber.setEnabled(true);
			subscriber.setPasteCount(0);
			subscriber.setPos(0);
			subscriber.setScore(0);
			subscriber.setThemeId(themeId);
			subscriber.setType(SUBSCRIBER_TYPE);
			subscriber.setUserId(userId);
			int id = subscriberMapper.subscribeFor(subscriber);
			subscriber.setId(id);
		}else if(!subscriber.isEnabled()){
			subscriberMapper.resubscribe(SUBSCRIBER_TYPE, themeId, userId);
		}else{
			isSubscribe = false;
		}
		
		if(isSubscribe){
			Theme update = new Theme();
			update.setId(theme.getId());
			update.setSubscribes(theme.getSubscribes()+1);
			themeMapper.updateBy(update);
		}
		
		return subscriber;
	}
	
	@Override
	public boolean unsubscribe(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		Theme theme = themeMapper.findById(themeId);
		if(theme == null){
			throw new I18nMessageException("-1","贴吧不存在");
		}
		int row = subscriberMapper.unsubscribe(SUBSCRIBER_TYPE, themeId, userId);
		if(row > 0){
			Theme update = new Theme();
			update.setId(theme.getId());
			update.setSubscribes(theme.getSubscribes()-1);
			themeMapper.updateBy(update);
		}
		return true;
	}

	@Override
	public Subscriber findByUserId(Integer themeId, Integer userId) {
		// TODO Auto-generated method stub
		return subscriberMapper.findByUserId(SUBSCRIBER_TYPE, themeId, userId);
	}

	@Override
	public List<Theme> search(String kw, int size, int offset) {
		// TODO Auto-generated method stub
		logger.info("size={},offset={}",size,offset);
		return themeMapper.search(kw, size, offset);
	}

	@Override
	public Theme findByKw(String kw) {
		// TODO Auto-generated method stub
		return themeMapper.findByKw(kw);
	}

	@Override
	public int countByKw(String kw) {
		// TODO Auto-generated method stub
		return themeMapper.countByKw(kw);
	}

	@Override
	public List<String> findAllKindOf() {
		// TODO Auto-generated method stub
		return themeMapper.findAllKindOf();
	}

	@Override
	public List<Theme> findByKindOf(String kindOf, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return themeMapper.findByKindOf(kindOf, size, offset);
	}
	
	@Override
	public List<Theme> findByRecommend(Integer userId,
			Integer size) {
		String[] kws = null, likeKindOfs = null;
		List<Theme> subscribers = themeMapper.findByUserSubscriber(userId);
		if(subscribers != null && !subscribers.isEmpty()){
			List<String> subList = new ArrayList<String>();
			Set<String> likes = new HashSet<String>();
			for(Theme subscriber : subscribers){
				subList.add(subscriber.getKw());
				likes.add(subscriber.getKindOf());
			}
			kws = subList.toArray(new String[subList.size()]);
			likeKindOfs = likes.toArray(new String[likes.size()]);
		}
		logger.info("kws={},likeKindOfs={}",new Gson().toJson(kws),new Gson().toJson(likeKindOfs));
		// TODO Auto-generated method stub
		
		List<Theme> recommends = themeMapper.findByRecommend(kws, likeKindOfs, size);
		if(recommends == null){
			recommends = new ArrayList<Theme>();
		}
		if(recommends.size() < size){
			int s = size - recommends.size();
			recommends.addAll(themeMapper.findByRecommend(kws, null, s));
		}
		return recommends;
	}
	
	@Override
	public List<Theme> findByUserSubscriber(Integer userId) {
		// TODO Auto-generated method stub
		return themeMapper.findByUserSubscriber(userId);
	}

}
