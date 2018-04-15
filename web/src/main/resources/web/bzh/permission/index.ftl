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
                url: "/permission/delete/" + id,
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
<@layout title="权限管理" active="permission">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
       权限列表
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i>权限管理</a></li>
        <li class="active"><i class="fa fa-table"></i>权限列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
		<@shiro.hasPermission name="system:permission:add">
			<a class="btn btn-sm btn-success" href="/permission/add">新增</a>
		</@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>权限名称</th>
					<th>类型</th>
					<th>权限url</th>
					<th>权限code</th>
                    <th>排序</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.list as permissionInfo>
                <tr>
                    <td>${permissionInfo.permissionName!}</td>
                    <td>
                        <#if permissionInfo.permissionType == 1>
                            <span class="label label-info">一级导航</span>
                        <#elseif permissionInfo.permissionType ==2>
                            <span class="label label-danger">二级导航</span>
                        <#elseif permissionInfo.permissionType ==3 >
                            <span class="label label-warning">操作按钮</span>
                        </#if>
                    </td>
					<td>${permissionInfo.url!}</td>
					<td>${permissionInfo.percode!}</td>
                    <td>${permissionInfo.sort!}</td>
                    <td>
                    <#if permissionInfo.isAvailable>
						<span class="label label-info">显示</span>
                    <#else>
						<span class="label label-danger">隐藏</span>
                    </#if>
                    </td>
                    <td>
                        <@shiro.hasPermission name="system:permission:edit">
                        <a class="btn btn-sm btn-primary" href="/permission/edit/${permissionInfo.permissionId!}">编辑</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="system:permission:delete">
                            <button class="btn btn-sm btn-danger" onclick="del('${permissionInfo.permissionId!}')">删除</button>
                        </@shiro.hasPermission>
					</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="/permission/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>