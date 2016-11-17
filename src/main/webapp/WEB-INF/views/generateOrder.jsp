<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<title>Echo Hotel 生成订单页</title>
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath }/img/hotelicon.png">
	  <link href="${pageContext.request.contextPath }/css/jquery.ui.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/date.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/CSSreset.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/divas_free_skin.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/css/material-kit.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/demo.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/bootstrap-spinner.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet"/>
  
     <style type="text/css">
        body{
		    margin:0;
		    padding:0;
		    font-size:12px;
		    color: #555555;
		    font-family:"arial","SimSun ";
		    background: #f7f7f7;
		}
		/*
		.bgBody{
		    background: #FFFFFF;
		}*/
		div,p,a,label,span,img,ul,li,table,
		ul,li,dl,dt,dd,form,textarea,em,thead,tbody
		{
		    margin:0;
		    padding:0;
		    list-style:none;
		    border:0;
		    text-decoration:none;
		    font-weight:normal;
		    font-style:normal;
		    font-size:12px;
		    font:"SimSun ";
		    background: none;
		}
		textarea{
		    outline: none;
		    resize: none;
		}
		input{
		    outline: none;
		}
		.clearFloat:after {
		    visibility:hidden;
		    clear:both;
		    display:block;
		    height:0px;
		    content: "."
		}
		.clearFloat{
		    zoom:1;
		}
		a{
		    cursor:pointer;
		}

    </style>
</head>

