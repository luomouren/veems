<#include "/bzh/layout/layout.ftl">
<#import "/bzh/layout/macro.ftl" as macro>
<#assign css>
<#--此ftl中用到的css-->
</#assign>
<#assign js>
<#--此ftl中用到的js-->
<script>
	$(".btn-submit").click(function () {
		// 校验表单合法
		var bootstrapValidator = $(".form-edit").data('bootstrapValidator');
		bootstrapValidator.validate();
		if(bootstrapValidator.isValid()){
			$.ajax({
				type: "POST",
				url: "/user/edit",
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
<@layout title="用户编辑" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		用户编辑
		<small>编辑用户详细信息</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
		<li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>
		<li class="active"><i class="fa fa-edit"></i> 用户编辑</li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<!-- Default box -->
			<div class="box  box-primary">
				<form class="form-horizontal form-edit" method="post" action="user/edit">
					<div class="box-body">
						<input type="hidden" id="userId" name="userId" value="${user.userId!}">
						<input type="hidden" id="isAdd" name="isAdd" value="${isAdd?string('true','false')}">
						<input type="hidden" id="salt" name="salt" value="${user.salt!}">

						<div class="form-group">
							<label class="col-sm-2 control-label">账号：</label>
							<div class="col-sm-10">
								<input required placeholder="请输入账号" id="userName" name="userName" class="form-control" type="text" value="${user.userName!}" <#if !isAdd> readonly="readonly"</#if> >
							</div>
						</div>

						<#if isAdd>
							<div class="form-group">
								<label class="col-sm-2 control-label">密码：</label>
								<div class="col-sm-10">
									<input required placeholder="请输入密码" id="password" name="password" class="form-control" type="password" value="${user.password!}">
								</div>
							</div>
						<#else>
							<input type="hidden" id="password" name="password" value="${user.password!}">
						</#if>


						<div class="form-group">
							<label class="col-sm-2 control-label">用户类别：</label>
							<div class="col-sm-10">
								<select name="userCategory" class="form-control">
									<option value=1 <#if user.userCategory == 1>selected="selected"</#if>>企业用户</option>
									<option value=2 <#if user.userCategory == 2>selected="selected"</#if>>个人用户</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">真实姓名：</label>
							<div class="col-sm-10">
								<input  required placeholder="请输入真实姓名" id="realName" name="realName" class="form-control" type="text" value="${user.realName!}">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">职位：</label>
							<div class="col-sm-10">
								<input   placeholder="请输入职位" id="positionJob" name="positionJob" class="form-control" type="text" value="${user.positionJob!}">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">手机号码：</label>
							<div class="col-sm-10">
								<input   placeholder="请输入手机号码" id="tel" name="tel" class="form-control" value="${user.tel!}">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话：</label>
							<div class="col-sm-10">
								<input   placeholder="请输入联系电话" id="cellphone" name="cellphone" class="form-control" value="${user.cellphone!}">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱：</label>
							<div class="col-sm-10">
								<input  placeholder="请输入邮箱" d="email" name="email" class="form-control" value="${user.email!}">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">状态：</label>
							<div class="col-sm-10">
								<select name="isLocked" class="form-control">
									<option value=1 <#if user.isLocked ?string('true','false')=='true'>selected="selected"</#if>>禁用</option>
									<option value=0 <#if user.isLocked ?string('true','false')=='false'>selected="selected"</#if>>正常</option>
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
