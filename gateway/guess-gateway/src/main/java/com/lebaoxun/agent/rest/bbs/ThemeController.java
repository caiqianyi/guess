package com.lebaoxun.agent.rest.bbs;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.account.entity.User;
import com.lebaoxun.agent.security.Oauth2SecuritySubject;
import com.lebaoxun.bbs.core.service.IThemeService;
import com.lebaoxun.commons.exception.SuccessMessage;

@RestController
@RequestMapping("/bbs")
public class ThemeController {

	@Resource
	private IThemeService themeService;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	
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
			@RequestParam("kindOf") String kindOf, 
			@RequestParam("lables") String lables,
			@RequestParam("logo") String logo){
		User user = oauth2SecuritySubject.getCurrentUser();
		return themeService.create(kw, descr, 0, kindOf, lables, logo, user.getUserId(), user.getUserId());
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
			@RequestParam("logo") String logo){
		User user = oauth2SecuritySubject.getCurrentUser();
		return themeService.updateBy(kw, descr, kindOf, lables, logo, user.getUserId());
	}
	
	/**
	 * 关注
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/subscribe",method=RequestMethod.GET)
	SuccessMessage subscribe(@RequestParam("themeId") Integer themeId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return themeService.subscribe(themeId, user.getUserId());
	}
	
	/**
	 * 取消关注
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/unsubscribe",method=RequestMethod.GET)
	SuccessMessage unsubscribe(@RequestParam("themeId") Integer themeId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return themeService.unsubscribe(themeId, user.getUserId());
	}
	
	/**
	 * 查询用户关注主题记录
	 * @param themeId 主题名
	 * @param userId 用户
	 * @return
	 */
	@RequestMapping(value="/theme/findByUserId",method=RequestMethod.GET)
	SuccessMessage findByUserId(@RequestParam("themeId") Integer themeId){
		User user = oauth2SecuritySubject.getCurrentUser();
		return themeService.findByUserId(themeId, user.getUserId());
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
		return themeService.search(kw, size, offset);
	}
	
	/**
	 * 查询主题
	 * @param kw 关键词
	 * @return
	 */
	@RequestMapping(value="/theme/findByKw",method=RequestMethod.GET)
	SuccessMessage findByKw(@RequestParam("kw") String kw){
		return themeService.findByKw(kw);
	}
	
	/**
	 * 查询关键词主题数
	 * @param kw 关键词
	 * @return
	 */
	@RequestMapping(value="/theme/countByKw",method=RequestMethod.GET)
	SuccessMessage countByKw(@RequestParam("kw") String kw){
		return themeService.countByKw(kw);
	}
	
	/**
	 * 查询搜索主题分类
	 * @return
	 */
	@RequestMapping(value="/theme/findAllKindOf",method=RequestMethod.GET)
	SuccessMessage findAllKindOf(){
		return themeService.findAllKindOf();
	}
	
	/**
	 * 查询某分类下所有主题
	 * @param kindOf 分类
	 * @param size 分页大小
	 * @param offset 偏移量
	 * @return
	 */
	SuccessMessage findByKindOf(@RequestParam("kindOf") String kindOf,
			@RequestParam("size") Integer size,
			@RequestParam("offset") Integer offset){
		return themeService.findByKindOf(kindOf, size, offset);
	}
}
