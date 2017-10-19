package com.caiqianyi.agent.account.service;

import java.util.List;

import com.caiqianyi.agent.core.entity.ServerCity;

public interface IServerCityService {
	
	/**
	 * 获得省下拉
	 * @return
	 */
	List<ServerCity> findAllProvinces();
	
	/**
	 * 根据省code，获得城市下拉
	 * @param provincecode
	 * @return
	 */
	List<ServerCity> findCityByProvince(String provincecode);
	
	/**
	 * 根据省市获得区县
	 * @param province
	 * @param citycode
	 * @return
	 */
	List<ServerCity> findAllDistrictByCity(String citycode);
	
	
	List<ServerCity> findServerProvinceByServerCode(String serverCode);
	
	
	List<ServerCity> getServerCityByCode(String serverCode);
	
	List<ServerCity> getParentServerCityByCode(String serverCode);
	
	List<ServerCity> getUpParentServerCityByCode(String serverCode);
	
	
}
