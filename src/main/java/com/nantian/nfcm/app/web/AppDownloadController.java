package com.nantian.nfcm.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nantian.nfcm.util.entity.Param;
import com.nantian.nfcm.util.service.ParamService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/appDownload")
public class AppDownloadController {
    private static Logger log = LoggerFactory.getLogger(AppUserController.class);
    private static String fileSavePath="fileSavePath";

    private ParamService paramService;
    @Autowired
    public AppDownloadController(ParamService paramService) {
        this.paramService = paramService;
    }
    /**  
     * 文件下载  
     *   
     * @param savePath  
     *            保存目录  
     * @param name  
     *            文件原名  
     * @param file  
     *            保存时的名称 包含后缀  
     * @param request  
     * @param response  
     * @return  
     */  
    @RequestMapping(value="/down",method=RequestMethod.GET)
    public String down(String fileName,String productNum,HttpServletRequest request,
            HttpServletResponse response)
    {  
		try {
			Param fileSavePathParam = paramService.findParamById(fileSavePath);
			String savePath = fileSavePathParam.getParamValue() + "/" + productNum + "/" + fileName;

			File file = new File(savePath);
			String name = file.getName();
			if (!file.exists()) {
				// 不存在
				request.setAttribute("name", name);
				return "download_error";// 返回下载文件不存在
			}
			response.setContentType("video/mp4");
			// 根据不同浏览器 设置response的Header
			String userAgent = request.getHeader("User-Agent").toLowerCase();

			if (userAgent.indexOf("msie") != -1) {
				// ie浏览器
				// System.out.println("ie浏览器");
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));

			} else {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + new String(name.getBytes("utf-8"), "ISO8859-1"));
			}

			response.addHeader("Content-Length", "" + file.length());
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(savePath));
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
			byte[] buffer = new byte[102400];
			
			// response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			OutputStream bos = new BufferedOutputStream(toClient);
			// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(bos));
			while(fis.read(buffer)>0) {
				bos.write(buffer);
				//Thread.sleep(1000);
			}
			
			// bw.close();
			fis.close();
			//bos.close();
			toClient.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";// 返回异常页面
		} finally {
			/*
			 * if (toClient != null) { try { toClient.close(); } catch (IOException e) {
			 * e.printStackTrace(); } }
			 */

		}
    }  
    
    
}
