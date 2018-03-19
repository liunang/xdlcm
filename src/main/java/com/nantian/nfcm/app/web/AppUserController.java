package com.nantian.nfcm.app.web;

import com.nantian.nfcm.app.service.AppUserService;
import com.nantian.nfcm.app.vo.AppUserBean;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
    private static Logger log = LoggerFactory.getLogger(AppUserController.class);

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/sysUserLogin.do")
    @ResponseBody
    private ResultInfo sysUserLogin(@RequestBody AppUserBean appUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            AppUserBean appUserBeanRet = appUserService.confirmSysUser(appUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(appUserBeanRet.getFirmInfos());
            resultInfo.setPromptMsg(appUserBeanRet.getUserName());
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @PostMapping("/firmUserLogin.do")
    @ResponseBody
    private ResultInfo firmUserLogin(@RequestBody AppUserBean appUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            AppUserBean appUserBeanRet = appUserService.confirmFirmUser(appUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(appUserBeanRet.getAppProductBeana());
            resultInfo.setExtData(appUserBeanRet.getAppTagBeans());
            resultInfo.setPromptMsg(appUserBeanRet.getUserName());
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
}
