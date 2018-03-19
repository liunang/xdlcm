package com.nantian.nfcm.bms.firm.web;

import com.nantian.nfcm.bms.firm.service.TagService;
import com.nantian.nfcm.bms.firm.vo.TagBean;
import com.nantian.nfcm.util.vo.GridData;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tag")
public class TagController {
    private static Logger log = LoggerFactory.getLogger(TagController.class);
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
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
    private ResultInfo findById(String tagNum) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            TagBean tagBean = tagService.findById(tagNum);
            resultInfo.setSuccess("true");
            resultInfo.setData(tagBean);
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
     * @param tagBean
     * @return ResultInfo
     * @throws Exception
     */
    @RequestMapping("/findByCondition")
    @ResponseBody
    private ResultInfo findByCondition(int page, int size, TagBean tagBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            GridData<TagBean> gridData = tagService.findByCondition(page, size, tagBean);
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
