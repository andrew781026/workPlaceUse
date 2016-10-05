<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.Calendar" %>
<%@ page language= "java" contentType ="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html ng-app='datasApp' >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日曆格式未交製令</title >
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>


<style type="text/css" >
       td {             
             height: 100px ;
      }
      
       th {             
             height: 10px ;
             background-color: lime ;
      }
</style>


</head>
<body ng-controller="MainCtrl as ctrl">

		<%!
		
			private String getDateWithFormat(String format,Calendar cal){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
				Date date = cal.getTime();
				return simpleDateFormat.format(date);							
			}		
		
		%>
       <%
       		int day = 1 ;
       		Calendar cal = Calendar.getInstance();
            String date = request.getParameter("date");
            
            if(date ==null){            	          
	            cal.set(Calendar.DATE, 1); //設定該月第一天
            }else{            	
            	String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);	           
            }
             
             int dayOfFirstDate = cal.get(Calendar.DAY_OF_WEEK)-1; // 週日=1 , 週一=2
             String dateMonth = getDateWithFormat("yyyyMM", cal);
             
             Calendar lastMonth = cal;
             lastMonth.add(Calendar.MONTH, -1);             
             String lastMonthParam = getDateWithFormat("yyyyMM", lastMonth);
             String lastMonthHrefString = "date="+lastMonthParam ;
             
             Calendar nextMonth = cal;
             nextMonth.add(Calendar.MONTH, 2);
             String nextMonthParam = getDateWithFormat("yyyyMM", nextMonth);
             String nextMonthHrefString = "date="+nextMonthParam ;
             
             cal.add(Calendar.MONTH, -1);
       %>      

       
	   <div>
	   	 	<a href='temp.jsp?<%=lastMonthHrefString %>'>上個月</a>
	   		力科製令未交明細行事曆<%=cal.get(Calendar.YEAR) %>年<%=cal.get(Calendar.MONTH)+1 %>月   
	   		<a href='temp.jsp?<%=nextMonthHrefString %>'>下個月</a>
	   </div>
       <table border="1" cellspacing="1" bordercolor="#CCCCCC" width="100%" style="border-collapse: collapse" >
             <thead>
                   <tr>
                         <th height="25" width="7%"><font color="red">日</font></th >
                         <th height="25" width="17%"> 一</th >
                         <th height="25" width="17%"> 二</th >
                         <th height="25" width="17%"> 三</th >
                         <th height="25" width="17%"> 四</th >
                         <th height="25" width="17%"> 五</th >
                         <th height="25" width="8%"><font color="red">六</font></th >
                   </tr>
             </thead>
             <tbody>
                   <% for (int i = 0 ; i < cal.getActualMaximum(cal.WEEK_OF_MONTH);i++){ %>
                         <tr valign="top">
                               <% // 第一行作微調 %>
                               <% if(i==0){ %>
                                     <% for (int z = 0 ; z < dayOfFirstDate ; z++ ){ %>
                                           <td></td>
                                     <% } %>
                                     <% for (int z = 0 ; z < 7 - dayOfFirstDate ; z++ ){  
                                     	cal.set(Calendar.DATE, day++);
                                     %>
                                     		
                                           <td>
                                           		<div align="center" style="background-color: pink;"><%=getDateWithFormat("yyyy/MM/dd", cal) %></div>                                            		
                                           		<div ng-show="ctrl.checkHaveDataOrNot"><img src="img/loading.gif">資料讀取中...</div>
                                           		<div ng-hide="ctrl.checkHaveDataOrNot">
	                                           		<div style="font-size:1em;">未完工製令：</div>
	                                           		<div style="font-size:0.7em;" ng-repeat="data in ctrl.orderInfos | filter:{ date: <%=getDateWithFormat("yyyyMMdd", cal) %> }">                                           			
	                                           				[<span style="color:red;">{{data.productName}}</span>] 未交數量：{{data.orderAmount-data.makedAmount}}{{data.unit}}<br><br>
	                                           		</div> 
                                           		</div>
                                           </td>
                                     <% } %>                             
                               <% } else { %>                      
                                     <% for (int j = 0 ; j < 7 ; j++ ){ %>
                                           <% if(day<=Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)){ 
                                        	   cal.set(Calendar.DATE, day++);	
                                           %>
                                                 
                                           	  <td>
                                          		<div align="center" style="background-color: pink;"><%=getDateWithFormat("yyyy/MM/dd", cal) %></div> 
                                          		<div ng-show="ctrl.checkHaveDataOrNot"><img src="img/loading.gif">資料讀取中...</div>
                                           		<div ng-hide="ctrl.checkHaveDataOrNot">
	                                          		<div style="font-size:1em;">未完工製令：</div>
	                                          		<div style="font-size:0.5em;" ng-repeat="data in ctrl.orderInfos | filter:{ date: '<%=getDateWithFormat("yyyyMMdd", cal) %>' }">                                           			
	                                          				[{{data.productName}}] 未交數量：{{data.orderAmount-data.makedAmount}}{{data.unit}}<br><br>
	                                          		</div>  
                                          		</div>                               
                                   			  </td>
                                                 
                                           <% }else{ %>
                                                 <td></td>
                                           <% } %>
                                     <% } %>
                               <% } %>
                         </tr>
                   <% } %>
             </tbody>
       </table>
</body>


<script type="text/javascript">
	var date = <%=dateMonth  %>;
</script>
</html>