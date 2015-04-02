<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar">
	<div class="sidebar-dropdown">
		<a href="#">导航</a>
	</div>
	<ul id="nav">
		<li>
			<a <c:if test="${ViewName == 'index'}"> class="open" </c:if> href="/home/index"><i class="icon-home"></i>首页</a>
        </li>
		<li>
			<a <c:if test="${ViewName == 'report-list'}"> class="open" </c:if> href="/report/list"><i class="icon-bar-chart"></i>报表列表</a>
		</li>
        <li>
			<a <c:if test="${ViewName == 'job-list'}"> class="open" </c:if> href="/job/list"><i class="icon-list-alt"></i>任务列表</a>
        </li>
        <li>
			<a <c:if test="${ViewName == 'sql-list'}"> class="open" </c:if> href="/sql/list"><i class="icon-magnet"></i>SQL列表</a>
        </li>
        <li>
			<a <c:if test="${ViewName == 'plugin-list'}"> class="open" </c:if> href="/plugin/list"><i class="icon-group"></i>插件列表</a>
        </li>
        <li>
			<a <c:if test="${ViewName == 'script-list'}"> class="open" </c:if> href="/script/list"><i class="icon-list-ul"></i>脚本列表</a>
        </li>
		<li>
			<a <c:if test="${ViewName == 'report-create'}"> class="open" </c:if> href="/report/create"><i class="icon-tasks"></i>新建报表</a>
		</li>
        <li>
			<a <c:if test="${ViewName == 'sql-create'}"> class="open" </c:if> href="/sql/create"><i class="icon-magic"></i>新建SQL</a>
		</li>
        <li>
			<a <c:if test="${ViewName == 'plugin-create'}"> class="open" </c:if> href="/plugin/create"><i class="icon-plus"></i>新建插件</a>
		</li>
        <li>
			<a <c:if test="${ViewName == 'script-create'}"> class="open" </c:if> href="/script/create"><i class="icon-th-large"></i>新建脚本</a>
		</li>
	</ul>
</div>