<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>表单模板列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 模板管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="250px">模板名称</td>
				<td width="250px">所用流程</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="documentTemplateList">
          <s:iterator value="#documentTempList">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td>${processKey}&nbsp;</td>
				<td><a onClick="return delConfirm()" href="#">删除</a>
					<a href="saveUI.html">修改</a>
					<s:if test="url != null">
					    <s:a action="documentTemplate_download?id=%{id}">下载</s:a>
				    </s:if>
				    <s:else>
				        <span style="color: gray;cursor: pointer;">下载</span>
				    </s:else>
				</td>
			</tr>
		  </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
			<s:a action="documentTemplate_addUI"><img src="${pageContext.request.contextPath}/style/blue/images/button/addNew.PNG" /></s:a>
        </div>
    </div>
</div>

</body>
</html>