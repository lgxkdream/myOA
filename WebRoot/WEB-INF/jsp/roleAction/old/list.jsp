<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Role_list.jsp</title>
	</head>

	<body>
		list.jsp<br/>
		<s:iterator value="#roleList">
		    <s:property value="id"/>&nbsp;&nbsp;
		    <s:property value="name"/>&nbsp;&nbsp;
		    <s:property value="description"/>&nbsp;&nbsp;
		    <s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
		    <s:a action="role_editUI?id=%{id}">修改</s:a><br/>
		</s:iterator>
		<s:a action="role_addUI">添加</s:a>
	</body>
</html>
