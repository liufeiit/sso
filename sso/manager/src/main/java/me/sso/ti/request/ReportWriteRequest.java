package me.sso.ti.request;

import me.sso.ti.ro.WriteReportRequest;
import me.sso.ti.utils.PropertyUtils;
import me.sso.ti.vo.Report;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月31日 下午4:27:18
 */
public class ReportWriteRequest {
	
	private Boolean enter = Boolean.TRUE;
	
	private Long report_id;

	/** 报表名称 */
	@NotEmpty(message = "SQL Property 'name' Acquired.")
	private String name;

	/** 报表任务调度表达式 */
	private String cron_expression;

	/** 报表插件 */
	private Long plugin_id;

	/** 报表脚本 */
	private Long script_id;

	/** 报表SQL */
	private Long sql_id;

	/** 报表数据提取类型 */
	private Byte type;

	/** 报表是否开启任务调度 */
	private Byte schedule;

	/** 报表邮件通知地址 */
	private String mail_to;

	/** 报表属性 */
	private String properties;
	
	public WriteReportRequest buildRequest() {
		WriteReportRequest request = new WriteReportRequest();
		request.setCron_expression(cron_expression);
		request.setMail_to(mail_to);
		request.setName(name);
		request.setPlugin_id(plugin_id);
		request.setSchedule(schedule);
		request.setScript_id(script_id);
		request.setSql_id(sql_id);
		request.setType(type);
		request.setProperties(PropertyUtils.toMap(properties));
		request.setId(report_id);
		return request;
	}
	
	public void mergeReport(Report report) {
		setCron_expression(report.getCron_expression());
		setMail_to(report.getMail_to());
		setName(report.getName());
		setPlugin_id(report.getPlugin_id());
		setSchedule(report.getSchedule());
		setScript_id(report.getScript_id());
		setSql_id(report.getSql_id());
		setType(report.getType());
		setProperties(PropertyUtils.toString(report.getProperties()));
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}

	public Boolean getEnter() {
		return enter;
	}

	public void setEnter(Boolean enter) {
		this.enter = enter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCron_expression() {
		return cron_expression;
	}

	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}

	public Long getPlugin_id() {
		return plugin_id;
	}

	public void setPlugin_id(Long plugin_id) {
		this.plugin_id = plugin_id;
	}

	public Long getScript_id() {
		return script_id;
	}

	public void setScript_id(Long script_id) {
		this.script_id = script_id;
	}

	public Long getSql_id() {
		return sql_id;
	}

	public void setSql_id(Long sql_id) {
		this.sql_id = sql_id;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getSchedule() {
		return schedule;
	}

	public void setSchedule(Byte schedule) {
		this.schedule = schedule;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}
}