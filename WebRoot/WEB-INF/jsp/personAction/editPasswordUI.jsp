<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>修改密码</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
function checkForm() {
	// 校验用户名:
	// 获得用户名文本框的值:
	var oldPassword = document.getElementById("oldPassword").value;
	if (oldPassword == null || oldPassword == '') {
		document.getElementById("span").innerHTML ="<font color='red'>请输入原密码！</font>";
		return false;
	}
		
	var password = document.getElementById("password").value;
	if (password == null || password == '') {
		document.getElementById("span").innerHTML ="<font color='red'>请输入新密码！</font>";
		return false;
	}
	if (oldPassword == password) {
		document.getElementById("span").innerHTML ="<font color='red'>原密码与新密码不能相同！</font>";
		return false;
	}

	var repassword = document.getElementById("repassword").value;
	if (repassword != password) {
		if (repassword == null || repassword == '') {
		    document.getElementById("span").innerHTML ="<font color='red'>请再次输入新密码！</font>";
	    }else{
	    	document.getElementById("span").innerHTML ="<font color='red'>两次输入的密码不一致！</font>";  	
	    }
		return false;
	}
}
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="person_editPassword" onsubmit="return checkForm();">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改密码 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr height="50">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入原密码
						</td>
						<td>
						    <s:password id="oldPassword" name="oldPassword" cssClass="InputStyle" /> *
						</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请填写新密码
						</td>
						<td>
						    <s:password id="password" name="password" cssClass="InputStyle" /> *
						</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							再次输入新密码
						</td>
						<td>
						    <s:password id="repassword" name="repassword" cssClass="InputStyle" />						</td>
					</tr>
					<tr height="5"></tr>
					<tr height="30">
					    <td colspan="2">
					        <span id="span"><s:actionmessage cssStyle="color:green;" />
					        <s:actionerror cssStyle="color:red;" /></span>
					    </td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	&nbsp;1.旧密码不能为空。<br />
	&nbsp;2.新密码不能为空。<br />
	&nbsp;3.再次输入的密码要和新密码一致。<br />
</div>

</body>
</html>