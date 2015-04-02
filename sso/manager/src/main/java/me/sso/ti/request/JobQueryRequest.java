package me.sso.ti.request;

import me.sso.ti.commons.Constants;
import me.sso.ti.ro.QueryJobRequest;
import me.sso.ti.utils.PropertyUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月2日 下午3:07:48
 */
public class JobQueryRequest implements QueryRequest {

	private Long job_id;

	/** 报表任务ID */
	private Long report_id;
	
	private Integer page;
	
	private String statuses;
	
	public QueryJobRequest buildRequest() {
		QueryJobRequest request = new QueryJobRequest();
		request.setJob_id(job_id);
		request.setReport_id(report_id);
		request.query(Constants.MAX_PAGE_SIZE, page == null ? 1 : page);
		if(StringUtils.isNotBlank(statuses)) {
			request.setStatuses(PropertyUtils.toSet(statuses));
		}
		request.setQueryString(getQuery());
		return request;
	}

	@Override
	public String getQuery() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (job_id != null) {
			sb.append("job_id").append("=").append(job_id);
			appended = true;
		}
		if (job_id != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("report_id").append("=").append(report_id);
			appended = true;
		}
		if (statuses != null && !statuses.isEmpty()) {
			if (appended) {
				sb.append("&");
			}
			sb.append("statuses").append("=").append(statuses);
			appended = true;
		}
		return sb.toString();
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public Long getJob_id() {
		return job_id;
	}

	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}