<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>个人信息</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="headimgForm" action="person_editHeadimg" enctype="multipart/form-data" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 个人信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
                        <td width="150">用户ID（登录名）</td>
                        <td>${loginName}</td>
						<td rowspan="5" align="right">
							<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/${headimg}" 
								onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
						</td>
                    </tr>
                    <tr>
                        <td>部门</td>
                        <td>${department.name}</td>
                    </tr>
					<tr>
                        <td>岗位</td>
                        <td>
                            <s:iterator value="roles">
                               ${name}&nbsp;
                            </s:iterator>
                        </td>
                    </tr>
					<tr>
                        <td>姓名</td>
                        <td>${name}</td>
                    </tr>
					<tr>
                        <td>头像</td>
                        <td>
                            <s:file name="resource" cssClass="InputStyle" cssStyle="width: 400px;" onchange="javascript:document.getElementById('headimgForm').submit();" accept=".jpg, .jpeg, .gif, .png" />
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
	&nbsp;1.可以修改自已的头像，在右侧是头像的预览。<br />
</div>

</body>
</html>