package me.sso.ti.request;

import me.sso.ti.ro.QueryReportRequest;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月31日 下午4:01:58
 */
public class ReportQueryRequest implements QueryRequest {

	private Long id;

	/** 报表名称 */
	private String name;

	/** 报表数据提取类型 */
	private Byte type;

	/** 报表是否开启任务调度 */
	private Byte schedule;

	private Boolean ps = Boolean.FALSE;

	private Integer page;
	
	public QueryReportRequest buildRequest() {
		QueryReportRequest request = new QueryReportRequest();
		request.setWithProperty(getPs());
		request.query(page == null ? 1 : page);
		request.setId(getId());
		request.setName(getName());
		request.setSchedule(getSchedule());
		request.setType(getType());
		request.setQueryString(getQuery());
		return request;
	}

	@Override
	public String getQuery() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (id != null) {
			sb.append("id").append("=").append(id);
			appended = true;
		}
		if (name != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("name").append("=").append(name);
			appended = true;
		}
		if (type != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("type").append("=").append(type);
			appended = true;
		}
		if (schedule != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("schedule").append("=").append(schedule);
			appended = true;
		}
		if (ps != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("ps").append("=").append(ps);
			appended = true;
		}
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Boolean getPs() {
		return ps;
	}

	public void setPs(Boolean ps) {
		this.ps = ps;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}