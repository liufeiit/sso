package me.sso.ti.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.sso.ti.ro.WriteSQLRequest;
import me.sso.ti.vo.SQL;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月31日 上午11:24:07
 */
public class SQLUpdateRequest {
	
	private Boolean enter = Boolean.TRUE;
	
	private Long sql_id;

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
	
	public void mergeSQL(SQL sql) {
		setDap_sql(sql.getDap_sql());
		setName(sql.getName());
		setSql_from_clause(sql.getSql_from_clause());
		setSql_group_clause(sql.getSql_group_clause());
		setSql_select_clause(sql.getSql_select_clause());
		setSql_where_clause(sql.getSql_where_clause());
		setSort(sql.getSort());
		setSql_id(sql.getId());
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
		request.setId(sql_id);
		return request;
	}
	
	public void setSort(Map<String, String> sort) {
		if(sort == null || sort.isEmpty()) {
			return;
		}
		List<Entry<String, String>> sortEntry = new ArrayList<Entry<String, String>>(sort.entrySet());
		Entry<String, String> entry1 = sortEntry.get(0);
		sort_column_name_1 = entry1.getKey();
		sort_policy_1 = entry1.getValue();
		int size = sortEntry.size();
		if(size == 2) {
			Entry<String, String> entry2 = sortEntry.get(1);
			sort_column_name_2 = entry2.getKey();
			sort_policy_2 = entry2.getValue();
		}
	}

	public Long getSql_id() {
		return sql_id;
	}

	public void setSql_id(Long sql_id) {
		this.sql_id = sql_id;
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