package com.nantian.nfcm.bms.report.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.nantian.nfcm.bms.report.ReportCfgUtil;
import com.nantian.nfcm.bms.report.dao.ReportBase;
import com.nantian.nfcm.bms.report.dao.ReportDao;


public class ReportDaoImpl extends ReportBase implements ReportDao
{
	public SqlRowSet queryReportDataRowSet(String sqlName,Map<String,String> paramMap) throws Exception
	{
		String sql=ReportCfgUtil.findSqlByName(sqlName, paramMap);
		return getInfoRowSet(sql);
	}
	
	public List queryReportDataList(String sqlName,Map<String,String> paramMap) throws Exception
	{
		String sql=ReportCfgUtil.findSqlByName(sqlName, paramMap);
//		System.out.println(sql);
		return getInfoList(sql);
	}
	
	public List queryReportDataListBySql(String sql,Map<String,String> paramMap) throws Exception
	{
		sql=ReportCfgUtil.getSqlByName(sql, paramMap);
		return getInfoList(sql);
	}
}
