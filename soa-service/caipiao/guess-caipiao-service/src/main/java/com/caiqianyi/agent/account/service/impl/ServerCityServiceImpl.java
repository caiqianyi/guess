package com.caiqianyi.agent.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caiqianyi.agent.account.service.IServerCityService;
import com.caiqianyi.agent.core.dao.IGameServerMapper;
import com.caiqianyi.agent.core.dao.IServerCityMapper;
import com.caiqianyi.agent.core.entity.GameServer;
import com.caiqianyi.agent.core.entity.ServerCity;
import com.caiqianyi.commons.exception.I18nMessageException;

@Service
public class ServerCityServiceImpl implements IServerCityService {
	
	private Logger logger = LoggerFactory.getLogger(ServerCityServiceImpl.class);

	@Resource
	private IServerCityMapper serverCityMapper;
	
	@Resource
	private IGameServerMapper gameServerMapper;
	
	@Override
	public List<ServerCity> findAllDistrictByCity(String citycode) {
		// TODO Auto-generated method stub
		return serverCityMapper.getServerCityByLevel(citycode, 3);
	}
	
	@Override
	public List<ServerCity> findAllProvinces() {
		// TODO Auto-generated method stub
		return serverCityMapper.getServerCityByLevel(null, 1);
	}
	
	@Override
	public List<ServerCity> findCityByProvince(String provincecode) {
		// TODO Auto-generated method stub
		return serverCityMapper.getServerCityByLevel(provincecode, 2);
	}
	
	@Override
	public List<ServerCity> getParentServerCityByCode(String serverCode) {
		// TODO Auto-generated method stub
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(serverCode);
		if(gameServer == null){
			throw new I18nMessageException("500");
		}
		return serverCityMapper.getParentServerCityByCode(gameServer.getLocationCode());
	}
	
	@Override
	public List<ServerCity> getServerCityByCode(String serverCode) {
		// TODO Auto-generated method stub
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(serverCode);
		if(gameServer == null){
			throw new I18nMessageException("500");
		}
		return serverCityMapper.getServerCityByCode(gameServer.getLocationCode());
	}
	
	@Override
	public List<ServerCity> getUpParentServerCityByCode(String serverCode) {
		// TODO Auto-generated method stub
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(serverCode);
		if(gameServer == null){
			throw new I18nMessageException("500");
		}
		return serverCityMapper.getUpParentServerCityByCode(gameServer.getLocationCode());
	}
	
	@Override
	public List<ServerCity> findServerProvinceByServerCode(String serverCode) {
		// TODO Auto-generated method stub
		GameServer gameServer = gameServerMapper.findGameServerByServerCode(serverCode);
		if(gameServer == null){
			throw new I18nMessageException("500");
		}
		
		Integer level = serverCityMapper.getLevelByCode(gameServer.getLocationCode());
		List<ServerCity> serverProvinceList = new ArrayList<ServerCity>();
		logger.debug("locationCode={},serverCode={},level={}",gameServer.getLocationCode(),serverCode,level);
		switch (level) {
		case 0:
			serverProvinceList = serverCityMapper.getServerCityByLevel(gameServer.getLocationCode(), 1);//全国游戏
			break;
		case 1:
			serverProvinceList = this.serverCityMapper.getServerCityByCode(gameServer.getLocationCode());//根据省级code获取省份
			break;
		case 2:
			serverProvinceList =  this.serverCityMapper.getParentServerCityByCode(gameServer.getLocationCode());//根据市级code获取对应省份
			break;
		case 3:
			serverProvinceList = this.serverCityMapper.getUpParentServerCityByCode(gameServer.getLocationCode());//根据区县级code获取对应省份
			break;
		}
		return serverProvinceList;
	}
}