<body class="profile-page">

	<nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
    	<div class="container">
        	<div class="navbar-header">
        		<a class="navbar-brand" href="${pageContext.request.contextPath }">Echo Hotel</a>
        	</div>
        	<div class="collapse navbar-collapse" id="navigation-example">
        		<ul class="nav navbar-nav navbar-left">
				    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 热门推荐  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 度假主题 </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }" target="_blank"> 酒店探索 </a> </li>
        		</ul>
        		<ul class="nav navbar-nav navbar-right">
        		  <c:if test="${sessionScope.authCustomer == null }">
    			    <li> <a href="${pageContext.request.contextPath }/customer/signin" target="_blank"> 登录  </a> </li>
    			    <li> <a href="${pageContext.request.contextPath }/customer/signup" target="_blank"> 注册  </a> </li>
                   
                   </c:if>
                    <c:if test="${sessionScope.authCustomer != null }">
                   		 <li class="dropdown">
                             <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span style="font-size: 14px;"> ${sessionScope.authCustomer.nickname }</span> <b class="caret"></b> </a>
                             <ul class="dropdown-menu">
                               <li><a href="${pageContext.request.contextPath }/customer/info" target="_blank"><i class="fa fa-home"></i>我的主页</a></li>
                               <li><a href="${pageContext.request.contextPath }/all" target="_blank"><i class="fa fa-building"></i>酒店查询</a></li>
                               <li><a href="${pageContext.request.contextPath }/customer/goViewOrders" target="_blank"><i class="fa fa-th-list"></i>订单查询</a></li>
                               <li class="divider"></li>
                               <li><a href="${pageContext.request.contextPath }/customer/signout"><i class="fa fa-sign-out"></i>退出</a>
                             </ul>
                         </li>
                    </c:if>

        		</ul>
        	</div>
    	</div>
    </nav>
     
	 
    <div class="wrapper">
   		
		<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath }/img/signin-bg1.jpg');"></div>
		
		<div  class="main main-raised" style="padding: 10px 10px 10px 15px;margin-top: -230px;">
		
		
				
		 	<div class="row">
		 	  	<div class="col-md-3" style="padding-top: 15px;">
		       		<div class="panel panel-default" >
					      <div class="panel-heading">
					        <h3 class="panel-title">酒店地址</h3>
					      </div>
					      <div class="panel-body">
							<h5 class="text-left ">${hotel.hotelName } </h5>
							 <a href=""><span style="color:  #9c27b0;font-size: 10px;">${hotel.city }</span></a>  ：
			                <a href=""><span style="color:  #9c27b0;font-size: 10px;"> ${hotel.district }</span></a>    <br>
			                <span style="font-size: 10px;color: #888888">${hotel.address }</span> <hr>
			                <span>${roomType.typeName } </span> <hr>
				                 <table class="table  resroom text-left">
			                		 <tr><td width="40%">客房类型：</td><td>${roomType.typeName } </td> </tr>
							   		 <tr><td width="40%">床型：</td><td>大床</td> </tr>
							   		 <tr><td width="40%">最多人住：</td><td>2人</td> </tr>
							   		 <tr><td width="40%">价格：</td><td>每晚 &yen;${roomType.price }</td> </tr>
							   		 <tr><td width="40%">其他：</td><td>${roomType.typeDesc }</td> </tr>
							   </table>
					      </div>
				    </div>
		       	</div>
		 	
				 <div class="col-md-9"   style="margin-top: 15px;margin-left: -20px;">
				 
					  <div class="panel panel-primary" >
					      <div class="panel-heading">
					        <h3 class="panel-title">客房预订</h3>
					      </div>
					      <div class="panel-body">
					         <c:if test="${OrderErrorInfo != null }">
					        	 ${OrderErrorInfo }
					         </c:if>
					          <form id="orderForm" action="${pageContext.request.contextPath }/customer/order/submitOrder&hotel_${hotel.hotelID }&roomType_${roomType.typeID}" method="POST">
			                     <table class="table  resroom text-left" >
			                         <tr><td width="20%">入住时间：</td>
			                         	<td class="stay-list-left"> 
								            <div class="sea-div"  style="margin-top: -20px;margin-left: -20px;">
								              <input type="text" readonly class="form-control" id="startDate" name="checkinDate" />
								            </div>
			                         	</td> 
			                         </tr>
			                         <tr><td width="20%">退房时间：</td>
			                         	<td class="stay-list-left"> 
								            <div class="sea-div"  style="margin-top: -20px;margin-left: -20px;">
								               <input type="text" readonly  class="form-control" id="endDate" name="checkoutDate"/>
							    	        </div>
			                         	</td> 
			                         </tr>
			                		 <tr style="border-color: #ffffff"><td width="20%">房间数量：</td><td> 
										<div class="input-group spinner col-sm-5" data-trigger="spinner" style="margin-top: -30px;margin-bottom: -10px;margin-left: 10px;">
									          <input type="text" class="form-control" id ="bookingNum" value="1" data-rule="quantity"  data-max="10" data-min="1" data-step="1" name="bookingNum" >
									          <div class="input-group-addon">
									            <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a>
									            <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a>
									          </div>
									          <div id="checkAllowBookResult" style=""></div>
										 </div>
										   
			                		 </td> 
			                		 </tr>
			                		 <tr style="border-color: #ffffff"><td width="20%">预计入住数量：</td><td> 
										<div class="input-group spinner asd col-sm-5" data-trigger="spinner" style="margin-top: -30px;margin-bottom: -10px;margin-left: 10px;">
									          <input type="text" class="form-control" value="1" data-rule="quantity"  data-max="10" data-min="1" data-step="1" name="peopleNum" >
									          <div class="input-group-addon">
									            <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a>
									            <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a>
									          </div>
										 </div>
			                		 </td> 
			                		 </tr>
			                		 <tr><td width="20%">有无儿童：</td>
			                		 	<td>
			                		 		<div class="col-sm-6" style="margin-top: 10px;margin-bottom: 10px;">
				                		 		 <div class="radio checkbox-inline" style="float: left;margin-top: 0px;margin-bottom: 0px;margin-left: -30px;"><label><input type="radio" name="hasChild" value="1">有</label></div>
							       				 <div class="radio checkbox-inline" style="float: left;margin-top: 0px;margin-bottom: 0px;"><label><input type="radio" name="hasChild" value="0" checked="checked">无</label></div>
				                		 	</div>
			                		 	</td> 
			                		 </tr>
			                		 <tr>
			                		 
			                		 <tr><td width="20%">房费单价：</td>
			                		 	<td>
			                		 		<div class="col-sm-6" style="margin-top: 10px;margin-bottom: 10px;">
				                		 		<span style="font-size: 16px;font-weight: bold;">&yen;${roomType.price }</span>
				                		 		<input type="hidden" value="${roomType.price }" name="roomPrice" />
				                		 	</div>
			                		 		
			                		 	</td> 
			                		 </tr>
			                		 <tr>
				                		 <td width="20%">姓名：</td>
				                		 <td>
				                			 <div class="col-sm-6" style="margin-top: -30px;margin-bottom: -10px;">
				                		 		<input type="text" value="" placeholder="联系人姓名" class="form-control" msg="联系人姓名" name="reservedName"/>
				                		 	</div>
				                		 </td> 
			                		 </tr>
			                		 <tr>
				                		 <td width="20%">联系电话：</td>
				                		 <td>
				                			 <div class="col-sm-6" style="margin-top: -30px;margin-bottom: -10px;">
				                		 		<input type="text" value="" placeholder="+86  需提供给酒店用来担保订单" msg="联系电话" class="form-control" id="reservedPhone" name="reservedPhone"/>
				                		 	</div>
				                		 </td> 
			                		 </tr> 
			                		  <tr>
				                		 <td width="20%">安全验证：</td>
				                		 <td>
											<div class="col-sm-4" style="margin-top: -30px;margin-bottom: -10px;">
												<input type="text" class="form-control" placeholder="验证码" msg="验证码" name="captcha"><div id="checkCaptchaResult"></div>
				                		 	</div>
										    <div class="col-sm-2">
				                		 		<img src="${pageContext.request.contextPath }/captcha-image"  id="kaptchaImage"  style="margin-bottom: -3px">
				                		 	</div>
				                		 </td> 
			                		 </tr>
							   </table>
					   		
		  					 
		  					 <div class="line-ds"></div><br>
		  					 
		  					 <span style="color: #888888;font-size: 10px;">
		  					  预订须知：<br>
		  					 <dl>
		  					    <dd>入住当日18:00前免费变更/取消；之后变更/取消，将扣除信用值，信用值为订单的（总价值*1/2）。</dd>
								<dd>如需发票，请从酒店前台索取</dd>
								<dd>房量紧张，如未预订成功，担保金全部原路退还</dd>
								<dd>入住结账，Echo向酒店确认后，担保金全额原路退还</dd>
								<dd>退还金额境内卡5个工作日内到账，境外卡20个工作日内到账</dd>
							</dl>
 
			  					
 							</span>
 							
 							<input class="btn btn-primary btn-lg" style="float: right;" type="submit" id="submit" onclick="return checkNullAndPhone();" value="提交订单" /> 
 							 </form>
			   	  </div>
	       	    </div>
		      </div>
		 </div>
	</div>
		 
    </div>
    
     <footer class="footer">
        <div class="container">
           <nav class="pull-left">
              
			<ul>
				<li> <a href=""> 网站导航 </a> </li>
				<li> <a href=""> 关于EchoHotel </a> </li>
				<li> <a href=""> 广告业务</a> </li>
				<li> <a href=""> 联系我们</a> </li> 
			</ul>
           </nav>
           <div class="copyright pull-right">
              Copyright &copy; 2016, echohotel.com. all rights reserved.<span id="qq"></span>
           </div>
        </div>
     </footer>


