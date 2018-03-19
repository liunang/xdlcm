package com.nantian.nfcm.ca.web;

import com.nantian.nfcm.ca.service.KeyInfoService;
import com.nantian.nfcm.ca.vo.KeyBean;
import com.nantian.nfcm.util.vo.GridData;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/key")
public class KeyInfoController {
    private static Logger log = LoggerFactory.getLogger(KeyInfoController.class);
    private KeyInfoService keyInfoService;

    @Autowired
    public KeyInfoController(KeyInfoService keyInfoService) {
        this.keyInfoService = keyInfoService;
    }

   
    /**
     * 根据条件分页查询密钥信息
     *
     * @param page
     * @param size
     * @param keyBean
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findByCondition")
    @ResponseBody
    private ResultInfo findByCondition(int page, int size, KeyBean keyBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            GridData<KeyBean> gridData = keyInfoService.findByCondition(page, size, keyBean);
            resultInfo.setSuccess("true");
            resultInfo.setData(gridData.getData());
            resultInfo.setNumber(gridData.getNumber());
            resultInfo.setPage(gridData.getPage());
            resultInfo.setTotalPage(gridData.getTotalPage());
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

   
}
