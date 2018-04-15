<#include "/bzh/layout/layout.ftl">
<#import "/bzh/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/user/grant/${user.userId}" ,
            data: $(".form-grant").serialize(),
            success: function (res) {
                layer.msg(res.message, {time: 2000}, function () {
                    location.reload();
                });
            }
        });
    });
</script>
</#assign>
<@layout title="角色分配" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        角色分配
        <small>分配用户关联角色</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 角色分配</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-grant" method="post" action="/user/save">
                    <div class="box-body">
                        <input type="hidden" id="userId" name="userId" value="${user.userId}">
                        <div class="form-group">
                            <#list roles as role>
                                <div class="col-sm-12">
                                    <div class="checkbox i-checks">
                                        <label>
                                        <#if roleIds?seq_contains(role.roleId)>
                                            <input type="checkbox" value="${role.roleId}" name="roleIds" checked="checked"> <i></i> ${role.roleName}
                                        <#else>
                                            <input type="checkbox" value="${role.roleId}" name="roleIds"> <i></i> ${role.roleName}
                                        </#if>
                                        </label>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default btn-back">返回</button>
                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>