</body>

	<script src="${pageContext.request.contextPath }/scripts/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/material.min.js"></script>
	<script src="${pageContext.request.contextPath }/scripts/material-kit.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.ui.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/moment.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/scripts/jquery.spinner.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	(function($){
	    stay = {
	        start:$('#startDate'),
	        end:$('#endDate'),
	        today:(new Date()),
	        init:function(){
	            stay.inputVal();
	            stay.endFun();
	            stay.startFun();
	        },
	        startFun:function(){
	            stay.start.datepicker({
	                dateFormat : 'yy-mm-dd',
	                dayNamesMin : ['日','一','二','三','四','五','六'],
	                monthNames : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	                altFormat : 'yy-mm-dd',
	                yearSuffix:'年',
	                showMonthAfterYear:true,
	                appendText : '明天',
	                firstDay : 1,
	                showOtherMonths:true,
	                minDate : 0,
	                maxDate:180,
	                onSelect:function(dateText,inst){
	                    stay.end.datepicker('option', 'minDate', new Date(moment(dateText).add('days', 1)));
	                    stay.end.datepicker('option', 'maxDate', new Date(moment(dateText).add('days', 180)));
	                    var strDay =  stay.compare($(this));
	                    stay.start.datepicker('option', 'appendText', strDay);
	                    if((new Date(stay.end.val()) - new Date(dateText)) <= (24*60*60*1000)){
	                        stay.end.datepicker('option', 'appendText', stay.compare(stay.end));
	                    }
	                }
	
	            });
	        },
	        endFun:function(){
	            stay.end.datepicker('refresh');
	            stay.end.datepicker({
	                dateFormat : 'yy-mm-dd',
	                dayNamesMin : ['日','一','二','三','四','五','六'],
	                monthNames : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	                altFormat : 'yy-mm-dd',
	                yearSuffix:'年',
	                showMonthAfterYear:true,
	                appendText : '后天',
	                firstDay : 1,
	                showOtherMonths:true,
	                minDate : 1,
	                maxDate:180,
	                    onSelect:function(){
	                        stay.end.datepicker('option', 'appendText', stay.compare($(this)));
	                    }
	            });
	        },
	        transformStr:function(day,strDay){
	            switch (day){
	                    case 1:
	                        strDay  = '星期一';
	                        break;
	                    case 2:
	                        strDay  = '星期二';
	                        break;
	                    case 3:
	                        strDay  = '星期三';
	                        break;
	                    case 4:
	                        strDay  = '星期四';
	                        break;
	                    case 5:
	                        strDay  = '星期五';
	                        break;
	                    case 6:
	                        strDay  = '星期六';
	                        break;
	                    case 0:
	                        strDay  = '星期日';
	                        break;
	                }
	            return strDay;
	        },
	        compare:function(obj){
	            var strDay = '今天';
	            var myDate = new Date(stay.today.getFullYear(),stay.today.getMonth(),stay.today.getDate());
	            var day = (obj.datepicker('getDate') - myDate)/(24*60*60*1000);
	            day == 0 ? strDay: day == 1 ?
	                (strDay = '明天') : day == 2 ?
	                (strDay = '后天') : (strDay = stay.transformStr(obj.datepicker('getDate').getDay(),strDay));
	            return strDay;
	        },
	        inputVal:function(){
	            stay.inputTimes(stay.start,1);
	            stay.inputTimes(stay.end,2);
	        },
	        inputTimes:function(obj,day){
	            var m = new Date(moment(stay.today).add('days', day));
	            obj.val(m.getFullYear() + "-" + stay.addZero((m.getMonth()+1)) + "-" + stay.addZero(m.getDate()));
	        },
	        addZero:function(num){
	            num < 10 ? num = "0" + num : num ;
	            return num;
	        }
	    }
	    stay.init();
	})(jQuery);
	
	
	
    $(function(){
        $(":input[name='captcha']").change(function(){
        	var val=$(this).val();
       	 	val=$.trim(val);
        	if(val!=""){
	        var url="${pageContext.request.contextPath }/customer/order/checkCaptcha";
	        var args={"captcha":val,"time":new Date()};
	        $.post(url,args,function(data){
	        	var str = data;
	        	if(str == 'true'){
	        		$("#checkCaptchaResult").html('<span style="color:green">√</span>');
	        		$("form").unbind('submit');
	        	}else{
	        		$("#checkCaptchaResult").html('<span style="color:red">×</span>');
	        		$('form').on('submit', function (event) {
	        			alert('验证码不正确');
	        		    event.preventDefault();
	        		});
	        	}
	        
	        });
	        }
          });
        
        $(":input[name='bookingNum']").click(function(){
        	var bookingNum=$(this).val();
        	var checkindate=$('#startDate').val();
        	var checkoutdate=$('#endDate').val();
        	bookingNum=$.trim(bookingNum);
        	if(bookingNum!=""){
	        var url="${pageContext.request.contextPath }/customer/order/checkAllowBookOrNot?roomTypeID=${roomType.typeID}";
	        var args={"bookingNum":bookingNum,"checkindate":checkindate,"checkoutdate":checkoutdate,"time":new Date()};
	        $.post(url,args,function(data){
	        	var str = data;
	        	if(str == 'true'){
	        		$("#checkAllowBookResult").html('<span style="color:green">可预订</span>');
	        	}else{
	        		$("#checkAllowBookResult").html('<span style="color:red">已被订满</span>');
	        	}
	        
	        });
	        }
          });
        
        
        })
        
 
	$(function(){  //生成验证码         
	    $('#kaptchaImage').click(function () {  
	    $(this).hide().attr('src', '${pageContext.request.contextPath }/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
	});   
	  		  
	function changeCode() {  //刷新
	    $('#kaptchaImage').hide().attr('src', '${pageContext.request.contextPath }/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
	    event.cancelBubble=true;  
	}  
	
	function checkNullAndPhone(){
	     var num=0;
	     var str="";
	     $("input[type$='text']").each(function(n){
	          if($(this).val()==""){
	               num++;
	               str+=$(this).attr("msg")+"不能为空\r\n";
	          }
	     });
	     if(num>0){
	          alert(str);
	          return false;
	     }
	     if (!$("#reservedPhone").val().match(/^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/)) { 
	 		alert("手机号码格式不正确！"); 
	 		return false; 
	 		} 
	 		return true; 
	}
	 
	
	</script> 
	

	
</html>