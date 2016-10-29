var productApp = angular.module('productApp', []);

productApp.controller('singleProduct', [function () {
    var self = this;

    self.products = [
        {
            'productID': 'A0010020003',
            'productName': 'PR-610-I',
            'storagePlace': 'K1-2-3',
            'unit': 'PSC',
            'MAP':[
                {'warehouseID':'S001','warehouseName':'成品倉','amountInWarehouse':55,'borrowAmount':2,'lendAmount':6} ,
                {'warehouseID':'S002','warehouseName':'材料倉','amountInWarehouse':1000,'borrowAmount':5,'lendAmount':7} ,
                {'warehouseID':'S003','warehouseName':'走道倉','amountInWarehouse':22,'borrowAmount':1,'lendAmount':0} ,
                {'warehouseID':'S063','warehouseName':'永鉅倉','amountInWarehouse':33,'borrowAmount':7,'lendAmount':13} ,
                {'warehouseID':'S075','warehouseName':'國貿倉','amountInWarehouse':77,'borrowAmount':6,'lendAmount':0} ,
                {'warehouseID':'S076','warehouseName':'威力科倉','amountInWarehouse':861,'borrowAmount':0,'lendAmount':0}
            ]
        }
    ];
}]);

