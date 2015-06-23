package com.topcheer.controller;

import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.User;
import com.topcheer.model.UserGroup;
import com.topcheer.service.IUserGroupService;
import com.topcheer.service.IUserService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserGroupService userGroupService;

    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
    	logger.info("invoke login...");
        String result = "login";
        String workIdParm = StringUtil.repNullToStr(request.getParameter(ConstantUtil.WORKID));
        String passwordParm = StringUtil.repNullToStr(request.getParameter(ConstantUtil.PASSWORD));
        String branchCodeParm = StringUtil.repNullToStr(request.getParameter(ConstantUtil.BRANCHCODE));
        if(workIdParm==null||"".equals(workIdParm)){
        	 return "login";
        }
        User user = userService.getUser(workIdParm);
        if(user==null){
       	 return "login";
       }
        if (workIdParm != null && !"".equals(workIdParm) && user == null) {
            request.setAttribute("userError", "�û�������");
            request.setAttribute(ConstantUtil.WORKID, workIdParm);
            return "login";
        }
        //�ж��û������������Ƿ���ڻ�����Ϣ����
        NewBranchInfo branchInfo = userService.getBranchInfo(user.getUserBankCode());
        if (branchInfo == null || !branchCodeParm.equals(user.getUserBankCode())){
            request.setAttribute("userError", "�û���������������");
            request.setAttribute(ConstantUtil.WORKID, workIdParm);
            return "login";
        }
        
        if (!passwordParm.equals(user.getUserPwd() == null ? "" : user.getUserPwd())) {
            request.setAttribute("passwordError", "���벻��ȷ");
            request.setAttribute(ConstantUtil.WORKID, workIdParm);
            return "login";
        }
        if (passwordParm != null && passwordParm.equals(user.getUserPwd() == null ? "" : user.getUserPwd())) {
            result = "index";
            request.getSession().setAttribute(ConstantUtil.IS_LOGIN, true);
            request.getSession().setAttribute(ConstantUtil.LOGIN_USER, user);
            request.getSession().setAttribute(ConstantUtil.WORKID, workIdParm);
            request.getSession().setAttribute(ConstantUtil.OPERATORNAME, user.getUserLoginName());
            request.getSession().setAttribute(ConstantUtil.BRANCHCODE, user.getUserBankCode());
            String subName = branchInfo.getName().replace("�ַ�����", "");
            subName = subName.replace("�ַ�", "");
            request.getSession().setAttribute(ConstantUtil.BRANCHNAME, ConstantUtil.NC_BANK_NAME+subName);
            request.getSession().setAttribute(ConstantUtil.SUBBRANCKCODE, branchInfo.getBranchCode());
            request.getSession().setAttribute(ConstantUtil.SUBBRANCKNAME, branchInfo.getName());
            List<UserGroup> list = userGroupService.getUserGroup(workIdParm);
            if (list != null && list.size() > 0) {
                request.getSession().setAttribute(ConstantUtil.USER_GRPID, list.get(0).getGrpId());
            }
        } else if (request.getSession().getAttribute(ConstantUtil.IS_LOGIN) != null) {
            return "index";
        }

        return result;
    }

    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request) {
    	logger.info("invoke logOut...");
        request.getSession().removeAttribute(ConstantUtil.USER_GRPID);
        request.getSession().removeAttribute(ConstantUtil.IS_LOGIN);
        return "login";
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IUserGroupService getUserGroupService() {
        return userGroupService;
    }

    public void setUserGroupService(IUserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }


}
