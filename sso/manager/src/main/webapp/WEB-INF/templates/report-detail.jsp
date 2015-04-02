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
	<title>DAP管理平台--报表详情</title>

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
	<jsp:include page="control/header.jsp" />
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
		<div class="mainbar">
			<div class="page-head">
				<h2 class="pull-left">报表详情</h2>
				<div class="bread-crumb pull-right">
					<a href="/home/index"><i class="icon-home"></i>首页</a><span class="divider">/</span>报表详情
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="matter">
				<div class="container">
					<!--row start-->
					<div class="row">
						<div class="col-md-12">
							<div class="widget wgreen">
								<div class="widget-head">
									<div class="pull-left">报表详情</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd form-horizontal">
										<c:if test="${!Success}">
											<div class="form-group">
												<div class="col-lg-12" style="text-align: center;">报表不存在!!!</div>
											</div>
										</c:if>
										<c:if test="${Success}">
											<div class="form-group">
												<label class="col-lg-5 control-label" >报表名称:</label>
												<div class="col-lg-7">${ResultData.name}</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="cron_expression">任务表达式:</label>
												<div class="col-lg-7">${ResultData.cron_expression}</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="sql_id">报表SQL:</label>
												<div class="col-lg-7">
													<a class="btn btn-xs btn-default" href="/sql/detail/${ResultData.sql_id}">报表SQL${ResultData.sql_id}</a>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="plugin_id">报表插件:</label>
												<div class="col-lg-7">&nbsp;${ResultData.plugin_id}&nbsp;</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="script_id">报表脚本:</label>
												<div class="col-lg-7">&nbsp;${ResultData.script_id}&nbsp;</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="name">类型:</label>
												<div class="col-lg-7">
													<c:if test="${ResultData.type == 1}"> 插件类型 </c:if>
													<c:if test="${ResultData.type == 2}"> 脚本类型 </c:if>
													<c:if test="${ResultData.type == 3}"> SQL类型 </c:if>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="name">开启任务:</label>
												<div class="col-lg-7">
													<c:if test="${ResultData.schedule == 1}"> 
														<span class="label label-success">开启</span>
													</c:if>
													<c:if test="${ResultData.schedule == 2}"> 关闭 </c:if>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" >邮件通知:</label>
												<div class="col-lg-7">${ResultData.mail_to}</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" >报表属性:</label>
												<div class="col-lg-7">
													<textarea disabled="disabled" class="form-control" rows="3" placeholder="报表属性">${ResultData.properties}</textarea>
												</div>
											</div>
											
											<hr />
											
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-9">
													<a class="btn btn-default btn-sm" href="/report/edit?report_id=${ResultData.id}">编辑</a>
												</div>
											</div>
										</c:if>
									</div>
								</div>
								<div class="widget-foot"></div>
							</div>
						</div>
					</div>
					<!--row end-->
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>

	<jsp:include page="control/copy-rights.jsp" />

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