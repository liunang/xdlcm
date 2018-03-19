package com.nantian.nfcm.app.web;

import com.nantian.nfcm.app.service.AppCommentService;
import com.nantian.nfcm.app.vo.AppCommentBean;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appComment")
public class AppCommentController {
    private static Logger log = LoggerFactory.getLogger(AppTagController.class);

    private AppCommentService appCommentService;

    @Autowired
    public AppCommentController(AppCommentService appCommentService) {
        this.appCommentService = appCommentService;
    }
    
    @PostMapping("/commentSubmit.do")
    @ResponseBody
    private ResultInfo commentSubmit(@RequestBody AppCommentBean appCommentBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            appCommentService.addCommentInfo(appCommentBean);
            //resultInfo.setData(errInfo);
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

   
}
