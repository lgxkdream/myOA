<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>TopMenu</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	<LINK href="${pageContext.request.contextPath}/style/blue/top.css" type=text/css rel=stylesheet>
	
	<script type="text/javascript">
        
    </script>
	
	<style type="text/css">
		#messageArea{
			color: white;
			font-size: 14px;
			font-weight: bold;
		}
	</style>
</head>

<body CLASS=PageBody leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
	
	<div id="Head1">
		<div id="Logo">
        	<!--<iframe name="autoRefashion" src="" width="0" height="0"></iframe>
			<a id="msgLink" href="javascript:void(0)"></a>-->
            <font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">CUST 办公自动化系统✍</font> 
			<!--<img border="0" src="css/blue/images/logo.png" />-->
        </div>
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" /> 您好，<b>${sessionScope.user.name }</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">
            	<a target="right" href="person_editUserInfoUI.action"><img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user_setup.gif" /> 个人设置</a>
			</div>
			<div id="Head1Right_Time">
				</div>
		</div>
        <div id="Head1Right_SystemButton">
            <a href="${pageContext.request.contextPath}/user_logout.action" target="_parent">
                <img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" />
            </a>
        </div>
        <div id="Head1Right_Button">
            <a target="desktop" href="javascript:void(0)"><img width="65" height="20" alt="显示桌面" src="${pageContext.request.contextPath}/style/blue/images/top/desktop.gif" /></a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                <li><a target="desktop" href="javascript:void(0)"><img border="0" width="11" height="13" src="${pageContext.request.contextPath}/style/images/top/msg.gif" /> 消息<span id="msg"></span></a></li>
                <li class="Line"></li>
                <li><a target="desktop" href="javascript:void(0)"><img border="0" width="16" height="11" src="${pageContext.request.contextPath}/style/images/top/mail.gif" /> 邮件<span id="mail"></span></a></li>
                <li class="Line"></li>
                
                <!-- 是否有待审批文档的提示1 -->
                <li><a href="${pageContext.request.contextPath}/formFlowAction_myTaskList.action" target="right">
                		<img border="0" width="12" height="14" src="${pageContext.request.contextPath}/style/images/top/wait.gif" /> 
                		待办事项（<span id="wait" class="taskListSize">0</span>）
                	</a>
                </li>
                <li class="Line"></li>
                
                <!-- 是否有待审批文档的提示2 -->
                <li id="messageArea"></li>
            </ul>
        </div>
        
        <div id="Head2_FunctionList">
			<marquee style="WIDTH:700px;" onMouseOver="this.stop()" onMouseOut="this.start()" 
				scrollamount=1 scrolldelay=30 direction=left>
				<b>☞ 欢迎访问CUST办公自动化系统！&nbsp;&nbsp;今天是
				<script type="text/javascript">
tmpDate = new Date();
date = tmpDate.getDate();
month= tmpDate.getMonth() + 1 ;
year= tmpDate.getFullYear();
document.write(year);
document.write("年");
document.write(month);
document.write("月");
document.write(date);
document.write("日 ");

myArray=new Array(6);
myArray[0]="星期日"
myArray[1]="星期一"
myArray[2]="星期二"
myArray[3]="星期三"
myArray[4]="星期四"
myArray[5]="星期五"
myArray[6]="星期六"
weekday=tmpDate.getDay();
if (weekday==0 | weekday==6)
{
document.write(myArray[weekday])
}
else
{document.write(myArray[weekday])
};
                </script>
				</b>
			</marquee>
		</div>
		<%--
        <div id="Head2_FunctionList" style="text-align: left">
        	<a href="javascript: window.parent.right.location.reload(true);">刷新</a>
        </div>
        --%>
    </div>

</body>

</html>
