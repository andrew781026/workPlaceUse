var orderInfosWithCalender = angular.module('dateInputExample', []);
var infos = [] ;


orderInfosWithCalender.controller(
				'myDateController',
				[
						'$http','$location','httpService',
						function($http,$location,httpService) {
							var self = this;

							// self.params = $location.search();
							// self.dateString = $location.search().date ;
							
							self.dateInit = function() {
								self.dateString = $location.search().date ;
								if(self.dateString === undefined ){
									self.date = new Date();
									self.year = self.date.getYear() + 1900;
									self.month = self.date.getMonth();
									self.dateString = dateFormat("yyyyMMdd", self.date);
									$location.search('date', self.dateString);
									console.log('year>>',self.year,'month>>',self.month);
									console.log(self.year+''+(self.month+1)+''+'01');
								}else{
									self.year = parseInt(self.dateString.substring(0,4));
									self.month = parseInt(self.dateString.substring(4,6))-1;					
									console.log('year>>',self.year,'month>>',self.month);
								}							
							}
							
							self.dateInit();
							
							self.DB = 'RIKO';
							self.weekString = [ '日', '一', '二', '三', '四', '五',	'六' ];
							self.weekDays = getWeeksInMonth(self.year,self.month);
							
							
							self.DBs = [
							         {label : '力科',id : '1' , value:'RIKO'},							            
							         {label : '永鉅',id : '2' , value:'YC'},							            
							        ];
							self.database = self.DBs[0]; // set default DB
							
							
							self.types = [ 
										{label : '製令單',id : '1',value:'manufactory'},
										{label : '託工單',id : '2',value:'outerfactory'},
										{label : '採購單',id : '3',value:'purchOrder'},
										{label : '所有',id : '4',value:'ALL'}
									];
							
							
							if( $location.search().typeLabel == undefined ){
								self.type = self.types[3]; // set default type
								console.log(self.type);
							}else{
								console.log(self.types);
								
								for( var id in self.types ){
									console.log($location.search().typeLabel);
									console.log(self.types[id]);
									if( self.types[id].label == $location.search().typeLabel  ){
										self.type = self.types[id] ;
									}
									
									console.log(self.type);
									
								}
							}
							
							
							
							
							
							
							// check data to decide show loading pic or not
							self.checkHaveDataOrNot = function() {
								
								infoNumbers = self.manufacOrderInfos.length + self.outFactoryOrderInfos.length + self.purchOrderInfos.length ;
								return infoNumbers == 0 ;
							};
							
							// initial data arrays
							self.manufacOrderInfos = [] ;
							self.outFactoryOrderInfos = [] ;
							self.purchOrderInfos = [] ;
									
							self.gotoChangeDate = function(orderID,serialNO,productID) {
								OpenNewWindow('changeDeadDate.html#?orderID='+orderID+'&serialNO='+serialNO+'&productID='+productID, 'changeDeadDate', 800, 600);
							};
							
							/*************************************************************
							
							// set sample data to arrays
							self.orderInfos = [ {
								id : '20160715000001',
								productID : 'A0010010001',
								productName : 'PSC1812-N',
								orderAmount : 10,
								makedAmount : 5,
								unit : 'pcs',
								date : '2016'+self.dateString.substring(4,6)+'01'
							}, {
								id : '20160722000015',
								productID : 'A0010010004',
								productName : 'PSC0801-NP',
								orderAmount : 1000,
								makedAmount : 500,
								unit : 'pcs',
								date : '2016'+self.dateString.substring(4,6)+'05'
							} ];
							*********************************************************/


							
							/****************************************
							console.log(self.checkHaveDataOrNot());
							
							var outerfactoryHttp = httpService.getHttpGet('outerfactory',self.dateString,self.DB);
							var manufactoryHttp = httpService.getHttpGet('manufactory',self.dateString,self.DB);
							var purchOrderHttp = httpService.getHttpGet('purchOrder',self.dateString,self.DB);
															// .success(function(data) {self.purchOrderInfos = data;});
							
							
							outerfactoryHttp.success(function(data) {
								self.outFactoryOrderInfos = data;
							});
							
							purchOrderHttp.success(function(data) {self.purchOrderInfos = data;});
							
							manufactoryHttp.success(function(data) {
								self.manufacOrderInfos = data;
							}).error(function(data) {
								console.log('Error while fetching notes');
							});
							*****************************************/
							
							
							
							self.changeDB = function() {
								
								self.DB = self.database.value ;
								
								self.dataReGet();
							}
							
							
							self.dataReGet = function() {
								
								self.outFactoryOrderInfos = [];
								self.manufacOrderInfos = [];
								self.purchOrderInfos = [];
								
								if(self.type.label == '所有'){
									
									httpService.getHttpPost('manufactory',self.dateString,self.DB)
							   		   		   .success(function(data) {self.manufacOrderInfos = data;});
									
									httpService.getHttpGet('outerfactory',self.dateString,self.DB)
									    	   .success(function(data) {self.outFactoryOrderInfos = data;});
									
									httpService.getHttpGet('purchOrder',self.dateString,self.DB)
					    	   		   		   .success(function(data) {self.purchOrderInfos = data;});
									
								}else if (self.type.label == '製令單') {
									
									httpService.getHttpPost('manufactory',self.dateString,self.DB)
							   		   .success(function(data) {self.manufacOrderInfos = data;});
									
								}else if (self.type.label == '託工單') {
									
									httpService.getHttpGet('outerfactory',self.dateString,self.DB)
							    	   		   .success(function(data) {self.outFactoryOrderInfos = data;});
									
								}else if (self.type.label == '採購單') {
									
									httpService.getHttpGet('purchOrder',self.dateString,self.DB)
							    	   		   .success(function(data) {self.purchOrderInfos = data;});
									
								}
								
								
							}
							
							
							self.dataReGet();


							self.filterOptions = function(day) { // 將傳入的參數收下來
								return function(data) { // 將 data in
														// dateCtrl.orderInfos 的
														// data 收下來

									// DateFormat("yyyyMMdd").format(day) >> dayString
									var tempDayString = dateFormat("yyyyMMdd", day);
									
									// ---------用date.toISOString()會差一天，所以要補回來-------------
									var tempDay =  parseInt(tempDayString.substring(6,8))+1  ;
									var dayString = '' ;
									if (tempDay.toString().length === 1) {
										dayString = tempDayString.substring(0,6)+'0'+tempDay;
									} else {
										dayString = tempDayString.substring(0,6)+tempDay;
									}
									//--------------------------------------------------------
									
									return parseInt(dayString) == data.date;
								};

							};
							
							
							self.holidayStyle = function(dayString) {

								if (dayString == '日' || dayString == '六') {
									return {
										'color' : 'red','width' : '7%'
									};
								} else {
									return {
										'color' : 'black','width' : '17%'
									};
								};
							};
							

							self.dayInMonthOrNot = function(year, month, date) {
								var firstDate = new Date(year, month, 1), lastDate = new Date(
										year, month + 1, 0);

								if (date >= firstDate && date <= lastDate) {
									return true;
								} else {
									return false;
								}
							}
							
							
							
							self.lastMonth = function() {
								 lastMonth = new Date(self.year,self.month-1,5);
								 lastMonthString = dateFormat("yyyyMMdd", lastMonth);
								 $location.search('date', lastMonthString);		
								 $location.search('typeLabel', self.type.label);
								 location.reload();
							}
							
							self.nextMonth = function() {
								 nextMonth = new Date(self.year,self.month+1,5);
								 nextMonthString = dateFormat("yyyyMMdd", nextMonth);
								 $location.search('date', nextMonthString);	
								 $location.search('typeLabel', self.type.label);
								 location.reload();
							}

						} ]);


orderInfosWithCalender.controller('hrefController',	[ function() {
	var self = this ;
	self.lastMonth = function() {
		self.dateString = $location.search().date ;
	}
}]);

orderInfosWithCalender.factory('httpService', ['$http',function($http) {
	
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	
	var factory = {} ;
	
	factory.getHttpGet = function(type,dateString,DB){
		// return $http.post('ManufacOrderServlet',{'type':type,'date':dateString,'DB':DB});
		return $http.get('ManufacOrderServlet?type='+type+'&date='+ dateString + '&DB=' + DB);
		
	}
	
	
	factory.getHttpPost = function(type,dateString,DB){
		return $http.post('ManufacOrderServlet',{'type':type,'date':dateString,'DB':DB});
		// return $http.get('ManufacOrderServlet?type='+type+'&date='+ dateString + '&DB=' + DB);	
	}
	
	return factory ;
	
	
}]);

