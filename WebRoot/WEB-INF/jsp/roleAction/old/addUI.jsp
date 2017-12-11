<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>role_addUI.jsp</title>
	</head>

	<body>
		addUI.jsp<br/>
		<s:form action="role_add">
		    <s:textfield name="name"></s:textfield><br/>
		    <s:textarea name="description"></s:textarea><br/>
		    <s:submit value="提交"></s:submit>
		</s:form>
		
	</body>
</html>
