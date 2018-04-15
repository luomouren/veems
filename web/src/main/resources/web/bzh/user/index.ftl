<#include "/bzh/layout/layout.ftl">
<#import "/bzh/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
	function del(id) {
		layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
			$.ajax({
				type: "POST",
				dataType: "json",
				url: "/user/delete/" + id,
				success: function (res) {
					layer.msg(res.message, {time: 2000}, function () {
						location.reload();
					});
				}
			});
		});
	}

	//tableId,queryId,conditionContainer
	//var userTable;
	var winId="userWin";
	$(function() {
		//init table and fill data
		//userTable = new CommonTable("user_table", "user_list", "searchDiv");

		//button event
		$('button[data-btn-type]').click(function() {
			var action = $(this).attr('data-btn-type');
			var rowId="ceshi";
			switch (action) {
				case 'add':
					modals.openWin({
						winId:winId,
						title:'新增用户',
						width:'900px',
						url:"/user/edit/ceshi"
					});
					break;
				case 'edit':
					if(!rowId){
						modals.info('请选择要编辑的行');
						return false;
					}
					modals.openWin({
						winId:winId,
						title:'编辑用户【'+userTable.getSelectedRowData().name+'】',
						width:'900px',
						url:"/user/edit/"+rowId,
					});
					break;
				case 'delete':
					if(!rowId){
						modals.info('请选择要删除的行');
						return false;
					}
					modals.confirm("是否要删除该行数据？",function(){
						ajaxPost(basePath+"/user/delete/"+rowId,null,function(data){
							if(data.success){
								//modals.correct("已删除该数据");
								userTable.reloadRowData();
							}else{
								modals.error("用户数据被引用，不可删除！");
							}
						});
					})
					break;
			}

		});
		//form_init();
	})




</script>
</#assign>
<@layout title="用户管理" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		用户列表
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
		<li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>
		<li class="active"><i class="fa fa-table"></i> 用户列表</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Default box -->
	<div class="box box-primary">
		<div class="box-header">
        <@shiro.hasPermission name="system:user:add">
			<a class="btn btn-sm btn-success" href="/user/add">新增</a>
        </@shiro.hasPermission>
		</div>
		<div class="box-body">
			<table class="table table-striped">
				<tr>
					<th>账号</th>
					<th>用户类别</th>
					<th>真实姓名</th>
					<th>职位</th>
					<th>手机号码</th>
					<th>联系电话</th>
					<th>邮箱</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
                <#list pageInfo.list as userInfo>
                <tr>
					<td>${userInfo.userName}</td>
					<td>
                        <#if userInfo.userCategory == 1>
							<span class="label label-info">企业用户</span>
                        <#elseif userInfo.sex == 2>
                            <span class="label label-danger">个人用户</span>
                        <#else >
                            <span class="label label-warning">未知</span>
                        </#if>
					</td>
					<td>${userInfo.realName}</td>
					<td>${userInfo.positionJob}</td>
					<td>${userInfo.tel}</td>
					<td>${userInfo.cellphone}</td>
					<td>${userInfo.email}</td>
					<td>
                        <#if userInfo.isLocked>
							<span class="label label-danger">已禁用</span>
                        <#else>
                            <span class="label label-info">正常</span>
                        </#if>

					</td>
					<td>
                    <@shiro.hasPermission name="system:user:edit">
                        <a class="btn btn-sm btn-primary" href="/user/edit/${userInfo.userId}" data-btn-type="edit">编辑</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:user:grant">
                        <a class="btn btn-sm btn-warning" href="/user/grant/${userInfo.userId}">分配角色</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:user:delete">
                        <button class="btn btn-sm btn-danger" onclick="del('${userInfo.userId}')">删除</button>
                    </@shiro.hasPermission>
					</td>
				</tr>
                </#list>
			</table>
		</div>
		<!-- /.box-body -->
		<div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="/user/index?" />
		</div>
	</div>
	<!-- /.box -->

</section>
<!-- /.content -->
</@layout>