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
    <style type="text/css">
    a:hover{
    text-decoration:underline;
    }
    </style>
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
                <li class="active">
                    <a href="table.html">
                    	<i class="fa fa-list-ul"></i>
                        <p>订单一览</p>
                    </a>        
                </li>
                <li>
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
                                <h4 class="title">订单信息</h4>
                                <p class="category">此表为所有订单信息的汇总，如需分类可看下方。</p>
                            </div>
                            <div class="content">
 								<table class="table">
					                <thead>
					                    <tr>
					                        <th class="text-left">订单ID</th>
					                        <th class="text-left">订单状态</th>
					                        <th class="text-left">酒店名称</th>
					                        <th class="text-left">下单日期</th>
					                        <th class="text-left">总金额</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <tr>
					                        <td class="text-left">1</td>
					                        <td class="text-left"><span style="color: #87CB16">已执行订单</span></td>
					                        <td class="text-left">南京金陵饭店</td>
					                        <td class="text-left">2016-10-06 17:39:31</td>
					                        <td class="text-left">&yen;867</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="添加评论"  data-toggle="modal" data-target="#myModal2" class="btn btn-success btn-simple btn-xs">
					                                <i class="fa fa-edit"></i>
					                            </button>
					                            <button type="button" rel="tooltip" title="查看详情"  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="text-left">2</td>
					                        <td class="text-left"><span style="color: #87CB16">已执行订单</span></td>
					                        <td class="text-left">南京中心大酒店 </td>
					                        <td class="text-left">2016-10-16 7:49:12</td>
					                        <td class="text-left">&yen;678</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip"  data-toggle="modal" data-target="#myModal2" title="添加评论" class="btn btn-success btn-simple btn-xs">
					                                <i class="fa fa-edit"></i>
					                            </button>
					                            <button type="button" rel="tooltip"  data-toggle="modal" data-target="#myModal" title="查看详情" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="text-left">3</td>
					                        <td class="text-left"><span style="color: #1DC7EA">未执行订单</span></td>
					                        <td class="text-left">南京怡华酒店  </td>
					                        <td class="text-left">2016-10-18 17:29:12</td>
					                        <td class="text-left">&yen;768</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="撤销订单" class="btn btn-danger btn-simple btn-xs">
					                                <i class="fa fa-times"></i>
					                            </button>
					                            <button type="button" rel="tooltip" title="查看详情"  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="text-left">4</td>
					                        <td class="text-left"><span style="color: #999999">异常订单</span></td>
					                        <td class="text-left">青岛万达艾美酒店    </td>
					                        <td class="text-left">2016-10-19 17:39:12</td>
					                        <td class="text-left">&yen;568</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="申诉" class="btn btn-warning btn-simple btn-xs">
					                                <i class="fa fa-bullhorn"></i>
					                            </button> 
					                            <button type="button" rel="tooltip" title="查看详情"  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="text-left">5</td>
					                        <td class="text-left"><span style="color: #999999">已撤销订单</span></td>
					                        <td class="text-left">2099酒店公寓(上海陆家嘴店)   </td>
					                        <td class="text-left">2016-10-19 17:29:12</td>
					                        <td class="text-left">&yen;668</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="查看详情"  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                </tbody>
		            			</table>
                            </div>
                        </div>
                    </div>
                 </div>
                 
                 <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
 								  <span class="category">选择订单类型：
	 								  <a href=""><span style="color: #87CB16">已执行订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href=""><span style="color: #1DC7EA">未执行订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href=""><span style="color: #999999">已撤销订单</span></a>&nbsp;&nbsp;&nbsp;
									  <a href=""><span style="color: #999999">异常订单</span></a>
 								  </span>
							</div>	  
                            <div class="content">
 								<table class="table">
					                 <thead>
					                    <tr>
					                        <th class="text-left">订单ID</th>
					                        <th class="text-left">订单状态</th>
					                        <th class="text-left">酒店名称</th>
					                        <th class="text-left">下单日期</th>
					                        <th class="text-left">总金额</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <tr>
					                        <td class="text-left">1</td>
					                        <td class="text-left"><span style="color: #87CB16">已执行订单</span></td>
					                        <td class="text-left">南京金陵饭店</td>
					                        <td class="text-left">2016-10-06 17:39:31</td>
					                        <td class="text-left">&yen;867</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="添加评论" class="btn btn-success btn-simple btn-xs">
					                                <i class="fa fa-edit"></i>
					                            </button>
					                            <button type="button" rel="tooltip" title="查看详情"  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                    <tr>
					                        <td class="text-left">2</td>
					                        <td class="text-left"><span style="color: #87CB16">已执行订单</span></td>
					                        <td class="text-left">南京中心大酒店 </td>
					                        <td class="text-left">2016-10-16 7:49:12</td>
					                        <td class="text-left">&yen;678</td>
					                        <td class="td-actions text-right">
					                            <button type="button" rel="tooltip" title="添加评论" class="btn btn-success btn-simple btn-xs">
					                                <i class="fa fa-edit"></i>
					                            </button>
					                            <button type="button" rel="tooltip"  data-toggle="modal" data-target="#myModal" title="查看详情" class="btn btn-info btn-simple btn-xs">
					                                <i class="fa fa-external-link"></i>
					                            </button>
					                        </td>
					                    </tr>
					                </tbody>
		            			</table>
                            </div>
                      </div>
                 </div>
              </div>
                
                 <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <p class="category">您预订过的酒店包括：</p>
                            </div>
                            <div class="content">
 								<table class="table table_honer">
					                <thead>
					                    <tr>
					                        <th class="text-center">#</th>
					                        <th>酒店名称</th>
					                        <th>城市</th>
					                        <th>商圈</th>
					                        <th class="text-left">预订次数</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <tr>
					                        <td class="text-center">1</td>
					                        <td><a href="">南京金陵饭店</a></td>
					                        <td><a href="">南京</a></td>
					                        <td><a href="">新街口地区（市中心）</a></td>
					                        <td class="text-left">3</td>
					                    </tr>
					                    <tr>
					                        <td class="text-center">2</td>
					                        <td><a href="">南京玄武饭店</a></td>
					                        <td><a href="">南京</a></td>
					                        <td><a href="">新街口地区（市中心）</a></td>
					                        <td class="text-left">2</td>
					                    </tr>
					                    <tr>
					                        <td class="text-center">3</td>
					                        <td><a href="">南京中心大酒店</a></td>
					                        <td><a href="">南京</a></td>
					                        <td><a href="">新街口地区（市中心）</a></td>
					                        <td class="text-left">1</td>
					                    </tr>
					                </tbody>
		            			</table>
                            </div>
                        </div>
                    </div>
                 </div>
                 
          </div>      

       </div>
   </div>
     


