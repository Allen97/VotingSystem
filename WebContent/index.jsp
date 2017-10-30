<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<script type="text/javascript" src="jquery/jquery-3.1.1.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootbox/bootbox.min.js"></script>
<script type="text/javascript">
	function addHero() {
		var uname = $("#uname_modal").val();
		if (uname == null) {
			alert("请添加投票人物");
		} else {
			$.ajax({
				url : "addHeroServlet",
				data : {
					"uname" : uname
				},
				method : "post",

				async : false,
				success : function(result) {
					if (result == 'true') {
						alert("添加人物成功");
						//window.location.href = "findTickerServlet";
					} else
						alert("投票人物失败");
				},
				error : function() {
					alert("请求服务器失败");
				}
			});
		}

	}
	$(function() {
		/* 	$("#btn_add").click(function(){
				$("#myModal").modal("show");
			}); */
		$("#btn_submit").click(function() {
			var radio = $("input:radio[name='id']:checked").val();
			if (radio == null) {
				bootbox.alert({
					message : "请添加人物",
					size : "small"
				});
			} else {
				$.ajax({
					url : "findTickerServlet",
					data : {
						"id" : radio
					},
					method : "post",

					async : false,
					success : function(result) {
						if (result == 'true') {
							bootbox.alert({
								message : "投票成功",
								size : "small"
							});
							//window.location.href = "findTickerServlet";
						} else
							bootbox.alert({
								message : "投票失败",
								size : "small"
							});
					},
					error : function() {
						alert("请求服务器失败");
					}
				});
			}

		});
	});
</script>
</head>
<body>
	<c:set var="sum1" value="${sessionScope.hashMap.sumTicker[0].sum}"></c:set>
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4>
					<span class="glyphicon glyphicon-sort-by-attributes"></span> 三国英雄投票
				</h4>
			</div>
			<div class="panel-body">
				<div class="well">
					<a id="btn_add" class="btn btn-primary" href="" data-toggle="modal"
						data-target="#myModal"><span
						class="glyphicon glyphicon-sort-by-attributes"></span> 添加人物</a> <a
						id="btn_update" class="btn btn-warning" href=""><span
						class="glyphicon glyphicon-sort-by-attributes"></span> 修改人物</a> <a
						id="btn_delete" class="btn btn-success" href=""><span
						class="glyphicon glyphicon-sort-by-attributes"></span> 删除人物</a>
				</div>
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<td>ID</td>
						<td>用户名</td>
						<td>票数</td>
						<td>进度条</td>
					</tr>
					<c:forEach items="${sessionScope.hashMap.tickerAll}" var="row">
						<tr>
							<td><input type="radio" name="id" value="${row.id }" /></td>
							<td>${row.uname }</td>
							<td>${row.number }/${sum1}</td>
							<td>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										style="width:${row.number.toString()/sum1.toString()*200}px;">
										<span> <fmt:formatNumber maxFractionDigits="2"> ${row.number.toString()/sum1.toString()*100}</fmt:formatNumber>
											% 完成
										</span>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="footer text-right">
				<a id="btn_submit" class="btn btn-success" href=""><span
					class="glyphicon glyphicon-sort-by-attributes"></span> 投票</a>
			</div>

		</div>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加英雄人物</h4>
				</div>
				<div class="modal-body">
					<input id="uname_modal" name="uname" placeholder="请输入投票英雄名称"
						class="form-control">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:addHero()">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>





</body>
</html>