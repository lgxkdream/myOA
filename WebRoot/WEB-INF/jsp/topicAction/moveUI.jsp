<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>移动主题</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 移动主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
<s:form action="topic_move">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1">
			<div width=85% style="float:left">
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_list">论坛</s:a>
				<font class="MenuPoint"> &gt; </font>
				<s:a action="forum_show?id=%{forum.id}">${forum.name}</s:a>
				<font class="MenuPoint"> &gt;&gt; </font>
				移动主题
			</div>
		</div>
		<div class="ItemBlockBorder">
			<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
			    <s:hidden name="id" />
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">帖子主题</div></td>
					<td class="InputAreaBg"><div class="InputContent">${title}</div></td>
				</tr>
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">移动到</div></td>
					<td class="InputAreaBg">
					   <div class="InputContent">
					        <s:select name="forum.id" cssClass="SelectStyle"
                                list="#forumList" listKey="id" listValue="name"
                                 />
						    <!-- 
						    <select name="destForumId">
							    <option value="1">常见问题</option>
							    <option value="2">新功能建议</option>
							    <option value="3">灌水专区</option>
						    </select> -->
						</div>
					</td>
				</tr>
				<tr height="30">
					<td class="InputAreaBg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
						<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
					</td>
				</tr>
			</table>
		</div>
	</center>
</s:form>
</div>

<div class="Description">
	说明：<br />
	
</div>

</body>
</html>