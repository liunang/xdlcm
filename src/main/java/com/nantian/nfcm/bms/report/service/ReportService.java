package com.nantian.nfcm.bms.report.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.nantian.nfcm.util.ServiceException;

public interface ReportService 
{
	public SqlRowSet queryReportDataRowSet(String sqlName,Map<String,String> paramMap) throws ServiceException;
	
	public List queryReportDataList(String sqlName,Map<String,String> paramMap) throws ServiceException;

	public List queryReportDataListBySql(String sql,Map<String,String> paramMap) throws ServiceException;
}
