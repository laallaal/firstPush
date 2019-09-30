<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">

    <!--表示使用IE最新的渲染模式进行解析-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--
    	兼容一些移动设备，会根据屏幕的大小缩放
    	width=device-width  表示宽度是设备的宽度（很多手机的宽度都是980px）
    	initial-scale=1  初始化缩放级别   1-5
    	minimum-scale=1  maximum-scale=5
    	user-scalable=no  禁止缩放
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加或修改Speaker</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!-- 如果IE版本小于9，加载以下js,解决低版本兼容问题 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <!--
    	引入网络的jquery  ,如果想换成自己的，导入即可
    	网站优化：建议将你网站的css\js等代码，放置在互联网公共平台上维护，比如：七牛
    -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>


    <style type="text/css">
        th {
            text-align: center;
        }
    </style>
    <script type="text/javascript">



        function fileUpload() {
            //alert("jinlaijialai");
            $.ajaxFileUpload({
                url : "${pageContext.request.contextPath}/video/upload", //需要链接到服务器地址
                secureuri : false, //是否启用安全提交，默认为false。
                fileElementId : "uploadImgInput", //上传文件选择框的id属性
                dataType : 'text', //json，与后台对应，text和json
                success : function(data) { //后台ajax返回的数据 此处Imgurl

                    //http://localhost:8081/images/f5f12014208d44c3bed1393f88366a29.jpg
                    $("#photo")
                        .html(
                            "<img  width='100px' height='100px' src='"+data+"'/>");
                    $("#imageUrl").val(data);

                    $("#videoImgUrl").val(data);

                },
                error : function(XMLHttpRequest, textStatus,
                                 errorThrown) {
                    alert('上传失败！');
                }
            });


        }


    </script>

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
                <li ><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
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

        <%-- <c:if test="empty ${video.id}"> --%>
        <c:if test="${empty speaker.id}">
            <h2>添加Speaker信息</h2>
        </c:if>

        <c:if test="${not empty speaker.id}">
            <h2>修改Speaker信息</h2>
        </c:if>

    </div>
</div>


<div class="container" style="margin-top: 20px;">

    <form class="form-horizontal" id="formLoad" >


        <c:if test="${not empty speaker.id}">
            <input type="hidden" name="id" value="${speaker.id}">
        </c:if>

        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">主讲人姓名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="speakerName" value="${speaker.speakerName}" placeholder="Speaker名称">
            </div>
        </div>



        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">主讲人职称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="speakerJob" value="${speaker.speakerJob}" placeholder="Speaker名称">
            </div>
        </div>





        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">主讲人简介</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="speakerDesc" rows="3">${speaker.speakerDesc}</textarea>
            </div>
        </div>



        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">主讲人照片</label>
            <div class="col-sm-10">
                <form method="post" id="imgform">
                    <input type="file" name="headImg" id="uploadImgInput" onchange="fileUpload()"/>
                    <%--<input type="url" name="imageUrl" class="form-control" value="${video.imageUrl}" placeholder="具体的url">--%>
                </form>

                <div id="photo">
                    <c:if test="${empty speaker.headImgUrl}">
                        <img  width='100px' height='100px' src='${pageContext.request.contextPath}/images/erha.jpg'/>
                    </c:if>
                    <c:if test="${not empty speaker.headImgUrl}">
                        <img  width='100px' height='100px' src='${speaker.headImgUrl}'/>
                    </c:if>

                </div>
                <input type="hidden" id="videoImgUrl" name="headImgUrl" class="form-control" value="${speaker.headImgUrl}" >
            </div>

        </div>
    </form>


    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="commitForm()">保存</button>
        </div>
    </div>

</div>

<script>

    function commitForm() {
        alert($("#formLoad").serialize());
        $.post("${pageContext.request.contextPath}/speaker/addSpeaker", $("#formLoad").serialize(), function(date){
         if (date == 'success') {
             location.href = "${pageContext.request.contextPath}/speaker/list";
         }

            
        })
    }
</script>


</body>
</html>