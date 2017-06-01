<%@ page language="java" import="java.util.*,utils.*,java.sql.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${sessionScope.youkaris==null}">
	
		<c:redirect url="update.action" />
	</c:if>

	<div class="left" style="display: block;
	border: red 1px solid; border-radius: 5px;
	width: 500px;height: auto;overflow: auto;float: left;margin-left: 5px;padding: 10px;">
	
	
		<form action="analysis.action" method="post">
		妖怪：<select name = "youkari" id="youkari1" onchange="javascript:selectChange('youkari',1)"
				>
		<option>请选择妖怪</option>
			<c:forEach var="ykr" items="${sessionScope.youkaris}">
			
				<option >${ykr.name}</option>
			</c:forEach>
		</select>
		线索:<select name ="clue" id ="clue1" onchange="javascript:selectChange('clue',1)">
			<option>暂无线索</option>
			<c:forEach var="clue" items="${sessionScope.youkaris}">
				<c:if test="${clue.clue!=null}">
					<option>${clue.clue}</option>
				</c:if>
				
			</c:forEach>
		</select>
		<input type="hidden" name="position" value="1">
	
		<button>提交</button>
	
	</form>
	
	<c:if test="${sessionScope.youkariMsg1!=null}">
		<br/>
		${sessionScope.youkariMsg1}
		
	</c:if>
	</div>
	<div class="middle" style="display: block;
	border: yellow 1px solid; border-radius: 5px;
	width: 500px;height: auto;overflow: auto;float: left;margin-left: 5px;padding: 10px;">
	
	<form action="analysis.action" method="post">
		妖怪：<select name = "youkari" id="youkari2" onchange="javascript:selectChange('youkari',2)">
		<option>请选择妖怪</option>
			<c:forEach var="ykr" items="${sessionScope.youkaris}">
			
				<option >${ykr.name}</option>
			</c:forEach>
		</select>
		线索:<select name ="clue" id ="clue2" onchange="javascript:selectChange('clue',2)">
			<option>暂无线索</option>
			<c:forEach var="clue" items="${sessionScope.youkaris}">
				<c:if test="${clue.clue!=null}">
					<option>${clue.clue}</option>
				</c:if>
				
			</c:forEach>
		</select>
		<input type="hidden" name="position" value="2">
	
		<button>提交</button>
	
	</form>
	
	<c:if test="${sessionScope.youkariMsg2!=null}">
		<br/>
		${sessionScope.youkariMsg2}
		
	</c:if>
	
	</div>
	<div class="right" style="display: block;
	border: green 1px solid; border-radius: 5px;
	width: 480px;height: auto;overflow: auto;float: left;margin-left: 5px;padding: 10px;">
	
	
	<form action="analysis.action" method="post">
		妖怪：<select name = "youkari" id="youkari3" onchange="javascript:selectChange('youkari',3)">
		<option>请选择妖怪</option>
			<c:forEach var="ykr" items="${sessionScope.youkaris}">
			
				<option >${ykr.name}</option>
			</c:forEach>
		</select>
		线索:<select name ="clue" id ="clue3" onchange="javascript:selectChange('clue',3)">
			<option>暂无线索</option>
			<c:forEach var="clue" items="${sessionScope.youkaris}">
				<c:if test="${clue.clue!=null}">
					<option>${clue.clue}</option>
				</c:if>
				
			</c:forEach>
		</select>
		<input type="hidden" name="position" value="3">
	
		<button>提交</button>
	
	</form>
	
	<c:if test="${sessionScope.youkariMsg3!=null}">
		<br/>
		${sessionScope.youkariMsg3}
		
	</c:if>
	</div >
	
	<div style=" border: orange 1px solid; border-radius: 5px;width:auto;
	height: auto;overflow: auto;margin-left: 5px;padding: 10px; float: left;margin-top: 10px;">
	
		<form action="analysisAll.action" method="post">
			<!-- 第一个妖怪的数量: <input type="number" min="1" max="20" id="youkariCount1" name="youkariCount1">
			<br/>
			第二个妖怪的数量:<input type="number" min="1" max="20"  id="youkariCount2" name="youkariCount2">
			<br/>
			第三个妖怪的数量:<input type="number" min="1" max="20" id="youkariCount3" name="youkariCount3">
			<br/>
			 -->
			 全部分析
			 
			<button id="allBtn">提交</button>
		</form>
	
		<br/>
		<hr/>
		<p id="result"></p>
	</div>

	<script type="text/javascript">
	$(document).ready(function(){
		changeSelectState();
		$("#allBtn").click(submitAllForm);
		
		
	});
	function submitAllForm(){
		//获取三个妖怪的名字以及数量 然后提交
		var youkari1 = $("#youkari1").val();
		var youkari2 = $("#youkari2").val();
		var youkari3 = $("#youkari3").val();
		//var num1 = $("#youkariCount1").val();
		//var num2 = $("#youkariCount2").val();
		//var num3 = $("#youkariCount3").val();
	//	var reg=/^[1-9]\d*$|^0$/;
		if (youkari1==null || youkari1=='请选择妖怪') {
			return false;
		}
		if (youkari2==null || youkari2=='请选择妖怪') {
			return false;
		}
		if (youkari3==null || youkari3=='请选择妖怪') {
			return false;
		}
	//	
	//	if (num1 ==null || reg.test(num1)!=true) {
	//		return false;
	//	}
	//	if (num2 ==null || reg.test(num2)!=true) {
	///		return false;
		//}
	//	if (num3 ==null || reg.test(num3)!=true) {
		//	return false;
	//}
		
		var url = 'analysisAll.action';
		var params ={
			'youkari1':youkari1,
			'youkari2':youkari2,
			'youkari3':youkari3
			
		}
		
		$.post(url,params,function (result,textStatus){
			 
			if(textStatus == 'success'){  
				 
				 if (result!=null) {
					$("#result").html(result);
				}
			 }
		});
		return false;
	} 
	
	
	
	function changeSelectState(){
		for(var i=1;i<=3;i++){
			$("#youkari"+i).val($.cookie('youkari'+i));
			$("#clue"+i).val($.cookie('clue'+i));
		}
		
	}
	
	
	function clearSelectState(){
		$.cookie('youkari1',null);
		$.cookie('clue1',null);
		$.cookie('youkari2',null);
		$.cookie('clue2',null);
		$.cookie('youkari3',null);
		$.cookie('clue3',null);
	}
	
	function selectChange(flag,pos){
			
			if(flag == 'youkari'){
				var name = $("#youkari"+pos).val();  
				if(name =='请选择妖怪') return ;
				
				var url = "getClue.action";  
				var params = {  
				     'name':name  
				 };  
				    $.post(url, params, function(result,textStatus){
				    	
				    	 if(textStatus == 'success'){  
				    		  
				    		 	if(result != null){   	
				    	         $("#clue"+pos).val(result);  //设置 值为 val为选中
				    	        }else {	
						    	 $("#clue"+pos).val("暂无线索"); //这里注意了 select中的值不是String啊 不能加“暂无线索”
				    	        } 
				    	    }  
				    	 
				    	 	console.log("cookie youkari "+pos+" is "+$.cookie('youkari'+pos));
						    console.log("youkari"+pos+"is "+$("#youkari"+pos).val());
						    console.log("cookie clue "+pos+" is "+$.cookie('clue'+pos));
						    console.log("clue "+pos+" is"+$("#clue"+pos).val());
						    $.cookie('clue'+pos,$("#clue"+pos).val());
						    $.cookie('youkari'+pos,$("#youkari"+pos).val());
						    console.log("cookie youkari "+pos+" is "+$.cookie('youkari'+pos));
							console.log("cookie clue "+pos+" is"+$.cookie('clue'+pos));
							console.log("------------------------------------------------");
				    });  
				   
			}
			if(flag == 'clue'){
				var clue = $("#clue"+pos).val(); 
				if(clue =='暂无线索') return;
				
				var url = "getName.action";  
				var params = {  
				     'clue':clue  
				 };  
				    $.post(url, params, function(result,textStatus){
				    	
				    	 if(textStatus == 'success'){  
				    	        if(result != null){  	        
				    	         $("#youkari"+pos).val(result);  //设置 值为 val为选中
				    	        }else{
				    	        	$("#youkari"+pos).val("请选择妖怪"); 
				    	        }
				    	       
				    	    }  
				    	 
				    	 console.log("cookie youkari "+pos+" is "+$.cookie('youkari'+pos));
						    console.log("youkari"+pos+"is "+$("#youkari"+pos).val());
						    console.log("cookie clue "+pos+" is "+$.cookie('clue'+pos));
						    console.log("clue "+pos+" is"+$("#clue"+pos).val());
						    $.cookie('clue'+pos,$("#clue"+pos).val());
						    $.cookie('youkari'+pos,$("#youkari"+pos).val());
						    console.log("cookie youkari "+pos+" is "+$.cookie('youkari'+pos));
							console.log("cookie clue "+pos+" is"+$.cookie('clue'+pos));
							console.log("------------------------------------------------");
							
				    });  
				   
			}
			
			
		}
		
	
	</script>
	
</body>
</html>