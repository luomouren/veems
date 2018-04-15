<#include "/bzh/layout/layout.ftl">
<#import "/bzh/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    $(".btn-submit").click(function () {

		// 校验表单合法
		var bootstrapValidator = $(".form-edit").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			$.ajax({
				type: "POST",
				url: "/permission/edit",
				data: $(".form-edit").serialize(),
				dataType: "JSON",
				success: function(res){
					layer.msg(res.message, {time: 2000
					}, function(){
						location.reload();
					});
				}
			});
		}else{
			return false;
		}
    });

	$('.form-edit').bootstrapValidator({
		message: '请输入有效值',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		}
	});
</script>
</#assign>
<@layout title="权限编辑" active="permission">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       权限编辑
        <small>编辑权限详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i>权限管理</a></li>
        <li class="active"><i class="fa fa-edit"></i>权限编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" method="post" action="/permission/edit">
                    <div class="box-body">
						<input type="hidden" id="isAdd" name="isAdd" value="${isAdd?string('true','false')}">
                        <input type="hidden" id="permissionId" name="permissionId" value="${permissionInfo.permissionId!}">
                        <input type="hidden" id="parentPermissionId" name="parentPermissionId" value="${permissionInfo.parentPermissionId!}">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">权限名称：</label>
                            <div class="col-sm-10">
                                <input id="permissionName" required name="permissionName" class="form-control" type="text" value="${permissionInfo.permissionName!}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">权限类型：</label>
                            <div class="col-sm-10">
                                <select name="permissionType" class="form-control">
                                    <option value="1" <#if permissionInfo.permissionType == 1>selected="selected"</#if>>一级导航</option>
                                    <option value="2" <#if permissionInfo.permissionType == 2>selected="selected"</#if>>二级导航</option>
                                    <option value="3" <#if permissionInfo.permissionType == 3>selected="selected"</#if>>操作按钮</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">权限url：</label>
                            <div class="col-sm-10">
                                <input id="url" name="url" required class="form-control" value="${permissionInfo.url!}">
                            </div>
                        </div>

						<div class="form-group">
							<label class="col-sm-2 control-label">权限code：</label>
							<div class="col-sm-10">
								<input id="percode" name="percode" required class="form-control" type="text" value="${permissionInfo.percode!}">
							</div>
						</div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">排序：</label>
                            <div class="col-sm-10">
                                <input id="sort" name="sort" required class="form-control" value="${permissionInfo.sort!}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">状态：</label>
                            <div class="col-sm-10">
								<select name="isAvailable" class="form-control">
									<option value=1 <#if permissionInfo.isAvailable ?string('true','false')=='true'>selected="selected"</#if>>显示</option>
									<option value=0 <#if permissionInfo.isAvailable ?string('true','false')=='false'>selected="selected"</#if>>隐藏</option>
								</select>
                            </div>
                        </div>

                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default btn-back">返回</button>
                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
                    </div>
                </form>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>