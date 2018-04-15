<#include "/bzh/layout/layout.ftl">
<#import "/bzh/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
    function del(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/role/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
</#assign>
<@layout title="角色管理" active="role">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        角色列表
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 角色管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 角色列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <@shiro.hasPermission name="system:role:add">
            <a class="btn btn-sm btn-success" href="/role/add">新增</a>
        </@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>角色名称</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.list as roleInfo>
                <tr>
					<td>${roleInfo.roleName}</td>
					<td>
                        <#if roleInfo.isAvailable >
							<span class="label label-info">正常</span>
                        <#else>
                            <span class="label label-danger">已禁用</span>
						</#if>
                    </td>
                    <td>
                    <@shiro.hasPermission name="system:role:edit">
                        <a class="btn btn-sm btn-primary" href="/role/edit/${roleInfo.roleId!}">编辑</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:grant">
                        <a class="btn btn-sm btn-warning" href="/role/grant/${roleInfo.roleId!}">分配资源</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:delete">
                        <button class="btn btn-sm btn-danger" onclick="del('${roleInfo.roleId!}')">删除</button>
					</@shiro.hasPermission>
                    </td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="/role/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>