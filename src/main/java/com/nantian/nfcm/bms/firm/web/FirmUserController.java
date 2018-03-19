package com.nantian.nfcm.bms.firm.web;

import com.nantian.nfcm.bms.firm.service.FirmUserService;
import com.nantian.nfcm.bms.firm.vo.FirmUserBean;
import com.nantian.nfcm.util.vo.GridData;
import com.nantian.nfcm.util.vo.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/firmUser")
public class FirmUserController {
    private static Logger log = LoggerFactory.getLogger(FirmUserController.class);
    private FirmUserService firmUserService;

    @Autowired
    public FirmUserController(FirmUserService firmUserService) {
        this.firmUserService = firmUserService;
    }
    
    @RequestMapping("/findById")
    @ResponseBody
    private ResultInfo findById(HttpServletRequest request,FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            String firmUserId = firmUserBean.getFirmUsrId();
        	FirmUserBean firmUserBeanRet = firmUserService.findByid(firmUserId);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmUserBeanRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @RequestMapping("/findByCondition")
    @ResponseBody
    private ResultInfo findByCondition(int page, int size, FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            GridData<FirmUserBean> gridData = firmUserService.findByCondition(page, size, firmUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(gridData.getData());
            resultInfo.setNumber(gridData.getNumber());
            resultInfo.setPage(gridData.getPage());
            resultInfo.setTotalPage(gridData.getTotalPage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @RequestMapping("/addFirmUser")
    @ResponseBody
    private ResultInfo addUser(FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            FirmUserBean firmUserBeanRet = firmUserService.addFirmUser(firmUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmUserBeanRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @RequestMapping("/updateFirmUser")
    @ResponseBody
    private ResultInfo updateUser(FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
        	FirmUserBean firmUserBeanRet = firmUserService.updateFirmUser(firmUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmUserBeanRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @RequestMapping("/delFirmUser")
    @ResponseBody
    private ResultInfo delUser(FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
        	firmUserService.delFirmUser(firmUserBean);
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
    
    
    @RequestMapping("/resetPassword")
    @ResponseBody
    private ResultInfo resetPassword(HttpServletRequest request,FirmUserBean firmUserBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
        	FirmUserBean firmUserBeanRet = firmUserService.resetUserPwd(firmUserBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmUserBeanRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
}
