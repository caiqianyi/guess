package com.caiqianyi.agent.account.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.account.service.INoticeService;
import com.caiqianyi.agent.core.entity.Notice;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Resource
	private INoticeService noticeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{serverCode}")
	List<Notice> findActivityNotice(@PathVariable(value="serverCode")String serverCode){
		return noticeService.findActivityNotice(serverCode);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{serverCode}/{type}")
	List<Notice> findNoticeByType(@PathVariable(value="type")String type,@PathVariable(value="serverCode")String serverCode){
		return noticeService.findNoticeByType(type, serverCode);
	}
}
