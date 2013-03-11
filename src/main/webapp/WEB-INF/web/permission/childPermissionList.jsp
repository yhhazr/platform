<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="static/css/demo_table_jui.css" rel="stylesheet">
<script type="text/javascript">
	oTable = $('#meterDataExp').dataTable({
		"bProcessing" : true,
		"bJQueryUI" : true,
		"bSort" : false,
		"bFilter" : false,
		"sPaginationType" : "full_numbers",
		"sAjaxSource" : "permission/listChildPermission.action?permissionId=" + $("#permissionId").val(),
		"bServerSide" : true,
		"sDom" : '<"H"lr>t<"F"ip>',
		"fnServerData" : retrieveData,
		"fnRowCallback" : rowCallback,
		"aoColumns" : [ {
			"mDataProp" : "id",
			"bVisible" : false
		}, {
			"mDataProp" : "parentId"
		}, {
			"mDataProp" : "permName"
		}, {
			"mDataProp" : "permDesc"
		}, {
			"mDataProp" : "enable"
		}, {
			"mDataProp" : "menuShow"
		}, {
			"mData" : null,
			"sClass" : "center"
		} ],
		"oLanguage" : {
			"oPaginate" : {
				"sFirst" : "首页",
				"sLast" : "末页",
				"sNext" : "下页",
				"sPrevious" : "上页",
			},
			"sInfo" : "显示_START_到_END_条，共_TOTAL_条",
			"sEmptyTable" : "没有数据",
			"sInfoEmpty" : "没有数据",
			"sLengthMenu" : "每页显示 _MENU_ 条",
			"sLoadingRecords" : "正在加载...",
			"sProcessing" : "正在加载...",
			"sSearch" : "搜索"
		}
	});
	$('.dataTables_length').css("float", "right");
	$('.dataTables_length').css("width", "15%");
	$('.dataTables_length').parent().append("<div style='float:left;'><button class='btn btn-primary' id='newPermission'><i class='icon-user icon-white'></i>新增权限</button></div>");
	function rowCallback(nRow, aData, iDisplayIndex) {
		$('td:eq(5)', nRow).html("<button value="+aData.id+" class='btn btn-info'><i class='icon-edit icon-white'></i>编辑</i></button><button value="+aData.id+" class='btn btn-danger'><i class='icon-trash icon-white'></i>删除</i></button>");
	}
	function retrieveData(sSource, aoData, fnCallback) {
		var pageSize = fnGetKey(aoData, "iDisplayLength");
		var startRow = fnGetKey(aoData, "iDisplayStart");
		var echo = fnGetKey(aoData, "sEcho");
		$.ajax({
			"type" : "POST",
			"url" : sSource,
			"dataType" : "json",
			"data" : {
				pageSize : pageSize,
				startRow : startRow,
				echo : echo
			},
			"success" : function(resp) {
				fnCallback($.parseJSON(resp.result));
			}
		});
	}
	function fnGetKey(aoData, sKey) {
		for ( var i = 0, iLen = aoData.length; i < iLen; i++) {
			if (aoData[i].name == sKey) {
				return aoData[i].value;
			}
		}
		return null;
	}

	$("#newPermission").click(function() {
		$.get("permission/addPermission.action", function(data) {
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop : false
			});
		});
	});
	$(".btn-danger").die().live("click", function() {
		var id = $(this).val();
		function callback() {
			$.get("permission/deletePermission.action?permissionId=" + id, function(data) {
				if (data.result == "true") {
					oTable.fnPageChange('first');
				} else {
					alert("失败！");
				}
			});
		}
		bootstrapConfirm("确定删除吗？", callback);
	})
	$(".btn-info").die().live("click", function() {
		var id = $(this).val();
		$.get("permission/editPermission.action?permissionId=" + id, function(data) {
			$("#myModal").html(data);
			$('#myModal').modal({
				backdrop : false
			});
		});
	})
	$("#return").die().live("click",function(){
		changeContent('permission/permissionManage.action');
	})
</script>
</head>
<body>
	<s:hidden name="permissionId"></s:hidden>
	<table id="meterDataExp" width="100%" class="display">
		<thead>
			<tr>
				<th width="16%">编号</th>
				<th width="15%">父节点编号</th>
				<th width="15%">权限名称</th>
				<th width="15%">权限描述</th>
				<th width="15%">是否可用</th>
				<th width="15%">是否菜单显示</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div style="text-align: center;">
		<button class='btn btn-primary' id="return"><i class="icon-chevron-left icon-white"></i>返回</button>
	</div>
</body>
</html>