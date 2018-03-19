package com.nantian.nfcm.app.web;

import com.nantian.nfcm.app.service.AppTagBatchService;
import com.nantian.nfcm.app.vo.AppTagBatch;
import com.nantian.nfcm.app.vo.AppTagBean;
import com.nantian.nfcm.util.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appTag")
public class AppTagController {
    private static Logger log = LoggerFactory.getLogger(AppTagController.class);

    private AppTagBatchService appTagBatchService;

    @Autowired
    public AppTagController(AppTagBatchService appTagBatchService) {
        this.appTagBatchService = appTagBatchService;
    }

    @PostMapping("/tagInit.do")
    @ResponseBody
    private ResultInfo tagInit(@RequestBody AppTagBatch appTagBatch) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            appTagBatchService.tagBatchInit(appTagBatch);
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @PostMapping("/tagActive.do")
    @ResponseBody
    private ResultInfo tagActive(@RequestBody AppTagBatch appTagBatch) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
            String errInfo = appTagBatchService.tagInfoActive(appTagBatch);
            resultInfo.setData(errInfo);
            resultInfo.setSuccess("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }

    @PostMapping("/tagCheck.do")
    @ResponseBody
    private ResultInfo tagCheck(@RequestBody AppTagBean appTagBean) throws Exception {
        ResultInfo resultInfo = new ResultInfo();
        try {
        	String tagText = appTagBean.getTagText();
        	String[] tagTexts = tagText.split("\\|",-1);
        	//正常标签进行check时应该可以split为五个值，标签num，标签key，标签密文，预留，末位
        	if(tagTexts.length==5)
        	{
        		AppTagBean appTagBeanTemp = new AppTagBean();
            	appTagBeanTemp.setTagNum(tagTexts[0]);
            	appTagBeanTemp.setTagKey(tagTexts[1]);
            	appTagBeanTemp.setTagCiphertext(tagTexts[2]);
                String str = appTagBatchService.tagInfoCheck(appTagBeanTemp);
                String imageUrl = appTagBatchService.getImageUrlByTag(appTagBeanTemp);
                String movieUrl = appTagBatchService.getMovieUrlByTag(appTagBeanTemp);
                resultInfo.setData(str);
                resultInfo.setExtData(imageUrl);
                resultInfo.setMapData(movieUrl);
                resultInfo.setSuccess("true");
        	}
        	else {
        		resultInfo.setSuccess("false");
                resultInfo.setData("该标签非茶产品指定标签。");
        	}
        	
        } catch (Exception e) {
            log.error(e.getMessage());
            resultInfo.setSuccess("false");
            resultInfo.setData(e.getMessage());
        }
        return resultInfo;
    }
}
