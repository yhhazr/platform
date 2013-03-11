<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
    <a class="close" data-dismiss="modal">×</a>
    <h3>修改权限</h3>
    </div>
    <div class="modal-body">
    	<s:form id="permissionForm" enctype="multipart/form-data"  cssClass="form-horizontal">
    	<s:hidden name="permission.id"></s:hidden>
    	 <div class="control-group ">
                	<label class="control-label">父节点:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="permissionList" listKey="id" listValue="permName" name="permission.parentId"></s:select>
					</div>
		</div>
   		 <div class="control-group ">
                	<label class="control-label">权限名称:</label>
                	<div class="controls">
                		<s:textfield name="permission.permName"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">权限描述:</label>
                	<div class="controls">
						<s:textfield name="permission.permDesc"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">权限URL:</label>
                	<div class="controls">
						<s:textfield name="permission.permUrl"/>
					</div>
		</div>
   		 <div class="control-group ">
                	<label class="control-label">排序编号:</label>
                	<div class="controls">
						<s:textfield name="permission.orderId"/>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">是否启用:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="#{false:'否',true:'是'}" name="permission.enable"></s:select>
					</div>
		</div>
		<div class="control-group ">
                	<label class="control-label">是否菜单显示:</label>
                	<div class="controls">
						<s:select style="width:auto;" list="#{false:'否',true:'是'}" name="permission.menuShow"></s:select>
					</div>
		</div>
		
		</s:form>
    </div>
    <div class="modal-footer">
    <a href="javascript:void(0);" id="save" class="btn btn-primary">保存</a>
    <a href="javascript:void(0);" class="btn" data-dismiss="modal">关闭</a>
    </div>
    <script type="text/javascript">
    	$("#save").click(function(){
    		$.post("permission/editPermissionSubmit.action",$("#permissionForm").serialize(),function(data){
    			if(data.result == "true"){
    				$('#myModal').modal('hide');
    				oTable.fnPageChange( 'first' );
    			}else{
    				var errors = {
    					fieldErrors:data.fieldErrors,
    					errors:data.actionErrors
    				}
    				bootstrapValidation($("#permissionForm"),errors);
    			}
    		});
    	});
    
    </script>