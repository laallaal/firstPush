<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>Y先生教育</title>

		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

		<!--[if lt IE 9]>
      	<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

		<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/confirm.js"></script>
		<script type="text/javascript">
		function showAddPage(){
			location.href="${pageContext.request.contextPath}/speaker/showAddSpeaker";
		}
		function delSpeakerById(Obj,id,name){
			//alert(id);
		 Confirm.show('温馨提示：', '确定要删除'+name+'么？', {
                'Delete': {
                    'primary': true,
                    'callback': function() {
                    	var param={"id":id};

                    	$.post("${pageContext.request.contextPath}/speaker/delById",param,function(data){
                    		if(data=='success'){
                    		 Confirm.show('温馨提示：', '删除成功');
                    		 $(Obj).parent().parent().remove();
                    		}else{
                    		 Confirm.show('温馨提示：', '操作失败');
                    		}
                    	});
                    }
                }
            });
		}
		</script>
		<style type="text/css">
    	  th{
    	  	 text-align: center;
    	  }
        </style>
	</head>

	<body>
		<nav class="navbar-inverse">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">

				<a class="navbar-brand" href="${pageContext.request.contextPath}/video/list">视频管理系统</a>
			</div>

			<div class="collapse navbar-collapse"
				 id="bs-example-navbar-collapse-9">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
					<li  class="active"><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
					<li><a href="${pageContext.request.contextPath}/course/list">课程管理</a></li>
				</ul>

				<p class="navbar-text navbar-right">
					<span>${sessionScope.ADMINUSER}</span> &nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/admin/showLogin" class="glyphicon glyphicon-log-in" aria-hidden="true"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/admin/showLogin" class="navbar-link">退出</a>
				</p>
			</div>
			<!-- /.navbar-collapse -->


		</div>
		<!-- /.container-fluid -->
	 </nav>
    
    <div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
	  <div class="container">
	          <h2>主讲人管理</h2>
	  </div>
	</div>
	
	
	<div class="container">
		
	    
	    <button onclick="showAddPage()" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      添加
		</button>
		
	</div>
	
	<div class="container" style="margin-top: 20px;">
		
		<table class="table table-bordered table-hover" 
		   style="text-align: center;table-layout:fixed;">
      <thead >
        <tr class="active">
          <th>序号</th>
          <th>名称</th>
          <th>职位</th>
          <th style="width:60%;">简介</th>
          <th>编辑</th>
          <th>删除</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${pageInfo.list}" var="speaker" varStatus="status">
            <tr>
	          <td>${status.index+1}</td>
	          <td>${speaker.speakerName}</td>
	          
	          <td>${speaker.speakerJob}</td>
	          <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${speaker.speakerDesc}</td>
	          <td><a href="${pageContext.request.contextPath}/speaker/edit/${speaker.id}"><span class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
	          <td><a href="#" onclick="return delSpeakerById(this,'${speaker.id}','${speaker.speakerName}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
           </tr>
        
        </c:forEach>
        
        
        </tbody>
      </table>
	</div>
	<div class="container">
		<div class="navbar-right">
			<nav aria-label="Page navigation">
				<ul class="pagination">

					<li>
						<a  href="${pageContext.request.contextPath}/speaker/list?page=1&pageSize=${pageInfo.pageSize}" aria-label="Previous">
							<span aria-hidden="true">首页</span>
						</a>
					</li>

					<li>
						<a onclick="toProPage()" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" >
						<li><a href="${pageContext.request.contextPath}/speaker/list?page=${pageNum}&pageSize=${pageInfo.pageSize}">${pageNum}</a></li>
					</c:forEach>


					<li>
						<a onclick="toNextPage()"  aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>

					<li>
						<a href="${pageContext.request.contextPath}/speaker/list?page=${pageInfo.pages}&pageSize=${pageInfo.pageSize}" aria-label="Previous">
							<span aria-hidden="true">末页</span>
						</a>
					</li>

				</ul>
			</nav>
		</div>
	</div>
  </body>

<script>


	function toProPage() {
		var prePage = ${pageInfo.prePage};

		var MaxPage = ${pageInfo.pages};
		//alert(MaxPage);
		//alert("prePage:"+prePage);

		if (prePage == 0) {

			prePage = MaxPage;
			//alert(prePage)
		}

		//alert(prePage);
		location.href = "${pageContext.request.contextPath}/speaker/list?page="+prePage+"&pageSize=${pageInfo.pageSize}"
	}


	function toNextPage() {
		var netPage = ${pageInfo.nextPage};

		if (netPage == 0) {
			netPage = 1;
		}

		location.href = "${pageContext.request.contextPath}/speaker/list?page="+netPage+"&pageSize=${pageInfo.pageSize}"
}


</script>





</html>

















