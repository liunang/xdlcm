package com.nantian.nfcm.bms.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 命名SQL语句管理，实现参数文件读取管理命名SQL语句
 * 编辑日期：2008-07
 * @author ntbms-wyj
 *
 */
public class ReportCfgUtil
{
	private static Logger logger = Logger.getLogger(ReportCfgUtil.class);
	private static ReportCfgUtil reportCfgUtil = new ReportCfgUtil();
	private Map<String,String> sqlMap = new HashMap<String,String>();

	/**
	 * ReportCfgUtil 构造方法
	 */
	private ReportCfgUtil()
	{
		_loadSqlInfo();
	}

	/**
	 * _loadSqlInfo 读取配置信息
	 */
	private void _loadSqlInfo()
	{
		Document doc = null;
		SAXReader reader = new SAXReader();
		try
		{
			doc = reader.read(getClass().getClassLoader().getResource(
					"config/ReportConfig.xml"));
			Element root = doc.getRootElement();
			Element sqlElment;

			for (Iterator it = root.elementIterator("sql"); it.hasNext();)
			{
				sqlElment = (Element) it.next();
				sqlMap.put(sqlElment.attribute("name").getText(), sqlElment
						.getText().replaceAll("\r\n", "\n").replaceAll(
								"\n[ \t]*", "\t").trim());
			}
		}
		catch (DocumentException e)
		{
			logger.error("method[getSqlInfo] 配置文件[ReportConfig.xml] 载入sql配置信息错误");
			logger.error(e);
		}
	}

	/**
	 * findSqlByName 获取命名SQL语句
	 * 
	 * @param sqlName String SQL名称
	 * @param paramMap Map<String,String> 参数及替换串
	 * @return String SQL语句
	 */
	static public String findSqlByName(String sqlName, Map<String,String> paramMap)
	{
		return reportCfgUtil._findSqlByName(sqlName, paramMap);
	}
	
	/**
	 * 获取sql
	 * @param sql
	 * @param paramMap Map<String,String>参数及替换串
	 * @return
	 */
	static public String getSqlByName(String sql, Map<String,String> paramMap)
	{
		return reportCfgUtil._getSql(sql, paramMap);
	}
	
	/**
	 * refreshSqlInfo 刷新读取配置信息
	 * @return
	 */
	static public void refreshSqlInfo()
	{
		reportCfgUtil._loadSqlInfo();
	}

	/**
	 * _findSqlByName 获取命名SQL语句
	 * 
	 * @param sqlName String SQL名称
	 * @param paramMap Map<String,String> 参数及替换串
	 * @return String SQL语句
	 */
	private String _findSqlByName(String sqlName, Map<String,String> paramMap)
	{
		String sqlStr = (String) sqlMap.get(sqlName);
		if (paramMap != null && sqlStr != null)
		{
			String valueStr = "";
			for(String keyStr : paramMap.keySet())
			{
				valueStr=(String)paramMap.get(keyStr);
				if(valueStr!=null)
				{
					sqlStr = replaceAll(sqlStr,keyStr, valueStr);
				}
			}
		}
		logger.debug("method[_findSqlByName] SQL命名["+sqlName+"] SQL语句---"+sqlStr);
		return sqlStr;
	}
	
	/**
	 * 获取sql
	 * @param sql
	 * @param paramMap Map<String,String>参数及替换串
	 * @return
	 */
	private String _getSql(String sql, Map<String,String> paramMap) {
		String sqlStr = sql;
		if (paramMap != null && sqlStr != null)
		{
			String valueStr = "";
			for(String keyStr : paramMap.keySet())
			{
				valueStr=(String)paramMap.get(keyStr);
				if(valueStr!=null)
				{
					sqlStr = replaceAll(sqlStr,keyStr, valueStr);
				}
			}
		}
		return sqlStr;
	}
	
	/**
	 * 以固定串替换原始串中的匹配串信息
	 * @param sourceStr 原始串
	 * @param keyStr 匹配串
	 * @param valueStr 固定串
	 * @return 完成替换的串
	 */
	private String replaceAll(String sourceStr,String keyStr,String valueStr)
	{
		String processStr=sourceStr;
		Pattern pattern = Pattern.compile(":"+keyStr);
		Matcher matcher = pattern.matcher(sourceStr);
		while(matcher.find())
		{
			if(matcher.end()==processStr.length() || !Character.isJavaIdentifierPart(processStr.charAt(matcher.end())))
			{
				processStr=processStr.substring(0, matcher.start())+valueStr+processStr.substring(matcher.end());
				matcher = pattern.matcher(processStr);
			}
		}
		return processStr;
	}
}
