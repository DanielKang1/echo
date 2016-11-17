<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo 订单信息页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
</head>
<body> 

<div class="wrapper">

	<!-- 侧边栏 -->
    <div class="sidebar" data-color="azure" data-image="${pageContext.request.contextPath }/img/signup-bg1.jpg">    
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="" class="simple-text">
                    Echo Hotel
                </a>
            </div>
                       
            <ul class="nav">
                <li>
                    <a href="dashboard.html">
                        <i class="fa fa-user"></i>
                        <p>账号与密码</p>
                    </a>            
                </li>
                <li>
                    <a href="user.html">
                        <i class="fa fa-search"></i>
                        <p>酒店查询</p>
                    </a>
                </li> 
                <li>
                    <a href="table.html">
                    	<i class="fa fa-list-ul"></i>
                        <p>订单一览</p>
                    </a>        
                </li>
                <li class="active">
                    <a href="icons.html">
                        <i class="fa fa-comments"></i>
                        <p>我的评论</p>
                    </a>        
                </li>
                <li>
                    <a href="maps.html">
                         <i class="fa fa-check"></i>
                        <p>Echo会员</p>
                    </a>        
                </li>
               
            </ul> 
    	</div>
    </div>
    <!-- 侧边栏END -->
    
    
    <div class="main-panel">
   		<!-- 导航条 -->
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">    
                 
                <div class="collapse navbar-collapse">       
                    <ul class="nav navbar-nav navbar-left">
                   		<li><a href=""><span style="font-size: 14px;color: #666666;">主页</span></a></li> 	
                        <li><a href=""><span style="font-size: 14px;color: #666666;">热门推荐</span></a></li> 
                        <li><a href=""><span style="font-size: 14px;color: #666666;">度假主题</span></a></li> 
                    </ul>
                    
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span style="font-size: 14px;color: #666666;"> Customer1</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-home"></i>我的主页</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i>设置</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>酒店查询</a></li>
                                <li><a href="#"><i class="fa fa-th-list"></i>订单查询</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="fa fa-sign-out"></i>退出</a>
                              </ul>
                        </li>
                        
                    </ul>
                </div>
            </div>
        </nav>
        <!-- 导航条END -->             

        <div class="content">
            <div class="container-fluid">
            
            
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">评论列表</h4>
                                <p class="category">我的历史评论信息</p>
                            </div>
                            <div class="content">
                            
 					 			<div class="media" style="font-size: 10px;">
 					 			  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;height: 100px;width: 100px;" src="/echo/img/hotel/hotel1.jpg" >
							      </a>
							      <div class="media-body" style="padding-left: 30px;line-height:20px;">
							        <div class="media-content">
							        <h5 class="media-heading">南京古南都饭店</h5> <div id="star1"></div>
							        <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 新街口地区（市中心）</span></a>    
						                <span>鼓楼区广州路188号（3楼办理入住），与上海路交叉口。</span><br>
					                	<span style="font-weight: bold;">优点</span>:服务、硬件、地段、设施都好。 <br>
					                	<span style="font-weight: bold;">缺点</span>:房间有时候有奇怪的“笃笃笃笃”的声音不知道从哪来的。 <br>
					                	<span>酒店服务特别的好，从前台办理入住，到餐厅就餐，到房间服务，特别满意。看到有小孩还特意送来了小孩的洗漱用品。客房服务员张小婷和夏杰还给宝宝叠了兔子。酒店楼下就是德基广场，吃饭购物都很方便。唯一小遗憾是酒店内的高脚杯是正着放的，关抽屉的时候就给摔下来碎了，希望下次高脚杯可以倒着放，还稳当点，或者放在安全固定的地方。一定要到酒店梅苑餐厅吃饭，味道一级棒。</span>
					                </div>
							      </div>
							    </div>
							    <hr>
							    
							    <div class="media" style="font-size: 10px;">
 					 			  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;height: 100px;width: 100px;" src="/echo/img/hotel/hotel1.jpg" >
							      </a>
							      <div class="media-body" style="padding-left: 30px;line-height:20px;">
							        <div class="media-content">
							        <h5 class="media-heading">南京古南都饭店</h5> <div id="star2"></div>
							        <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 新街口地区（市中心）</span></a>    
						                <span>鼓楼区广州路188号（3楼办理入住），与上海路交叉口。</span><br>
					                	<span style="font-weight: bold;">优点</span>:服务、硬件、地段、设施都好。 <br>
					                	<span style="font-weight: bold;">缺点</span>:房间有时候有奇怪的“笃笃笃笃”的声音不知道从哪来的。 <br>
					                	<span>酒店服务特别的好，从前台办理入住，到餐厅就餐，到房间服务，特别满意。看到有小孩还特意送来了小孩的洗漱用品。客房服务员张小婷和夏杰还给宝宝叠了兔子。酒店楼下就是德基广场，吃饭购物都很方便。唯一小遗憾是酒店内的高脚杯是正着放的，关抽屉的时候就给摔下来碎了，希望下次高脚杯可以倒着放，还稳当点，或者放在安全固定的地方。一定要到酒店梅苑餐厅吃饭，味道一级棒。</span>
					                </div>
							      </div>
							    </div>
							    <hr>
							    
							    <div class="media" style="font-size: 10px;">
 					 			  <a class="media-left" href="#">
							        <img class="media-object img-rounded" style="margin-top: 5px;height: 100px;width: 100px;" src="/echo/img/hotel/hotel1.jpg" >
							      </a>
							      <div class="media-body" style="padding-left: 30px;line-height:20px;">
							        <div class="media-content">
							        <h5 class="media-heading">南京古南都饭店</h5> <div id="star3"></div>
							        <a href="" class="res"><span style="color: #1597B8">南京</span></a>  ：
						                <a href="" class="res"><span style="color: #1597B8"> 新街口地区（市中心）</span></a>    
						                <span>鼓楼区广州路188号（3楼办理入住），与上海路交叉口。</span><br>
					                	<span style="font-weight: bold;">优点</span>:服务、硬件、地段、设施都好。 <br>
					                	<span style="font-weight: bold;">缺点</span>:房间有时候有奇怪的“笃笃笃笃”的声音不知道从哪来的。 <br>
					                	<span>酒店服务特别的好，从前台办理入住，到餐厅就餐，到房间服务，特别满意。看到有小孩还特意送来了小孩的洗漱用品。客房服务员张小婷和夏杰还给宝宝叠了兔子。酒店楼下就是德基广场，吃饭购物都很方便。唯一小遗憾是酒店内的高脚杯是正着放的，关抽屉的时候就给摔下来碎了，希望下次高脚杯可以倒着放，还稳当点，或者放在安全固定的地方。一定要到酒店梅苑餐厅吃饭，味道一级棒。</span>
					                </div>
							      </div>
							    </div>
							    <hr>
							    
                            </div>
                        </div>
                    </div>
                 </div>
                 
               
          </div>      

       </div>
   </div>
     
 	<!-- 条款弹出框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h5 class="modal-title" id="myModalLabel"><i class="fa fa-bars"></i>&nbsp;&nbsp;订单详细信息</h5>
	      </div>
	      <div class="modal-body ">
			<div class="card card-plain card-nav-tabs">
				<div class="content">
					<div class="tab-content text-center">
						<form>
                           <table>
	                                 <div class="row">
	                                      <div class="col-md-2"> <span style="line-height:35px">订单ID：</span> </div>
	                                      <div class="col-md-4"> <input type="text" class="form-control" placeholder="订单ID" disabled value="0015213101"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">订单类型：</span> </div>
	                                      <div class="col-md-4"> <input type="text" class="form-control" placeholder="订单类型" disabled value="已执行订单">  </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">酒店名称：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="酒店名称" disabled value="南京金陵饭店"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">下单时间：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="下单时间" disabled value="2016-10-01 13:13:13"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">入住时间：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="入住时间" disabled value="2016-10-04 14:13:13"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">退房时间：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="退房时间" disabled value="2016-10-06 14:13:13"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">最晚执行：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="最晚执行" disabled value="2016-10-05 13:13:13"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">房间类型：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="房间类型" disabled value="豪华客房"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">房间数量：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="房间数量" disabled value="1"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">入住人数：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="入住人数" disabled value="2"> </div>
	                                      <div class="col-md-2"> <span style="line-height:35px">有无儿童：</span> </div>
	                                      <div class="col-md-4">  <input type="text" class="form-control" placeholder="有无儿童" disabled value="无"> </div>
	                                </div>      
                          	 </table>
                         </form>
						</div>
					 
					</div>
				</div>
			</div>
        </div>
	    
	    </div>
	  </div>
	</div>
	<!-- 条款弹出框END -->

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.raty.js" type="text/javascript"></script>
	<script type="text/javascript">
	$('#star1').raty({
		  readOnly : true,
		  score    : 5
		});
	$('#star2').raty({
		  readOnly : true,
		  score    : 5
		});
	$('#star3').raty({
		  readOnly : true,
		  score    : 5
		});
	</script>
	
 
</html>
 