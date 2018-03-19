package com.nantian.nfcm.bms.firm.web;

import com.nantian.nfcm.bms.firm.service.TagBatchService;
import com.nantian.nfcm.bms.firm.vo.TagBatchBean;
import com.nantian.nfcm.util.vo.GridData;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tagBatch")
public class TagBatchController {
    private static Logger log = LoggerFactory.getLogger(TagBatchController.class);
    private TagBatchService tagBatchService;

    @Autowired
    public TagBatchController(TagBatchService tagBatchService) {
        this.tagBatchService = tagBatchService;
    }

    /**
     * 根据标签编号查询标签信息
     *
     * @param tagNum
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findById")
    @ResponseBody
    private ResultInfo findById(Long batchId) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            TagBatchBean tagBatchBean = tagBatchService.findById(batchId);
            resultInfo.setSuccess("true");
            resultInfo.setData(tagBatchBean);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    /**
     * 根据条件分页查询标签信息
     *
     * @param page
     * @param size
     * @param tagBatchBean
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findByCondition")
    @ResponseBody
    private ResultInfo findByCondition(int page, int size, TagBatchBean tagBatchBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            GridData<TagBatchBean> gridData = tagBatchService.findByCondition(page, size, tagBatchBean);
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
