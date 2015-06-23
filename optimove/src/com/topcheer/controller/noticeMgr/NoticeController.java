package com.topcheer.controller.noticeMgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.noticeMgr.Notice;
import com.topcheer.model.noticeMgr.Noticemiddle;
import com.topcheer.service.noticeMgr.INoticeService;
import com.topcheer.service.noticeMgr.INoticemiddleService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/notices")
public class NoticeController {
	@Autowired
	private INoticeService noticeService;
	@Autowired
	private INoticemiddleService noticemiddleService;
	@RequestMapping("/searchNotice")
	@ResponseBody
	public Map<String, Object> searchNotice(HttpServletRequest request) {
		// 如果是超级管理员

		String grpId = (String) request.getSession().getAttribute("grpId");
		String branchCode = (String) request.getSession().getAttribute(
				"BranchCode");
		String subbranchCode = (String) request.getSession().getAttribute(
				"SubbranchCode");
		System.out.println("grpId:" + grpId + "branchCode:" + branchCode
				+ "subbranchCode:" + subbranchCode);
		Page page = Page.newBuilder(request);
		Map<String, Object> map = page.getSearchPageMap(request);
		map.put("noticebranch", branchCode);
		map.put("noticedept", subbranchCode);
		map.put("noticegroup", grpId);
		noticeService.searchNotice(map);
		return page.getPageReturn();
	}

	@RequestMapping("/getNotice")
	@ResponseBody
	public Map<String, Object> getNotice(String noticecode) {
		List<Notice> notice = noticeService.getNotice(noticecode);
		Map<String, Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, notice);
		return result;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@Valid Notice notice,
			BindingResult binding, HttpServletRequest request) {
		if (binding.hasErrors()) {
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),
					binding);
		}
		String noticebranchs = notice.getNoticebranch();
		String noticedepts = notice.getNoticedept();
		String noticegroups = notice.getNoticegroup();
		Date currentDate = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String operatetime = df.format(currentDate);
		// 如果没有选择部门 角色 和机构 则设置角色和自己一样的
		String grpId = (String) request.getSession().getAttribute(ConstantUtil.USER_GRPID);
		String branchCode = (String) request.getSession().getAttribute(ConstantUtil.BRANCHCODE);
		String subbranchCode = (String) request.getSession().getAttribute(
				ConstantUtil.SUBBRANCKCODE);
		String workId = (String) request.getSession().getAttribute(ConstantUtil.WORKID);
		if ((noticebranchs == null || noticebranchs.length() == 0)
				&& (noticedepts == null || noticedepts.length() == 0)
				&& (noticegroups == null || noticegroups.length() == 0)) {
			// 获得系统时间
			System.out.println("insert grpId:" + grpId + "insert branchCode:"
					+ branchCode + "insert subbranchCode:" + subbranchCode+"insert workId:"+workId);
			Noticemiddle noticemiddle = new Noticemiddle();
			noticemiddle.setNoticecode(notice.getNoticecode());
			noticemiddle.setNoticegroup(grpId);
			noticemiddle.setNoticebranch(branchCode);
			noticemiddle.setNoticedept(subbranchCode);
			
			
			notice.setCreaterid(workId);
			notice.setOperatetime(operatetime);
			noticeService.insert(notice);
			noticemiddleService.insert(noticemiddle);
		} else {
			String[] noticebranchArr = noticebranchs.split(",");
			String[] noticedeptArr = noticedepts.split(",");
			String[] noticegroupArr = noticegroups.split(",");
			System.out.println("insert grpIds:" + noticegroupArr + "insert branchCodes:"
					+ noticebranchArr + "insert subbranchCodes:" + noticedeptArr);
			notice.setCreaterid(workId);
			notice.setOperatetime(operatetime);
			noticeService.insert(notice);
			for (int i = 0; i < noticebranchArr.length; i++) {
				for (int j = 0; j < noticedeptArr.length; j++) {
					for (int k = 0; k < noticegroupArr.length; k++) {
						Noticemiddle noticemiddle = new Noticemiddle();
						noticemiddle.setNoticecode(notice.getNoticecode());
						noticemiddle.setNoticebranch(noticebranchArr[i]);
						noticemiddle.setNoticedept(noticedeptArr[j]);
						noticemiddle.setNoticegroup(noticegroupArr[k]);
						noticemiddleService.insert(noticemiddle);
					}
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(@Valid Notice notice,
			BindingResult binding) {
		if (binding.hasErrors()) {
			return ResultJsonUtil.analyzeError(binding);
		}
		noticeService.update(notice);
		return ResultJsonUtil.getResultMap();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String noticecodes) {
		if (noticecodes != null) {
			String[] _ids = noticecodes.split(",");
			for (String noticecode : _ids) {
				if (!noticecode.equals("")) {
					noticemiddleService.delete(noticecode);
					noticeService.delete(noticecode);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}

}
