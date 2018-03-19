package com.nantian.nfcm.bms.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ReportDao
{
	public SqlRowSet queryReportDataRowSet(String sqlName,Map<String,String> paramMap) throws Exception;
	public List queryReportDataList(String sqlName,Map<String,String> paramMap) throws Exception;
	public List queryReportDataListBySql(String sql,Map<String,String> paramMap) throws Exception;
}
