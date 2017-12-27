package com.caiqianyi.guess.caipiao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caiqianyi.commons.exception.I18nMessageException;
import com.caiqianyi.commons.utils.FormulaCalculate;
import com.caiqianyi.guess.caipiao.config.KindOfCons;
import com.caiqianyi.guess.caipiao.service.IGuessTemplateService;
import com.caiqianyi.guess.core.dao.GuessTemplateMapper;
import com.caiqianyi.guess.core.dao.GuessTemplateOptionMapper;
import com.caiqianyi.guess.entity.GuessTemplate;
import com.caiqianyi.guess.entity.GuessTemplateOption;

@Service
public class GuessTemplateServiceImpl implements IGuessTemplateService {
	
	private Logger logger = LoggerFactory.getLogger(GuessTemplateServiceImpl.class);
	
	@Resource
	private GuessTemplateMapper guessTemplateMappler;
	
	@Resource
	private GuessTemplateOptionMapper guessTemplateOptionMapper;
	
	@Override
	public List<GuessTemplate> findByUserId(Integer userId, String kindOf,
			String topicType) {
		// TODO Auto-generated method stub
		return guessTemplateMappler.findAllByWhere(kindOf, topicType, null, userId);
	}

	@Override
	public List<GuessTemplate> findByClubId(Integer clubId, String kindOf,
			String topicType) {
		// TODO Auto-generated method stub
		return guessTemplateMappler.findAllByWhere(kindOf, topicType, clubId, null);
	}

	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public List<GuessTemplate> copyTemplateToClub(Integer[] templateId, Integer clubId) {
		// TODO Auto-generated method stub
		
		List<GuessTemplate> templates = guessTemplateMappler.findByTemplates(templateId);
		if(templates == null || templates.isEmpty() || 
				templates.size() != templateId.length){
			throw new I18nMessageException("20101","参数错误");
		}
		
		List<GuessTemplate> gts = new ArrayList<GuessTemplate>();
		for(int i=0;i<templates.size();i++){
			GuessTemplate template = new GuessTemplate();
			template.setKindOf(templates.get(i).getKindOf());
			template.setLabel(templates.get(i).getLabel());
			template.setOptions(templates.get(i).getOptions());
			template.setStatus(templates.get(i).getStatus());
			template.setOrderBy(templates.get(i).getOrderBy());
			template.setClubId(clubId);
			template.setSubject(templates.get(i).getSubject());
			template.setTopicType(templates.get(i).getTopicType());
			template.setUserId(templates.get(i).getUserId());
			this.create(template);
			
			gts.add(template);
		}
		return gts;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public GuessTemplate create(GuessTemplate template) {
		
		if(template == null || template.getOptions() == null
				|| template.getOptions().isEmpty()){
			throw new I18nMessageException("20101","参数错误");
		}
		
		// TODO Auto-generated method stub
		GuessTemplate createTemplate = new GuessTemplate();
		createTemplate.setSubject(template.getSubject());
		createTemplate.setLabel(template.getLabel());
		createTemplate.setKindOf(template.getKindOf());
		createTemplate.setOrderBy(template.getOrderBy());
		createTemplate.setClubId(template.getClubId());
		createTemplate.setTopicType(template.getTopicType());
		createTemplate.setUserId(template.getUserId());
		createTemplate.setStatus(template.getStatus());
		
		guessTemplateMappler.insert(createTemplate);
		
		List<GuessTemplateOption> options = new ArrayList<GuessTemplateOption>();
		boolean hasSelect = false;
		for(int i=0;i<template.getOptions().size();i++){
			GuessTemplateOption option = new GuessTemplateOption(),
					gto = template.getOptions().get(i);
			option.setFormula(gto.getFormula());
			option.setName(gto.getName());
			option.setOrderBy(gto.getOrderBy());
			option.setTemplateId(createTemplate.getId());
			option.setOdds(gto.getOdds());
			String lotters[] = KindOfCons.getLotterys(template.getKindOf());
			try{
				if(FormulaCalculate.check(lotters, option.getFormula())){
					hasSelect = true;
				}
			}catch(Exception e){
				logger.debug("option.getFormula={},lotters={}",option.getFormula(),lotters);
				throw new I18nMessageException("20102","选项公式错误,无法计算结果");
			}
			guessTemplateOptionMapper.insert(option);
			options.add(option);
		}
		if(!hasSelect){
			throw new I18nMessageException("20103","没有中奖项");
		}
		createTemplate.setOptions(options);
		return createTemplate;
	}
	
	@Override
	@Transactional(readOnly=false,timeout=10,propagation=Propagation.REQUIRED)
	public int deleteBy(Integer id,Integer userId) {
		// TODO Auto-generated method stub
		int row = guessTemplateMappler.deleteByPrimaryKey(id,userId);
		row += guessTemplateOptionMapper.deleteByTemplateId(id);
		return row;
	}
	
	@Override
	public GuessTemplate update(GuessTemplate template) {
		// TODO Auto-generated method stub
		guessTemplateMappler.updateByPrimaryKeySelective(template);
		return template;
	}
	
	@Override
	public int disable(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return guessTemplateMappler.updateStatus(id, userId, 0);
	}
	
	@Override
	public int enabled(Integer id, Integer userId) {
		// TODO Auto-generated method stub
		return guessTemplateMappler.updateStatus(id, userId, 1);
	}
}
