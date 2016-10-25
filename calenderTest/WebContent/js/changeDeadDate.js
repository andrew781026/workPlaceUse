var changeDate = angular.module('changeDate', []);
var DB = 'ACC' ;

changeDate.controller('orderFilterWithProductIDController',
		['$http','$location','httpService',
          function($http,$location,httpService){
				var self = this;
				
				// check data to decide show loading pic or not
				self.checkHaveDataOrNot = function() {
					infoNumbers = self.purchOrderInfos.length ;
					return infoNumbers == 0 ;
				};
				
				self.titles = [ '品號' , '品名' , '未交數量' , '採購單號' , '序號' ]
				
				// initial data arrays
				self.purchOrderInfos = [] ;
				
				self.params = $location.search();
				self.productID = self.params.productID ;
				
				// self.DB = 'RIKO';
				self.DB = DB ;
				
				
				self.dataReGet = function() {
					httpService.getHttpGetWithProductID(self.productID,self.DB)
								.success(function(data) {self.purchOrderInfos = data;});
								
				}
				
				self.dataReGet();
				
				
}]);


changeDate.filter('numberFixedLen', function () {
    return function (n, len) {
        var num = parseInt(n, 10);
        len = parseInt(len, 10);
        if (isNaN(num) || isNaN(len)) {
            return n;
        }
        num = '' + num;
        while (num.length < len) {
            num = '0' + num;
        }
        return num;
    };
});

changeDate.controller('factoryController',
		['$http','$location','httpService','$scope',
          function($http,$location,httpService,$scope){
				var self = this;
				
				// check data to decide show loading pic or not
				self.checkHaveDataOrNot = function() {
					infoNumbers = self.factoryInfos.length ;
					return infoNumbers == 0 ;
				};
				
				// initial data arrays
				self.factoryInfos = [] ;
				
				self.params = $location.search();
				self.orderID = self.params.orderID ;
				
				// self.DB = 'RIKO';
				self.DB = DB ; 
				
								
				
}]);

		changeDate.controller(
				'purchOrderController',
				[
						'$http','$location','httpService','$scope',
						function($http,$location,httpService,$scope) {
							var self = this;

							self.params = $location.search();
							self.orderID = self.params.orderID ;
							self.serialNO = self.params.serialNO ;
						

							// self.DB = 'RIKO';
							self.DB = DB ;
							
							self.DBs = [
							         {label : '力科',id : '1' , value:'RIKO'},						            
							         {label : '永鉅',id : '2' , value:'YC'}							            
							        ];
							
							self.database = self.DBs[0]; // set default DB
							
							// check data to decide show loading pic or not
							self.checkHaveDataOrNot = function() {
								infoNumbers = self.purchOrderInfos.length ;
								return infoNumbers == 0 ;
							};
							
							// initial data arrays
							self.purchOrderInfos = [] ;
							self.factoryInfos = [] ;
							
							self.changeDB = function() {
								
								self.DB = self.database.value ;
								
								self.dataReGet();
							}
							
							
							self.updateDeadDay = function(deadDate) {
								orderID = self.purchOrderInfos.orderID;
								serialNO = self.purchOrderInfos.serialNO;
								var deadDateString = deadDate.getFullYear() + '' + numberFormat(2,deadDate.getMonth()+1) + '' + numberFormat(2,deadDate.getDate());
								
								var password = window.prompt("請輸入您的修改密碼");
								
								httpService.getHttpPost(orderID, DB, deadDateString, serialNO,password)
										   .success(function(data) {
											   self.returnString = data;
											   console.log('deadDateString='+deadDateString+';returnString='+self.returnString);
										   });
								
								
								/*
								httpService.HttpUpdate(orderID, DB, deadDateString , serialNO , password)
		   						   			.success(function(data) {
		   						   				self.returnString = data;
		   						   				
		   						   				console.log('deadDateString='+deadDateString+';returnString='+self.returnString);
		   						   				
		   						   				if(data ==="資料更新資料成功!" ){
		   						   					alert('預交日期  '+deadDateString+' 更新成功');
		   						   					window.close();
		   						   				}else if(data ==="無法更新資料，請通知管理員" ){
		   						   					alert(data);
		   						   				}else if(data ==="密碼錯誤" ){
		   						   					alert(data);
		   						   				}
		   						   				
		   						   			})
		   						   			.error(function(data) {
		   										alert('資料更新失敗!');									
		   									});
								
								*/
								
							}
							
							self.dataReGet = function() {
								
								
								self.purchOrderInfos = [];								
								httpService.getHttpGet(self.orderID,self.DB,self.serialNO)
						    	   		   .success(function(data) {
						    	   			   
						    	   			   self.purchOrderInfos = data[0];
						    	   			   
						    	   			   httpService.getHttpGetFactoryInfo(self.purchOrderInfos.factoryID, DB)
				    	   		   						   .success(function(data) {self.factoryInfos = data[0];});
						    	   			   
						    	   		   });
								
								
								
								// $scope.factoryID = self.purchOrderInfos.factoryID ;
									
							}
							
							self.dataReGet();

						} ]);

changeDate.factory('httpService', ['$http',function($http) {
	
	// $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	
	var factory = {} ;
	
	factory.getHttpGet = function(orderID,DB,serialNO){
		// return $http.post('rest/PurchOrders',{'productID':productID,'DB':DB});
		return $http.get('rest/PurchOrders/'+orderID+'?DB=' + DB+'&serialNO='+serialNO);
		
	}
	
	factory.getHttpGetWithProductID = function(productID,DB){
		// return $http.post('rest/PurchOrders',{'productID':productID,'DB':DB});
		return $http.get('rest/PurchOrders?DB=' + DB+'&productID='+productID);
		
	}
	
	factory.getHttpGetFactoryInfo = function(factoryID,DB){
		return $http.get('rest/Factories/' +factoryID+'?DB=' + DB);
	}
	
	factory.HttpUpdate = function(orderID,DB,deadDate,serialNO,password){
		
		// return $http.post('rest/PurchOrders/'+orderID,{'serialNO':serialNO,'date':date});
		return $http.get('rest/PurchOrders/'+orderID+'/'+serialNO+'?DB=' + DB+'&deadDate='+deadDate+'&password='+password);	
	}
	
	factory.getHttpPost = function(orderID,DB,deadDate,serialNO,password){
		
		return $http.post('rest/PurchOrders/'+orderID+'/'+serialNO,
				 "deadDate=" + encodeURIComponent(deadDate) +"&password=" + encodeURIComponent(password) ,
				{ headers: {  'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'  }});
	}
	
	return factory ;
	
	
}]);


function dateFormat(format, date) {
	var dayString;
	if (format === "yyyyMMdd"){
		dayString = date.toISOString().substring(0, 4)
				+ date.toISOString().substring(5, 7)
				+ date.toISOString().substring(8, 10);
	}else if (format === "yyyy/MM/dd") {
		dayString = date.toISOString().substring(0, 4)
		+ "/"
		+ date.toISOString().substring(5, 7)
		+ "/"
		+ date.toISOString().substring(8, 10);
	}
		

	return dayString;
}
