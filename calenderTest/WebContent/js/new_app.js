var dateInputExample = angular
		.module('dateInputExample', [])
		.controller(
				'myDateController',
				[
						'$http','$location',
						function($http,$location) {
							var self = this;

							self.params = $location.search();
							self.dateString = self.params.date ;
							
							console.log(self.dateString);
							
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
							
							
							
							self.DB = 'LJ';
							self.weekString = [ '日', '一', '二', '三', '四', '五',	'六' ];
							self.weekDays = getWeeksInMonth(self.year,self.month);
							
							
							

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

							// self.orderInfos = [] ;

							self.checkHaveDataOrNot = self.orderInfos.length == 0;

							var getOrderInfos = function(dateString) {
								// var dateString = dateFormat("yyyyMMdd", self.date);
								return $http
										.get(
												'ManufacOrderServlet?type=ManufacOrder&date='
														+ dateString
														+ '&DB=' + self.DB)
										.then(
												function(response) {
													self.orderInfos = response.data;

													self.checkHaveDataOrNot = self.orderInfos.length == 0;
													console.log(self.checkHaveDataOrNot);
													console.log(self.orderInfos.length);
													console.log(self.orderInfos);

												},
												function(errResponse) {
													console.log('Error while fetching notes');
												});
							};

							getOrderInfos(self.dateString);

							self.filterOptions = function(day) { // 將傳入的參數收下來
								return function(data) { // 將 data in
														// dateCtrl.orderInfos 的
														// data 收下來

									// DateFormat("yyyyMMdd").format(day) >> dayString
									var dayString = dateFormat("yyyyMMdd", day);
									return parseInt(dayString) == data.date;
								};

							};

							
							self.width = function(dayString) {

								if (dayString == '日' || dayString == '六') {
									return {
										'width' : '7%'
									};
								} else {
									return {
										'width' : '17%'
									};
								}
							};
							
							self.holidayOrNot = function(dayString) {

								if (dayString == '日' || dayString == '六') {
									return {
										'color' : 'red'
									};
								} else {
									return {
										'color' : 'black'
									};
								}
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
								 console.log(lastMonthString);
								 $location.search('date', lastMonthString);
								 location.reload();
							}
							
							self.nextMonth = function() {
								 nextMonth = new Date(self.year,self.month+1,5);
								 nextMonthString = dateFormat("yyyyMMdd", nextMonth);
								 console.log(nextMonthString);
								 $location.search('date', nextMonthString);
								 location.reload();
							}

						} ]);




dateInputExample.controller('hrefController',	[ function() {
	var self = this ;
	self.lastMonth = function() {
		self.dateString = $location.search().date ;
	}
}]);


function dateFormat(format, date) {
	var dayString;
	if (format === "yyyyMMdd")
		dayString = date.toISOString().substring(0, 4)
				+ date.toISOString().substring(5, 7)
				+ date.toISOString().substring(8, 10);

	return dayString;
}

// note: month is 0 based, just like Dates in js
function getWeeksInMonth(year, month) {
	var weeks = [], weekDays = [], firstDate = new Date(year, month, 1), lastDate = new Date(
			year, month + 1, 0), numDays = lastDate.getDate();

	var start = 1;

	// 7 - 當月一號為週幾
	var end = 7 - firstDate.getDay();
	while (start <= numDays) {

		weeks.push({
			start : start,
			end : end
		});

		var flag;
		if (end - 6 < 0) {
			flag = end - 6;
		} else {
			flag = start;
		}

		weekDays.push({
			sunday : new Date(year, month, flag),
			monday : new Date(year, month, flag + 1),
			tuesday : new Date(year, month, flag + 2),
			wednesday : new Date(year, month, flag + 3),
			thursday : new Date(year, month, flag + 4),
			friday : new Date(year, month, flag + 5),
			saturday : new Date(year, month, flag + 6)
		});

		start = end + 1;
		end = end + 7;
		if (end > numDays) {
			end = numDays;

		}

	}
	return weekDays;
}