<!-- 添加评论弹出框 -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加评论</h4>
      </div>
      <div class="modal-body">
      
      <table class="table table_honer">
					                    <tr>
					                        <th width="20%">酒店名称</th>
					                        <td class="text-left">南京金陵饭店</td>
					                    </tr>
					                    <tr>
					                        <th width="20%">订单号</th>
					                        <td class="text-left">0123456789</td>
					                    </tr>
					                    <tr>
					                        <th width="20%">客房类型</th>
					                        <td class="text-left">标准双人床房间</td>
					                    </tr>
					                    <tr>
					                        <th width="20%">评分</th>
					                        <td class="text-left"> <div id="star1"></td>
					                    </tr>
					                    <tr>
					                        <th width="20%">优点</th>
					                        <td class="text-left"> <input type="text" class="form-control"></td>
					                    </tr>
					                    <tr>
					                        <th width="20%">优点</th>
					                        <td class="text-left"> <input type="text" class="form-control"></td>
					                    </tr>
					                    <tr>
					                        <th width="20%">综合评论</th>
					                        <td class="text-left"><textarea class="form-control" rows="3"></textarea></td>
					                    </tr>
					                </tbody>
		            			</table>
      					</div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default btn-simple" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-info btn-simple">提交评论</button>
				      </div>
				    </div>
				  </div>
				</div>
     
     
 	<!-- 订单详细信息弹出框 -->
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
	<!-- 订单详细信息弹出框END -->

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.raty.js" type="text/javascript"></script>
 	<script type="text/javascript">
 	$('#star1').raty();
 	</script>
</html>
 