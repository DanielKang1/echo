<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %> 

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo 个人信息页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/light-bootstrap-dashboard.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
</head>
<body> 

<div class="wrapper">

	<!-- 侧边栏 -->
    <div class="sidebar" data-color="azure" data-image="${pageContext.request.contextPath }/img/signup-bg1.jpg">    
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="${pageContext.request.contextPath }/all" class="simple-text">
                    Echo Hotel
                </a>
            </div>
                       
            <ul class="nav">
                <li class="active">
                    <a href="${pageContext.request.contextPath }/customer/info" target="_blank">
                        <i class="fa fa-user"></i>
                        <p>账号与密码</p>
                    </a>            
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/all" target="_blank">
                        <i class="fa fa-search"></i>
                        <p>酒店查询</p>
                    </a>
                </li> 
                <li>
                     <a href="${pageContext.request.contextPath }/customer/goViewOrders" target="_blank">
                    	<i class="fa fa-list-ul"></i>
                        <p>订单一览</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/goViewEvaluations" target="_blank">
                        <i class="fa fa-comments"></i>
                        <p>我的评论</p>
                    </a>        
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/customer/goMemberHandle" target="_blank">
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
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                              	<span style="font-size: 14px;color: #666666;"> ${sessionScope.authCustomer.nickname}</span> <b class="caret"></b> </a>
                              <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa fa-home"></i>我的主页</a></li>
                                <li><a href="#"><i class="fa fa-cog"></i>设置</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>酒店查询</a></li>
                                <li><a href="#"><i class="fa fa-th-list"></i>订单查询</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath }/customer/signout"><i class="fa fa-sign-out"></i>退出</a>
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
                                <div class="row">
                                      <div class="col-md-4">
                                          <h4 class="title">基础信息</h4>
                                      </div>
                                      <div class="col-md-8">
                                          <button  data-toggle="modal" data-target="#myModal" class="btn btn-info btn-fill pull-right"><i class="fa fa-cog"></i>&nbsp;&nbsp;修改信息</button> 
                                      </div>
                                 </div>
                            </div>
                            <div class="content">
                   
                                <form>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>用户名</label> 
                                                <input type="text" class="form-control" disabled placeholder="Company" value="${sessionScope.authCustomer.nickname}">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>绑定手机号</label>
                                                <input type="text" class="form-control" disabled placeholder="+86" value="${sessionScope.authCustomer.phone}">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">绑定邮箱</label>
                                                <input type="email" class="form-control" disabled placeholder="Email" value="${sessionScope.authCustomer.email}">
                                            </div>        
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                         <div class="col-md-4">
                                            <div class="form-group">
                                                <label>信用值</label>
                                                <input type="text" class="form-control" disabled placeholder="Company" value="${sessionScope.authCustomer.credit}">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>等级</label>
                                                <input type="text" class="form-control" disabled placeholder="Username" value="<c:choose><c:when test="${sessionScope.authCustomer.grade ==1}">1级会员</c:when><c:when test="${sessionScope.authCustomer.grade ==2}">2级会员</c:when><c:when test="${sessionScope.authCustomer.grade ==3}">3级会员</c:when><c:when test="${sessionScope.authCustomer.grade ==4}">4级会员</c:when><c:when test="${sessionScope.authCustomer.grade ==5}">5级会员</c:when></c:choose>">
                                            </div>        
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">生日</label>
                                                <input type="text" class="form-control" disabled value="<fmt:formatDate  pattern="yyyy-MM-dd" value="${sessionScope.authCustomer.birthday}" />" >
                                                
                                            </div>        
                                        </div>
                                    </div> 
                                </form>
                                
                            </div>
                        </div>
                    </div>
                 </div>
                     
                <div class="row">
                 <div class="col-md-12">
                   <div class="card">
                       <div class="header">
                            <div class="row">
                                      <div class="col-md-4">
                                         <h4 class="title">信用记录</h4>
                           				 <p class="category">此表单将显示您每一次的信用变化情况</p>
                                      </div>
                                      <div class="col-md-8">
                                      <!-- 
                                          <button  data-toggle="modal" data-target="#myModal" class="btn btn-warning btn-fill pull-right"><i class="fa fa-money"></i>&nbsp;&nbsp;信用充值</button> 
                                      -->
                                      </div>
                                 </div>
                       </div>
                       
                       <div class="content table-responsive table-full-width">
		                     <table class="table">
				                <thead>
				                    <tr>
				                        <th class="text-center col-md-1">ID</th>
				                        <th class="text-left col-md-2">订单ID</th>
				                        <th class="text-left col-md-2">酒店名称</th>
				                        <th class="text-left col-md-2">操作类型</th>
				                        <th class="text-left col-md-1">信用值变化</th>
				                        <th class="text-left col-md-1">信用值结果</th>
				                        <th class="text-left col-md-2">相关日期</th>
				                        <th class="text-center col-md-1"></th>
				                    </tr>
				                </thead>
				                <tbody>
				                <c:forEach items="${creditItems }" var="item">
				                    <tr>
				                        <td class="text-center col-md-1">${item.itemID }</td>
				                        <td class="text-left col-md-2">${item.orderID }</td>
				                        <td class="text-left col-md-2">${item.hotelName }</td>
				                        <td class="text-left col-md-2">
					                        <c:choose>
					                        	<c:when test="${item.operationType == 1 }">订单执行</c:when>
					                        	<c:when test="${item.operationType == 2 }">订单异常</c:when>
					                        	<c:when test="${item.operationType == 3 }">异常订单撤销</c:when>
					                        	<c:when test="${item.operationType == 4 }">信用充值</c:when>
					                        	<c:when test="${item.operationType == 5 }">执行撤销过晚</c:when>
					                        	<c:when test="${item.operationType == 6 }">异常订单执行</c:when>
					                        </c:choose>
				                        </td>
				                        <td class="text-left col-md-1">
					                        <c:if test="${item.amount > 0 }"><span style="color: green;">+${item.amount }</span></c:if>
					                        <c:if test="${item.amount < 0 }"><span style="color: red;">${item.amount }</span></c:if>
				                        </td>
				                        <td class="text-left col-md-1"> ${item.oldResult } </td>
				                        <td class="text-left col-md-2"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${item.changeDate }" /></td>
				                        <td class="td-actions text-center col-md-1">
				                            <a href="${pageContext.request.contextPath }/hotel_${item.hotelID}" type="button" rel="tooltip" title="查看酒店信息" class="btn btn-info btn-simple btn-xs">
				                                <i class="fa fa-building"></i>
				                            </a>
				                            <a href="${pageContext.request.contextPath }/customer/order/detail/${item.orderID}" type="button" rel="tooltip" title="查看订单信息" class="btn btn-success btn-simple btn-xs">
				                                <i class="fa fa-file"></i>
				                            </a> 
				                        </td>
				                    </tr>
				               </c:forEach>
				                    <tr>
				                        <td class="col-md-12 text-right" colspan="8">
				                       		 <span style="font-size: 16px;">当前总计：${credit_ }</span>
				                        </td>
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
   </div>
     
 

