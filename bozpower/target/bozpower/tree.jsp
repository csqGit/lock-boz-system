<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>导航树</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css">
@import url('components/dtree/dtree.css');
</style>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/components/dtree/dtree.css">
<script type="text/javascript">var dtreeIconBasePath = "components/dtree";</script>
<script type="text/javascript"
	src="<%=path%>/components/dtree/dtree.js"></script>
<style>
* {
	background: none repeat scroll 0 0 transparent;
	border: 0 none;
	margin: 0;
	padding: 0;
	vertical-align: baseline;
	font-family: 微软雅黑;
	overflow: hidden;
}

#menuControll {
	width: 100%;
	position: relative;
	word-wrap: break-word;
	border-bottom: 1px solid #065FB9;
	margin: 0;
	padding: 0 10px;
	font-size: 14px;
	height: 40px;
	line-height: 40px;
	vertical-align: middle;
	background-image: -moz-linear-gradient(top, #388E8E, #40E0D0);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #388E8E),
		color-stop(1, #40E0D0));
}

.dtree {
	margin: 3px 0 0 0;
	padding: 0;
	font-size: 13px;
}

.dTreeNode {
	margin: 10px 3px 0 5px;
}

.dtree a.node, .dtree a.nodeSel {
	letter-spacing: 2px;
}
</style>

</head>
<body>
	<script type="text/javascript"> 
var treeMenu = [
	{ level:1, name:"历史记录"},
	{ level:2, name:"开锁记录", ico:"<%=path%>/images/icon_default.gif",
		link:"<%=path%>/pcAdmin/selectLogList?page=1"},
			
	{ level:1, name:"变电站管理"},
	{ level:2, name:"变电站列表", ico:"<%=path%>/images/icon_default.gif",
		link:"<%=path%>/pcTransformersub/selectTransformersubList?page=1"},
	{ level:2, name:"新增变电站", ico:"<%=path%>/images/icon_default.gif",
			link:"<%=path%>/transformersub/transformersub_add.jsp"},
	
	{ level:1, name:"智能锁管理"},
	{ level:2, name:"智能锁列表", ico:"<%=path%>/images/icon_default.gif",
		link:"<%=path%>/pcLock/selectLockList?page=1"},
	{ level:2, name:"新增智能锁", ico:"<%=path%>/images/icon_default.gif",
			link:"<%=path%>/pcLock/requestType?param=add"},
	
	{ level:1, name:"用户管理"},
	{ level:2, name:"用户查询", ico:"<%=path%>/images/icon_default.gif",
			link:"<%=path%>/pcUser/selectUserList?page=1"},
	{ level:2, name:"新增用户", ico:"<%=path%>/images/icon_default.gif",
				link:"<%=path%>/pcUser/requestType?param=add"},
	
	{ level:1, name:"系统设置"},
	{ level:2, name:"管理员查询", ico:"<%=path%>/images/icon_default.gif",
		link:"<%=path%>/pcAdmin/selectAdminList?page=1"},
	{ level:2, name:"新增管理员", ico:"<%=path%>/images/icon_default.gif",
			link:"<%=path%>/pcAdmin/requestType?param=add"
				}

		];
	</script>

	<div id="menuControll">
		菜单【<a href="#" onclick="tree.openAll();this.blur();return false;"
			style="color: #333333; text-decoration: none">展开</a>】 【<a href="#"
			onclick="tree.closeAll();this.blur();return false;"
			style="color: #333333; text-decoration: none">折叠</a>】
	</div>
	<div class="dtree" style="margin: 10px;">
		<script type="text/javascript">
			//建立新树
			tree = new dTree('tree');
			tree.config.target = "MainFrame";
			tree.config.useCookies = false;
			var selNum = -1;
			var link = "";
			//根目录
			tree.add(0, -1, '管理中心', null, null, null, '', '');
			var count = 0;
			var pLevelIdArray = new Array();
			pLevelIdArray[1] = 0;
			var currLevel = 1;
			for (var i = 0; i < treeMenu.length; i++) {
				var item = treeMenu[i];
				var itemLevel = item.level;
				pLevelIdArray[itemLevel + 1] = ++count;
				if (item.link != null && item.link != "") {
					if (item.ico != null) {//表示二级菜单
						tree.add(count, pLevelIdArray[itemLevel], item.name,
								item.link, null, null, item.ico, item.ico);
					} else {
						tree.add(count, pLevelIdArray[itemLevel], item.name,
								item.link);
					}
				} else {//表示一级菜单
					if (item.ico != null) {
						tree.add(count, pLevelIdArray[itemLevel], item.name,
								null, null, null, item.ico, item.ico);
					} else {
						tree.add(count, pLevelIdArray[itemLevel], item.name);
					}
				}
				if (item.select) {
					selNum = count;
					link = item.link;
				}
			}
			document.write(tree);
			//tree.openAll();
			tree.closeAll();

			if (selNum != -1) {
				tree.openTo(selNum, true);
				top.document.frames["MainFrame"].location.href = link;
			}
		</script>
	</div>
</body>
</html>