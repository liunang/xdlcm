package com.nantian.nfcm.bms.report.web;

import java.util.HashMap;
import java.util.Map;

public class ReportBean
{
	private Map<String,String> reportParams = new HashMap<String,String>();
	private String reportSqlName;//用于导出报表的查询sql
	private String reportSqlNameCount;//用于导出报表时计算总数的sql
	private String reportSqlNameGather;//用于导出报表时统计最后一行汇总的sql
	private String japerFileLocation;
	private String reportFormat="HTML";
	protected String documentName;
	protected String contentDisposition;
	protected String delimiter;
	public Map<String, String> getReportParams()
	{
		return reportParams;
	}
	public void setReportParams(Map<String, String> reportParams)
	{
		this.reportParams = reportParams;
	}
	public String getReportSqlName()
	{
		return reportSqlName;
	}
	public void setReportSqlName(String reportSqlName)
	{
		this.reportSqlName = reportSqlName;
	}
	public String getJaperFileLocation()
	{
		return japerFileLocation;
	}
	public void setJaperFileLocation(String japerFileLocation)
	{
		this.japerFileLocation = japerFileLocation;
	}
	public String getReportFormat()
	{
		return reportFormat;
	}
	public void setReportFormat(String reportFormat)
	{
		this.reportFormat = reportFormat;
	}
	public String getDocumentName()
	{
		return documentName;
	}
	public void setDocumentName(String documentName)
	{
		this.documentName = documentName;
	}
	public String getContentDisposition()
	{
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition)
	{
		this.contentDisposition = contentDisposition;
	}
	public String getDelimiter()
	{
		return delimiter;
	}
	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	public String getReportSqlNameCount() {
		return reportSqlNameCount;
	}
	public void setReportSqlNameCount(String reportSqlNameCount) {
		this.reportSqlNameCount = reportSqlNameCount;
	}
	public String getReportSqlNameGather() {
		return reportSqlNameGather;
	}
	public void setReportSqlNameGather(String reportSqlNameGather) {
		this.reportSqlNameGather = reportSqlNameGather;
	}
	
}
