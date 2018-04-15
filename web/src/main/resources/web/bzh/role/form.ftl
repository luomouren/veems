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
				dataType: "json",
				url: "/role/edit",
				data: $(".form-edit").serialize(),
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
<@layout title="角色编辑" active="role">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        角色编辑
        <small>编辑角色详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 角色管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 角色编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post" action="/role/edit">
                    <div class="box-body">
                        <input type="hidden" id="roleId" name="roleId" value="${role.roleId!}">
						<input type="hidden" id="isAdd" name="isAdd" value="${isAdd?string('true','false')}">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色名称：</label>
                            <div class="col-sm-10">
                                <input id="roleName"  required   placeholder="请输入角色名称"  name="roleName" class="form-control" type="text" value="${role.roleName!}">
                            </div>
                        </div>

						<div class="form-group">
							<label class="col-sm-2 control-label">状态：</label>
							<div class="col-sm-10">
								<select name="isAvailable" class="form-control">
									<option value=1 <#if role.isAvailable ?string('true','false')=='true'>selected="selected"</#if>>正常</option>
									<option value=0 <#if role.isAvailable ?string('true','false')=='false'>selected="selected"</#if>>已禁用</option>
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