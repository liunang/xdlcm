package com.nantian.nfcm.bms.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ReportBase extends JdbcDaoSupport
{
	public void execute(String sql)
	{
		getJdbcTemplate().execute(sql);
	}
	
	public List<Map<String, Object>> getInfoList(String sql)
	{
		List<Map<String, Object>> query = getJdbcTemplate().queryForList(sql);
		return query;
	}
	
	public SqlRowSet getInfoRowSet(String sql)
	{
		SqlRowSet query = getJdbcTemplate().queryForRowSet(sql);
		return query;
	}
}
