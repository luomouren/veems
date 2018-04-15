<#macro page pageInfo url>
    <#--当前页-->
    <#local pageNo = pageInfo.pageNum>
    <#--总共有多少页-->
    <#local totalPage = pageInfo.pages>
    <#--获得一页显示的条数-->
    <#local pageSize = pageInfo.pageSize>
    <#--是否是第一页-->
    <#local isFirstPage = pageInfo.isFirstPage>
    <#--是否是最后一页-->
    <#local isLastPage = pageInfo.isLastPage>


    <#local showPages = 6>
    <ul class="pagination pagination-sm no-margin pull-right">
        <#if pageNo!=1 && totalPage gt 1>
            <li><a href="${url!}&pageNum=1">首页</a></li>
            <li><a href="${url!}&pageNum=${pageNo - 1}">上一页</a></li>
        </#if>
        <#if pageNo-showPages/2 gt 0>
            <#assign start = pageNo-(showPages-1)/2/>
            <#if showPages gt totalPage>
                <#assign start = 1/>
            </#if>
        <#else>
            <#assign start = 1/>
        </#if>
        <#if totalPage gt showPages>
            <#assign end = (start+showPages-1)/>
            <#if end gt totalPage>
                <#assign start = totalPage-showPages+1/>
                <#assign end = totalPage/>
            </#if>
        <#else>
            <#assign end = totalPage/>
        </#if>
        <#assign pages=start..end/>
        <#list pages as page>
            <#if page==pageNo>
                <li class="active"><a href="${url!}&pageNum=${page}">${page}</a></li>
            <#else>
                <li><a href="${url!}&pageNum=${page}">${page}</a></li>
            </#if>
        </#list>
        <#if pageNo!=totalPage && pageNo lt totalPage>
            <li><a href="${url!}&pageNum=${pageNo + 1}">下一页</a></li>
            <li><a href="${url!}&pageNum=${totalPage}">尾页</a></li>
        </#if>
    </ul>
</#macro>