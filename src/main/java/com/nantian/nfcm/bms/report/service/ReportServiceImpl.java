package com.nantian.nfcm.bms.report.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.nantian.nfcm.bms.report.dao.ReportDao;
import com.nantian.nfcm.util.ServiceException;

public class ReportServiceImpl implements ReportService{
    
	private ReportDao reportDao;

    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }
	
	private String excelType = "xls";//EXCEL 文件格式，先默认所有报表都是xls。

	/*查询报表数据SqlRowSet集合*/
	public SqlRowSet queryReportDataRowSet(String sqlName,Map<String,String> paramMap) throws ServiceException
	{
		SqlRowSet reportData=null;
		try
		{
			reportData=reportDao.queryReportDataRowSet(sqlName, paramMap);
		}
		catch(Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		return reportData;
	}
	
	/*查询报表数据list*/
	public List queryReportDataList(String sqlName,Map<String,String> paramMap) throws ServiceException
	{
		if(sqlName!=null && sqlName.trim().length()>0)
		{
			try
			{
				return reportDao.queryReportDataList(sqlName, paramMap);
			}
			catch(Exception e)
			{
				throw new ServiceException(e.getMessage());
			}
		}
		else
		{
			throw new ServiceException("reportSqlName["+sqlName+"]");
		}
	}

	
	
	public List queryReportDataListBySql(String sql,Map<String,String> paramMap) throws ServiceException
	{
		if(sql!=null && sql.trim().length()>0)
		{
			try
			{
				return reportDao.queryReportDataListBySql(sql, paramMap);
			}
			catch(Exception e)
			{
				throw new ServiceException(e.getMessage());
			}
		}
		else
		{
			throw new ServiceException("reportSql["+sql+"] is null");
		}
	}
	
}
