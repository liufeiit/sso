package me.sso.ti.request;

import java.util.HashMap;
import java.util.Map;

import me.sso.ti.ro.WriteSQLRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月31日 上午11:24:07
 */
public class SQLCreateRequest {
	
	private Boolean enter = Boolean.TRUE;

	/** SQL的名称 */
	@NotEmpty(message = "SQL Property 'name' Acquired.")
	private String name;

	/** 全部SQL语句 */
	private String dap_sql;

	/** SQL的SELECT块 */
	private String sql_select_clause;

	/** SQL的FROM块 */
	private String sql_from_clause;

	/** SQL的WHERE块 */
	private String sql_where_clause;

	/** SQL的GROUP块 */
	private String sql_group_clause;

	private String sort_column_name_1;
	private String sort_policy_1;

	private String sort_column_name_2;
	private String sort_policy_2;
	
	public Map<String, String> sort() {
		Map<String, String> sort = new HashMap<String, String>();
		if(StringUtils.isNotBlank(sort_column_name_1) && StringUtils.isNotBlank(sort_policy_1)) {
			sort.put(sort_column_name_1, sort_policy_1);
		}
		if(StringUtils.isNotBlank(sort_column_name_2) && StringUtils.isNotBlank(sort_policy_2)) {
			sort.put(sort_column_name_2, sort_policy_2);
		}
		return sort;
	}
	
	public WriteSQLRequest buildRequest() {
		WriteSQLRequest request = new WriteSQLRequest();
		request.setDap_sql(dap_sql);
		request.setName(name);
		request.setSql_from_clause(sql_from_clause);
		request.setSql_group_clause(sql_group_clause);
		request.setSql_select_clause(sql_select_clause);
		request.setSql_where_clause(sql_where_clause);
		request.setSort(sort());
		return request;
	}

	public Boolean getEnter() {
		return enter;
	}

	public void setEnter(Boolean enter) {
		this.enter = enter;
	}

	public String getDap_sql() {
		return dap_sql;
	}

	public void setDap_sql(String dap_sql) {
		this.dap_sql = dap_sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql_select_clause() {
		return sql_select_clause;
	}

	public void setSql_select_clause(String sql_select_clause) {
		this.sql_select_clause = sql_select_clause;
	}

	public String getSql_from_clause() {
		return sql_from_clause;
	}

	public void setSql_from_clause(String sql_from_clause) {
		this.sql_from_clause = sql_from_clause;
	}

	public String getSql_where_clause() {
		return sql_where_clause;
	}

	public void setSql_where_clause(String sql_where_clause) {
		this.sql_where_clause = sql_where_clause;
	}

	public String getSql_group_clause() {
		return sql_group_clause;
	}

	public void setSql_group_clause(String sql_group_clause) {
		this.sql_group_clause = sql_group_clause;
	}

	public String getSort_column_name_1() {
		return sort_column_name_1;
	}

	public void setSort_column_name_1(String sort_column_name_1) {
		this.sort_column_name_1 = sort_column_name_1;
	}

	public String getSort_policy_1() {
		return sort_policy_1;
	}

	public void setSort_policy_1(String sort_policy_1) {
		this.sort_policy_1 = sort_policy_1;
	}

	public String getSort_column_name_2() {
		return sort_column_name_2;
	}

	public void setSort_column_name_2(String sort_column_name_2) {
		this.sort_column_name_2 = sort_column_name_2;
	}

	public String getSort_policy_2() {
		return sort_policy_2;
	}

	public void setSort_policy_2(String sort_policy_2) {
		this.sort_policy_2 = sort_policy_2;
	}
}