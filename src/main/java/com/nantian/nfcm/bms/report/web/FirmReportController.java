package com.nantian.nfcm.bms.report.web;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantian.nfcm.bms.firm.entity.FirmInfo;
import com.nantian.nfcm.bms.firm.service.FirmService;
import com.nantian.nfcm.bms.firm.vo.FirmBean;
import com.nantian.nfcm.util.DataUtil;
import com.nantian.nfcm.util.DateUtil;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.service.ParamService;
import com.nantian.nfcm.util.vo.LoginBean;
import com.nantian.nfcm.util.vo.ResultInfo;

@Controller
@RequestMapping(("/firmReport"))
public class FirmReportController
{
	private static Logger log = Logger.getLogger(FirmReportController.class.getName());
	private FirmService firmService;
	private ParamService paramService;
	private String excelPath = "";//指定到服务器某个文件路径
	@Autowired
	public FirmReportController(FirmService firmService,ParamService paramService) {
	     this.firmService = firmService;
	     this.paramService = paramService;
	}

	private static final String path = System.getProperty("bmsApp.root");
	
	private static final int limit = 500;
	
	private static final int startRowNum = 3;
	
	private static final String fileSavePath = "fileSavePath";
	
    @RequestMapping("/poiFirmReport")
    @ResponseBody
	public ResultInfo poiFirmReport(FirmBean firmBean ,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	ResultInfo resultInfo = new ResultInfo();
		try
		{
			String time = DateUtil.getCurrentTime("yyyyMMddHHmmss");
			String savePath = paramService.findParamById(fileSavePath).getParamValue();
			if(!new File(savePath).exists())
				new File(savePath).mkdirs();
			String fileName = "firmReport"+time+".xlsx";
			String path = savePath+File.separator+fileName;
			LoginBean loginBean=(LoginBean)request.getSession().getAttribute("loginInfo");
			//LoginBean loginBean=(LoginBean)session.get("loginInfo")
			File file = new File(path);
			
			FileOutputStream out = new FileOutputStream(file);
			String formatTime = DateUtil.getFormatTime(time);
			getExportExcel(out,firmBean,formatTime,loginBean);
			
			//downloadFile(file,request,response);
			//file.delete();
			resultInfo.setSuccess("true");
            resultInfo.setData(fileName);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return resultInfo;
		
			
	}
	
    @RequestMapping("/downloadExcel")
   // @ResponseBody
    public void downloadExcel(String fileName,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String path = "";
		String savePath = paramService.findParamById(fileSavePath).getParamValue();
		if(excelPath.equals(""))
		{
			path = savePath+File.separator+fileName;
		}
		else
		{
			path = excelPath + fileName;
		}
		File file = new File(path);
		downloadFile(file,request,response);
		
		DataUtil.deleteFileOrFolder(file);
	}
 
	private void downloadFile(File file,HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		// 因为本action没有经过filter，所以需手动转码
		String agent = request.getHeader("USER-AGENT");
		String filename = file.getName();
		if (agent != null && agent.indexOf("MSIE") != -1)
		{
			filename = java.net.URLEncoder.encode(filename, "UTF-8");
		}
		else if (agent != null && agent.indexOf("Mozilla") != -1)
		{
			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
		}

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + filename);

		RandomAccessFile raf = null;
		FileChannel fcin = null;
		OutputStream out = null;
		WritableByteChannel cout = null;
		try
		{
			raf = new RandomAccessFile(file, "r");
			fcin = (raf.getChannel());

			out = response.getOutputStream();
			cout = Channels.newChannel(out);

			ByteBuffer bbuf = ByteBuffer.allocateDirect(10240);

			while (true)
			{
				bbuf.clear();
				if (fcin.read(bbuf) == -1)
				{
					break;
				}
				bbuf.flip();
				cout.write(bbuf);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (fcin != null)
			{
				fcin.close();
			}
			if (raf != null)
			{
				raf.close();
			}
			if (cout != null)
			{
				cout.close();
			}
			if (out != null)
			{
				out.close();
			}
		}
	}
	/**
	 * 通过数据库查询后导出excel报表
	 * 
	 * @throws Exception
	 */
	public void getExportExcel(FileOutputStream out,FirmBean firmBean,String time,LoginBean loginBean) throws Exception
	{
		try
		{
				String title ="厂商信息统计表";
				String[] headerNames = {"厂商编号","厂商名称","联系人","电话号码","手机号码","传真","电子邮箱","地址","注册时间","备注"};
				//厂商编号#厂商名称#联系人#电话号码#手机号码#传真#电子邮箱#地址#注册时间#备注
				//String path = "";
				//LoginBean loginBean=(LoginBean)session.get("loginInfo");
				
				//OutputStream out = response.getOutputStream();
				
				getExcelFile(title, headerNames, out,firmBean,time,loginBean.getRealName());
	
				//getExcelFile(out,firmBean, true, getSql());
		}
		catch (Exception e)
		{
			log.error(e);
		}
	}
	

	protected void getExcelFile(String title,String[] headerNames,FileOutputStream out, FirmBean firmBean,String time,String operater)
	{
		String sheetName = "exportData";
		Workbook workbook = null;
		try
		{
			Sheet sheet = null;
			workbook = new XSSFWorkbook();
			sheet= workbook.createSheet(sheetName);
			createExcel(workbook, sheet, title,headerNames,firmBean,time,operater);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (ServiceException e) {
			log.error(e.getMessage());
		} finally {
			if(out != null)
			{
				try
				{
					out.flush();
					out.close();
				}
				catch (IOException e)
				{
					log.error(e.getMessage());
				}
			}
		}
	}
	
	private void createExcel(Workbook workbook, Sheet sheet, String title,String[] headerNames,FirmBean firmBean,String time,String operater) throws ServiceException
	{
		Row row = null;
		Cell cell = null;
		//设置内容风格
		CellStyle cellContextStyle = getContextCellStyle(workbook);
		//设置TITTLE风格
		CellStyle cellTittleStyle = getTittleCellStyle(workbook);
		//设置HEADER风格
		CellStyle cellHeaderStyle = getHeaderCellStyle(workbook);
		//Map<String, String> paramMap = firmBean.getReportParams();
		//String header="厂商编号#厂商名称#联系人#电话号码#手机号码#传真#电子邮箱#地址#注册时间#备注";
		//String[] headerNames = header.split("#");
		int headerCount = headerNames.length;
		//String title="厂商信息统计表";
		
		//生成标题
		row = sheet.createRow(0);
		cell= row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(cellTittleStyle);
		//sheet.setColumnWidth(0, 3766);//设置列宽:第一个参数代表列id(从0开始),第2个参数代表宽度值  参考 ："2012-08-10"的宽度为2500
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,headerCount>=2?headerCount:0));
				
		row = sheet.createRow(1);
		cell= row.createCell(0);
		cell.setCellValue("【操作人员】："+operater+" 【时间】 : "+time);
		CellStyle otherContext = getOtherCellStyle(workbook);
		cell.setCellStyle(otherContext);
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,headerCount>=2?headerCount:0));
		//生成表格头
		row = sheet.createRow(2);
		cell= row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(cellHeaderStyle);
		for(int cellNum = 0; cellNum < headerCount; cellNum++)
        {
			sheet.autoSizeColumn(cellNum+1, true);
			cell= row.createCell(cellNum+1);
			cell.setCellValue(headerNames[cellNum]);
			cell.setCellStyle(cellHeaderStyle);
        }
		
		
		//生成内容
		int dataSize = 0;
		
		//if(limitQuery) {
			int seq = startRowNum;
			int page = 0;
			int size = limit;
			//paramMap.put("startRownum", page+"");
			//paramMap.put("endRownum", size+"");
			//System.out.println(sql);
			//List list = reportService.queryReportDataListBySql(sql, paramMap);
			FirmInfo firmInfo = new FirmInfo();
			String firmNum=firmBean.getFirmNum();
			String firmName=firmBean.getFirmName();
			if(firmNum!=null&&!"".equals(firmNum))
			{
				firmInfo.setFirmNum(firmNum);
			}
			if(firmName!=null&&!"".equals(firmName))
			{
				firmInfo.setFirmName(firmName);
			}
			Page<FirmInfo> pages = firmService.findByCondition(page, size, firmInfo);
			List<FirmInfo> list = pages.getContent();
			if(list!=null && list.size()>0) {
				do {
					int tmpSize = list.size();
					for(int rowNum = 0; rowNum < tmpSize; rowNum++)
					{
						FirmInfo firmInfoTemp= list.get(rowNum);
						//Map dataMap = (Map) list.get(rowNum);
						row = sheet.createRow(seq);
						cell= row.createCell(0);
						cell.setCellValue(seq-startRowNum+1);
						seq++;
						//"厂商编号#厂商名称#联系人#电话号码#手机号码#传真#电子邮箱#地址#注册时间#备注";
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(1);
						cell.setCellValue(firmInfoTemp.getFirmNum());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(2);
						cell.setCellValue(firmInfoTemp.getFirmName());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(3);
						cell.setCellValue(firmInfoTemp.getContact());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(4);
						cell.setCellValue(firmInfoTemp.getTelephone());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(5);
						cell.setCellValue(firmInfoTemp.getMobilePhone());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(6);
						cell.setCellValue(firmInfoTemp.getFax());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(7);
						cell.setCellValue(firmInfoTemp.getEmail());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(8);
						cell.setCellValue(firmInfoTemp.getAddress());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(9);
						cell.setCellValue(firmInfoTemp.getFirmDate());
						cell.setCellStyle(cellContextStyle);
						
						cell.setCellStyle(cellContextStyle);
						cell= row.createCell(10);
						cell.setCellValue(firmInfoTemp.getRemark());
						cell.setCellStyle(cellContextStyle);
						/*Iterator iter = dataMap.entrySet().iterator();
						int cellNum = 0;
						int cellSize = dataMap.size();
						while (iter.hasNext()) {
						    Entry entry = (Entry) iter.next(); 
						    //Object key = entry.getKey(); 
						    String val = entry.getValue()==null?"":entry.getValue()+"";
						    cell= row.createCell(cellNum+1);
							cell.setCellValue(val);
							cell.setCellStyle(cellContextStyle);
							cellNum++;
							if(cellNum >= (cellSize-2))
								break;
						} */
					}
					page++;
					//start = start+end;
					//end = end+limit;
					//paramMap.put("startRownum", start+"");
					//paramMap.put("endRownum", end+"");
					//list = reportService.queryReportDataListBySql(sql, paramMap);
					list = firmService.findByCondition(page, size, firmInfo).getContent();
				} while(list!=null && list.size()>0);
			}
			else {
				//无数据
				row = sheet.createRow(startRowNum);
				cell= row.createCell(0);
				cell.setCellValue("无报表数据！");
				cell.setCellStyle(cellContextStyle);
				CellRangeAddress cra=new CellRangeAddress(3,3,0,headerCount>=2?headerCount:0);
				sheet.addMergedRegion(cra);
				setRegionStyle(sheet,cra,cellContextStyle);
			}
		/*}
		else {
			//List list = reportService.queryReportDataListBySql(sql, paramMap);
			dataSize = list.size();
			for(int rowNum = 0; rowNum < dataSize; rowNum++)
			{
				Map dataMap = (Map) list.get(rowNum);
				row = sheet.createRow(rowNum+3);
				cell= row.createCell(0);
				cell.setCellValue(rowNum+1);
				cell.setCellStyle(cellContextStyle);
				Iterator iter = dataMap.entrySet().iterator();
				int cellNum = 0;
				while (iter.hasNext()) {
				    Entry entry = (Entry) iter.next(); 
				    //Object key = entry.getKey(); 
				    String val = entry.getValue()==null?"":entry.getValue()+"";
				    cell= row.createCell(cellNum+1);
					cell.setCellValue(val);
					cell.setCellStyle(cellContextStyle);
					cellNum++;
				} 
			}
		}*/
	}
	/**
	 * 合并单元格后补全边框
	 * @param sheet
	 * @param region
	 * @param cs
	 */
	public static void setRegionStyle(Sheet sheet, CellRangeAddress region,
            CellStyle cs) {

        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {

            Row row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);

            }
        }
    }
	/**
	 * 设置标题风格
	 * @param workbook
	 * @return
	 */
	private static CellStyle getTittleCellStyle(Workbook workbook)
	{
		CellStyle cellTittleStyle = workbook.createCellStyle();
		cellTittleStyle.setFillForegroundColor((short)55);//HSSFColor.GREY_40_PERCENT.index);// 设置背景色 
		cellTittleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellTittleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		Font tittleFont = workbook.createFont();
		tittleFont.setFontHeightInPoints((short) 26);//设置字体大小
		cellTittleStyle.setFont(tittleFont);
		return cellTittleStyle;
	}
	/**
	 * 设置列名风格
	 * @param workbook
	 * @return
	 */
	private static CellStyle getHeaderCellStyle(Workbook workbook)
	{
		CellStyle cellHeaderStyle = workbook.createCellStyle();
		cellHeaderStyle.setFillForegroundColor((short)22);//HSSFColor.GREY_25_PERCENT.index);// 设置背景色 
		cellHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		cellHeaderStyle.setBorderBottom((short)1); //下边框
		cellHeaderStyle.setBorderLeft((short)1);//左边框
		cellHeaderStyle.setBorderTop((short)1);//上边框
		cellHeaderStyle.setBorderRight((short)1);//右边框
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 10);//设置字体大小
		cellHeaderStyle.setFont(headerFont);
		return cellHeaderStyle;
	}
	/**
	 * 设置内容风格
	 * @param workbook
	 * @return
	 */
	private static CellStyle getContextCellStyle(Workbook workbook)
	{
		CellStyle cellContextStyle = workbook.createCellStyle();
		cellContextStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		cellContextStyle.setBorderBottom((short)1); //下边框
		cellContextStyle.setBorderLeft((short)1);//左边框
		cellContextStyle.setBorderTop((short)1);//上边框
		cellContextStyle.setBorderRight((short)1);//右边框
		Font contextFont = workbook.createFont();
		contextFont.setFontHeightInPoints((short) 10);//设置字体大小
		cellContextStyle.setFont(contextFont);
		return cellContextStyle;
	}
	/**
	 * 设置其它风格
	 * @param workbook
	 * @return
	 */
	private static CellStyle getOtherCellStyle(Workbook workbook)
	{
		CellStyle cellTimeStyle = workbook.createCellStyle();
		cellTimeStyle.setAlignment(CellStyle.ALIGN_RIGHT); // 靠右
		Font timeFont = workbook.createFont();
		timeFont.setFontHeightInPoints((short) 10);//设置字体大小
		cellTimeStyle.setFont(timeFont);
		return cellTimeStyle;
	}
	private static String getTimeStr(String time)
	{
		return time.replace(" ", "").replace("-", "").replace(":", "");
	}
	
}
