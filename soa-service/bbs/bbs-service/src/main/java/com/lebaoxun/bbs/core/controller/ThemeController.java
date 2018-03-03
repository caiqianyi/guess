package com.lebaoxun.bbs.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.bbs.core.service.IThemeService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
public class ThemeController {
	
	private IThemeService themeService;

	/**
	 * 创建主题
	 * @param kw 贴吧名
	 * @param descr 说明
	 * @param flag 状态
	 * @param kindOf 分类
	 * @param lables 标签
	 * @param logo 图标
	 * @param creator 创建人
	 * @param owner 吧主
	 * @return
	 */
	@RequestMapping(value="/theme/create",method=RequestMethod.POST)
	SuccessMessage create(@RequestParam("kw") String kw, 
			@RequestParam("descr") String descr, 
			@RequestParam("flag") Integer flag,
			@RequestParam("kindOf") String kindOf, 
			@RequestParam("lables") String lables,
			@RequestParam("logo") String logo, 
			@RequestParam("creator") Integer creator,
			@RequestParam("owner") Integer owner){
		return new SuccessMessage(themeService.create(kw, 
				descr, flag, kindOf, lables, logo, creator, owner));
	}
	
	/**
	 * 修改主题
	 * @param kw 贴吧名
	 * @param descr 说明
	 * @param kindOf 分类
	 * @param lables 标签
	 * @param logo 图标
	 * @param owner 吧主
	 * @return
	 */
	@RequestMapping(value="/theme/updateBy",method=RequestMethod.POST)
	SuccessMessage updateBy(@RequestParam("kw") String kw, 
			@RequestParam("descr") String descr,
			@RequestParam("kindOf") String kindOf, 
			@RequestParam("lables") String lables,
			@RequestParam("logo") String logo, 
			@RequestParam("owner") Integer owner){
		return new SuccessMessage(themeService.updateBy(kw, 
				descr, kindOf, lables, logo, owner));
	}
	
	/**
	 * 关注
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/subscribe",method=RequestMethod.GET)
	SuccessMessage subscribe(@RequestParam("themeId") Integer themeId, 
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(themeService.subscribe(themeId, userId));
	}
	
	/**
	 * 取消关注
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/unsubscribe",method=RequestMethod.GET)
	SuccessMessage unsubscribe(@RequestParam("themeId") Integer themeId, 
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(themeService.unsubscribe(themeId, userId));
	}
	
	/**
	 * 查询用户关注主题记录
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/findByUserId",method=RequestMethod.GET)
	SuccessMessage findByUserId(@RequestParam("themeId") Integer themeId, 
			@RequestParam("userId") Integer userId){
		return new SuccessMessage(themeService.findByUserId(themeId, userId));
	}
	
	/**
	 * 搜索主题
	 * @param kw 关键词
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/theme/search",method=RequestMethod.GET)
	SuccessMessage search(@RequestParam("kw") String kw, 
			@RequestParam("size") int size, 
			@RequestParam("offset") int offset){
		return new SuccessMessage(themeService.search(kw, size, offset));
	}
	
	/**
	 * 查询主题
	 * @param kw 关键词
	 * @return
	 */
	@RequestMapping(value="/theme/findByKw",method=RequestMethod.GET)
	SuccessMessage findByKw(@RequestParam("kw") String kw){
		return new SuccessMessage(themeService.findByKw(kw));
	}
	
	/**
	 * 查询关键词主题数
	 * @param kw 关键词
	 * @return
	 */
	@RequestMapping(value="/theme/countByKw",method=RequestMethod.GET)
	SuccessMessage countByKw(@RequestParam("kw") String kw){
		return new SuccessMessage(themeService.countByKw(kw));
	}
	
	/**
	 * 查询搜索主题分类
	 * @return
	 */
	@RequestMapping(value="/theme/findAllKindOf",method=RequestMethod.GET)
	SuccessMessage findAllKindOf(){
		return new SuccessMessage(themeService.findAllKindOf());
	}
	
	/**
	 * 查询某分类下所有主题
	 * @param kindOf 分类
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	@RequestMapping(value="/theme/findByKindOf",method=RequestMethod.GET)
	SuccessMessage findByKindOf(@RequestParam("kindOf") String kindOf,
			@RequestParam("size") Integer size,
			@RequestParam("offset") Integer offset){
		return new SuccessMessage(themeService.findByKindOf(kindOf, size, offset));
	}
}
