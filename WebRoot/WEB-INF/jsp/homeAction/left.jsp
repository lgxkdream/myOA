<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>导航菜单</title>
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
    <script language="JavaScript" src="${pageContext.request.contextPath}/script/menu.js"></script>
    <!-- <script type="text/javascript">
        function menuClick( menu ){
        	$(menu).next().toggle();
        }
    </script> -->
</head>
<body style="margin: 0">
<div id="Menu">

    <ul id="MenuUl">
        <!-- 显示一级菜单 -->
        <s:iterator value="#application.topPrivilegeList">
        <s:if test="#session.user.hasPrivilegeById(id)">
        <li class="level1">
            <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/FUNC${id}.gif" class="Icon" /> ${name}</div>
            
            <ul style="display: none;" class="MenuLevel2">
                <!-- 显示二级菜单 -->
                <s:iterator value="children">
                <s:if test="#session.user.hasPrivilegeById(id)">
                <li class="level2">
                    <s:if test="%{url.indexOf('http')>=0}">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="${url}"> ${name}</a></div>
                    </s:if>
                    <s:else>
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="${pageContext.request.contextPath}${url}.action"> ${name}</a></div>
                    </s:else>
                </li>
                </s:if>
                </s:iterator>
            </ul>
        </li>
        </s:if>
        </s:iterator>
        
    </ul>
    
</div>
</body>
</html>