<!-- 信息修改弹出框 -->
	<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h5 class="modal-title" id="myModalLabel"><i class="fa fa-cog"></i>修改信息</h5>
	      </div>
	      <div class="modal-body ">
			<div class="card card-plain card-nav-tabs">
				<div class="header header-success">
					<div class="nav-tabs-navigation">
						<div class="nav-tabs-wrapper">
							<ul class="nav nav-tabs" data-tabs="tabs">
								<li class="active">
									<a href="#profile" data-toggle="tab">
										<i style="font-style: normal;">基础信息修改</i>
									</a>
								</li>
								<li>
									<a href="#messages" data-toggle="tab">
										<i style="font-style: normal;">密码修改</i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="content">
					<div class="tab-content text-center">
						<div class="tab-pane active" id="profile">
						   <form:form action="${pageContext.request.contextPath }/customer/modify" method="POST" modelAttribute="authCustomer">
                                 <div class="row">
                                     <div class="col-md-6">
                                         <div class="form-group">
                                             <label style="float: left;">用户名</label>
                                             <form:input path="nickname" type="text" class="form-control"/><form:errors path="nickname" cssClass="error"></form:errors> 
                                         </div>        
                                     </div>
                                     <div class="col-md-6">
                                         <div class="form-group">
                                             <label style="float: left;">绑定邮箱</label>
                                             <form:input  path="email" type="text" class="form-control"/><form:errors path="email" cssClass="error"></form:errors>
                                         </div>        
                                     </div>
                                 </div>
                                 
                                 <div class="row">
                                      <div class="col-md-6">
                                         <div class="form-group">
                                             <label  style="float: left;">手机号</label>
                                             <form:input path="phone" type="text" class="form-control"/><form:errors path="phone" cssClass="error"></form:errors> 
                                         </div>        
                                     </div>
                                     <div class="col-md-6">
                                         <div class="form-group">
                                             <label  style="float: left;">生日</label>
                                             <form:input path="birthday" type="text" class="form-control"/><form:errors path="birthday" cssClass="error"></form:errors> 
                                         </div>        
                                     </div>
                                 </div>
                                 <br><br>
                                   <input type="hidden" name="_method" value="PUT">
                                   <button type="submit" class="btn btn-success btn-fill pull-right" style="margin-top: -40px;">确认修改</button> 
                             </form:form>
						</div>
						<div class="tab-pane" id="messages">
						<form action="${pageContext.request.contextPath }/customer/modifyPwd" method="POST">
                           <table>
                               <div class="row">
                               	 <div class="col-md-8 col-md-offset-2" >
	                                 <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">旧密码：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <input type="password" class="form-control" name="oldpwd" placeholder="旧密码">
	                                      </div>
	                                </div>    
	                                <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">新密码：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <input type="password" class="form-control"  name="newpwd" placeholder="新密码">
	                                      </div>
	                                </div> 
	                                <div class="row">
	                                      <div class="col-md-3">
	                                     	 <span style="line-height:35px">密码确认：</span>
	                                      </div>
	                                      <div class="col-md-9">
	                                      	 <input type="password" class="form-control" name="confirmpwd" placeholder="密码确认">
	                                      </div>
	                                </div>   
	                                <div class="row">
	                                      <div class="col-md-12">
	                                     	  <button type="submit" class="btn btn-success btn-fill pull-right">提交</button> 
	                                      </div>
	                                </div>    
                                  </div>
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
	
	<!-- 信息修改弹出框END -->
	
	<c:if test="${requestScope.creditDeficiency != null }">
		${requestScope.creditDeficiency}
	</c:if>
	
 <c:if test="${ requestScope.modifyError!= null }">
   ${modifyError }
  </c:if>
  <c:if test="${ requestScope.modifyPwdError!= null }">
   ${modifyPwdError }
  </c:if>
  <c:if test="${ requestScope.modifySuc!= null }">
   ${modifySuc }
  </c:if>
  <c:if test="${ requestScope.modifyPwdSuc!= null }">
   ${modifyPwdSuc }
  </c:if>

</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-notify.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/light-bootstrap-dashboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
     $(function(){
	     $(":input[name='oldpwdzz']").change(function(){
		     var val=$(this).val();
		     val=$.trim(val);
		     if(val!=""){
			     var url="${pageContext.request.contextPath }/customer/checkPwd";
			     var args={"oldpwd":val,"time":new Date()};
			     $.post(url,args,function(data){
			     $("#message").html(data);
			     });
		     }
	     });
     })
 
    
    	$(document).ready(function(){
        	$.notify({
            	message: "欢迎来到个人信息主页，您可以在这里编辑个人信息、查看订单以及评论。EchoHotel的VIP会员有惊喜优惠哦 。"
            },{
                type: 'success',
                timer: 2000,
                placement: {
                    from: 'bottom',
                    align: 'left'
                }
            });
            
    	});
     
 	$('.form_date').datetimepicker({
         language:  'en',
         format: "yyyy-mm-dd",
         weekStart: 1,
         todayBtn:  1,
 		autoclose: 1,
 		todayHighlight: 1,
 		startView: 2,
 		minView: 2,
 		forceParse: 0
     });
	</script>
</html>