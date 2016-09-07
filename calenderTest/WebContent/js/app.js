angular
		.module('datasApp', [])
		.controller(
				'MainCtrl',
				[
						'$http','$location',
						function($http) {
							var self = this;

							self.orderInfos = [ {
								id : '20160715000001',
								productID : 'A0010010001',
								productName : 'PSC1812-N',
								orderAmount : 10,
								makedAmount : 5,
								unit : 'pcs',
								date : '20160901'
							}, {
								id : '20160722000015',
								productID : 'A0010010004',
								productName : 'PSC0801-NP',
								orderAmount : 1000,
								makedAmount : 500,
								unit : 'pcs',
								date : '20160905'
							} ];

							self.date = date;
							self.DB = 'LJ';

							var getOrderInfos = function() {
								return $http
										.get('ManufacOrderServlet?type=ManufacOrder&date='+ self.date + '&DB='+ self.DB)
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

							getOrderInfos();
							
							console.log(self.checkHaveDataOrNot);
							console.log(self.orderInfos.length);
							console.log(self.orderInfos);
							console.log(date);

						} ]);
