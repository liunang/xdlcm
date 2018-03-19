package com.nantian.nfcm.bms.firm.web;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.service.FirmService;
import com.nantian.nfcm.util.vo.ResultInfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(("/firm"))
public class FirmController {
    private static Logger log = LoggerFactory.getLogger(FirmController.class);
    private FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    /**
     * 根据厂商编号查询信息
     * @param firmNum
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findById")
    @ResponseBody
    private ResultInfo findById(String firmNum) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            FirmInfo firmInfoRet = firmService.findById(firmNum);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmInfoRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 根据条件分页查询厂商信息列表
     * @param page
     * @param size
     * @param firmInfo
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findByCondition")
    @ResponseBody
    private ResultInfo findByCondition(int page, int size, FirmInfo firmInfo) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            Page<FirmInfo> pages = firmService.findByCondition(page, size, firmInfo);
            resultInfo.setData(pages.getContent());
            resultInfo.setNumber(pages.getTotalElements());
            resultInfo.setPage(pages.getNumber());
            resultInfo.setTotalPage(pages.getTotalPages());
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 新增厂商信息
     * @param firmInfo
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/addFirm")
    @ResponseBody
    private ResultInfo addFirm(FirmInfo firmInfo) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            FirmInfo firmInfoRet = firmService.addFirmInfo(firmInfo);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmInfoRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 更新厂商信息
     * @param firmInfo
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/updateFirm")
    @ResponseBody
    private ResultInfo updateFirm(FirmInfo firmInfo) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            FirmInfo firmInfoRet = firmService.updateFirmInfo(firmInfo);
            resultInfo.setSuccess("true");
            resultInfo.setData(firmInfoRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 删除厂商信息
     * @param firmInfo
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/delFirm")
    @ResponseBody
    private ResultInfo delFirm(FirmInfo firmInfo) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            firmService.delFirmInfo(firmInfo);
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
    
    /**
     * 删除厂商信息
     * @param firmInfo
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/queryFirmOptions")
    @ResponseBody
    private ResultInfo queryFirmOptions() throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            List firmList= firmService.queryFirmOptions();
            resultInfo.setSuccess("true");
            resultInfo.setData(firmList);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
}
