package com.caiqianyi.agent.account.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.IServerCityService;
import com.caiqianyi.agent.core.entity.ServerCity;

@RestController
@RequestMapping("/serverCity")
public class ServerCityController {
	
	@Resource
	private IServerCityService serverCityService;
	
	/**
	 * 获得省下拉
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/provinces")
	List<ServerCity> provinces(){
		return serverCityService.findAllProvinces();
	}
	
	/**
	 * 根据省code，获得城市下拉
	 * @param provincecode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/citys/{provincecode}")
	List<ServerCity> citys(@PathVariable(value="provincecode")String provincecode){
		return serverCityService.findCityByProvince(provincecode);
	}
	
	/**
	 * 根据省市获得区县
	 * @param province
	 * @param citycode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/districts/{citycode}")
	List<ServerCity> districts(@PathVariable(value="citycode")String citycode){
		return serverCityService.findAllDistrictByCity(citycode);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getServerCityByCode/{serverCode}")
	List<ServerCity> getServerCityByCode(@PathVariable(value="serverCode")String serverCode){
		return serverCityService.getServerCityByCode(serverCode);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getParentServerCityByCode/{serverCode}")
	List<ServerCity> getParentServerCityByCode(@PathVariable(value="serverCode")String serverCode){
		return serverCityService.getParentServerCityByCode(serverCode);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getUpParentServerCityByCode/{serverCode}")
	List<ServerCity> getUpParentServerCityByCode(@PathVariable(value="serverCode")String serverCode){
		return serverCityService.getUpParentServerCityByCode(serverCode);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findServerProvinceByServerCode/{serverCode}")
	List<ServerCity> findServerProvinceByServerCode(@PathVariable(value="serverCode")String serverCode){
		return serverCityService.findServerProvinceByServerCode(serverCode);
	}
}
