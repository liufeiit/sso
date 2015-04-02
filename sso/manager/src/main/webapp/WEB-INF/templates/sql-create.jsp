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
	<title>DAP管理平台--新建SQL</title>

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
				<h2 class="pull-left">新建SQL</h2>
				<div class="bread-crumb pull-right">
					<a href="/home/index"><i class="icon-home"></i>首页</a><span class="divider">/</span>新建SQL
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
									<div class="pull-left">新建SQL</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<div class="padd">
										<form class="form-horizontal" role="form" action="/sql/create" method="post">
											<input type="hidden" name="enter" value="false"></input>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="name">SQL名称:</label>
												<div class="col-lg-7">
													<input name="name" id="name" value="${req.name}" type="text" class="form-control" placeholder="SQL名称"></input>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-5 control-label" for="dap_sql">完整SQL:</label>
												<div class="col-lg-7">
													<textarea name="dap_sql" id="dap_sql" class="form-control" rows="3" placeholder="完整SQL">${req.dap_sql}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="sql_select_clause">SELECT块:</label>
												<div class="col-lg-7">
													<textarea name="sql_select_clause" id="sql_select_clause" class="form-control" rows="3" placeholder="SELECT块">${req.sql_select_clause}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="sql_from_clause">FROM块:</label>
												<div class="col-lg-7">
													<textarea name="sql_from_clause" id="sql_from_clause" class="form-control" rows="3" placeholder="FROM块">${req.sql_from_clause}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="sql_where_clause">WHERE块:</label>
												<div class="col-lg-7">
													<textarea name="sql_where_clause" id="sql_where_clause" class="form-control" rows="3" placeholder="WHERE块">${req.sql_where_clause}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label" for="sql_group_clause">GROUP块:</label>
												<div class="col-lg-7">
													<textarea name="sql_group_clause" id="sql_group_clause" class="form-control" rows="3" placeholder="GROUP块">${req.sql_group_clause}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label">排序策略:</label>
												<div class="col-lg-4">
													<input name="sort_column_name_1" id="sort_column_name_1" value="${req.sort_column_name_1}" type="text" class="form-control" placeholder="排序字段名称"></input>
												</div>
												<div class="col-lg-3">
													<div class="radio">
														<label><input type="radio" name="sort_policy_1" value="ASC" <c:if test="${req.sort_policy_1 == 'ASC'}"> checked </c:if>>ASC升序</label>
													</div>
													<div class="radio">
														<label><input type="radio" name="sort_policy_1" value="DESC" <c:if test="${req.sort_policy_1 == 'DESC'}"> checked </c:if>>DESC降序</label>
													</div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-5 control-label">排序策略:</label>
												<div class="col-lg-4">
													<input name="sort_column_name_2" id="sort_column_name_2" value="${req.sort_column_name_2}" type="text" class="form-control" placeholder="排序字段名称"></input>
												</div>
												<div class="col-lg-3">
													<div class="radio">
														<label><input type="radio" name="sort_policy_2" value="ASC" <c:if test="${req.sort_policy_2 == 'ASC'}"> checked </c:if>>ASC升序</label>
													</div>
													<div class="radio">
														<label><input type="radio" name="sort_policy_2" value="DESC" <c:if test="${req.sort_policy_2 == 'DESC'}"> checked </c:if>>DESC降序</label>
													</div>
												</div>
											</div>
											<hr />
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-9">
													<button type="submit" class="btn btn-default">提交</button>
												</div>
											</div>
										</form>
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