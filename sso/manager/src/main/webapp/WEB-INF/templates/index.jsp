<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="author" content="andpay.me" />
	<title>DAP管理平台--首页</title>
	
	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${cdn}css/fullcalendar.css"></link>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
	<link rel="stylesheet" href="${cdn}css/rateit.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-datetimepicker.min.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery.cleditor.css"></link>
	<link rel="stylesheet" href="${cdn}css/uniform.default.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-switch.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<link rel="stylesheet" href="${cdn}css/widgets.css"></link>
	
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->
	
	<link rel="Shortcut Icon" href="http://www.andpay.me/favicon.ico" />

</head>
<body>
	<jsp:include page="control/header.jsp"/>
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
		<div class="mainbar">
			<div class="page-head">
				<h2 class="pull-left">首页</h2>
				<div class="bread-crumb pull-right"><i class="icon-home"></i>首页</div>
				<div class="clearfix"></div>
			</div>
			<div class="matter">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">任务数据统计</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd statement">
										<div class="row">
											<div class="col-md-4">
												<div class="well">
													<h2>${executingCount}</h2>
													<p><a href="/home/index?statuses=EXECUTING">正在执行的任务</a></p>
												</div>
											</div>
											<div class="col-md-4">
												<div class="well">
													<h2>${completedCount}</h2>
													<p><a href="/home/index?statuses=COMPLETED">执行成功的任务</a></p>
												</div>
											</div>
											<div class="col-md-4">
												<div class="well">
													<h2>${failedCount}</h2>
													<p><a href="/home/index?statuses=FAILED">执行失败的任务</a></p>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<hr />
												<table class="table table-striped table-hover table-responsive table-bordered">
													<thead>
														<tr>
															<th>ID</th>
															<th>报表名称</th>
															<th>任务表达式</th>
															<th>类型</th>
															<th>开启任务</th>
															<th>邮件通知</th>
															<th>状态</th>
															<th>开始时间</th>
															<th>结束时间</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${!Success}">
															<tr><td colspan="10" style="text-align: center;">空空如也!!!</td></tr>
														</c:if>
														<c:if test="${Success}">
															<c:forEach var="job" items="${ResultData}" varStatus="status">
																<tr>
																	<td>${job.id}</td>
																	<td>${job.name}</td>
																	<td>${job.cron_expression}</td>
																	<td>
																		<c:if test="${job.type == 1}"> 插件类型 </c:if>
																		<c:if test="${job.type == 2}"> 脚本类型 </c:if>
																		<c:if test="${job.type == 3}"> SQL类型 </c:if>
																	</td>
																	<td>
																		<c:if test="${job.schedule == 1}"> 
																			<span class="label label-success">开启</span>
																		</c:if>
																		<c:if test="${job.schedule == 2}"> 关闭 </c:if>
																	</td>
																	<td>${job.mail_to}</td>
																	<td>${job.status}</td>
																	<td>${job.created}</td>
																	<td>${job.modified}</td>
																	<td>
																		<div class="btn-group">
																			<c:if test="${job.download}"> 
																				<a class="btn btn-xs btn-default" href="/job/download/${job.id}">
																					下载报表
																				</a>
																			</c:if>
																		</div>
																	</td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="widget-foot">
										<ul class="pagination pull-right">
											<li><a href="/home/index?page=1&${query.queryString}">首页</a></li>
											<li><a href="/home/index?page=${query.pre_page}&${query.queryString}">上一页</a></li>
											<li><a href="/home/index?page=${query.next_page}&${query.queryString}">下一页</a></li>
										</ul>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<jsp:include page="control/copy-rights.jsp"/>
	
	<script src="${cdn}js/jquery.js"></script>
	<script src="${cdn}js/bootstrap.js"></script>
	<script src="${cdn}js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="${cdn}js/fullcalendar.min.js"></script>
	<script src="${cdn}js/jquery.rateit.min.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	<script src="${cdn}js/excanvas.min.js"></script>
	<script src="${cdn}js/jquery.flot.js"></script>
	<script src="${cdn}js/jquery.flot.resize.js"></script>
	<script src="${cdn}js/jquery.flot.pie.js"></script>
	<script src="${cdn}js/jquery.flot.stack.js"></script>
	<script src="${cdn}js/jquery.noty.js"></script>
	<script src="${cdn}js/themes/default.js"></script>
	<script src="${cdn}js/layouts/bottom.js"></script>
	<script src="${cdn}js/layouts/topRight.js"></script>
	<script src="${cdn}js/layouts/top.js"></script>
	<script src="${cdn}js/sparklines.js"></script>
	<script src="${cdn}js/jquery.cleditor.min.js"></script>
	<script src="${cdn}js/bootstrap-datetimepicker.min.js"></script>
	<script src="${cdn}js/jquery.uniform.min.js"></script>
	<script src="${cdn}js/bootstrap-switch.min.js"></script>
	<script src="${cdn}js/filter.js"></script>
	<script src="${cdn}js/custom.js"></script>
	<script src="${cdn}js/charts.js"></script>
	
</body>
</html>