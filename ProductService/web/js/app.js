var productApp = angular.module('productApp', []);

productApp.controller('singleProduct', [function () {
    var self = this;

    self.products = [
        {
            'productID': 'A0010020003',
            'productName': 'PR-610-I',
            'storagePlace': 'K1-2-3',
            'unit': 'PSC',
            'amountList':[
                {"warehouse":{"id":"S001","name":"成品倉","note":"專門放材料的地方"},'amountInWarehouse':55,'borrowAmount':2,'lendAmount':6},
                {"warehouse":{"id":"S002","name":"材料倉","note":"專門放材料的地方"},'amountInWarehouse':1000,'borrowAmount':5,'lendAmount':7},
                {"warehouse":{"id":"S003","name":"走道倉","note":"專門放材料的地方"},'amountInWarehouse':22,'borrowAmount':1,'lendAmount':0},
                {"warehouse":{"id":"S063","name":"永鉅倉","note":"專門放材料的地方"},'amountInWarehouse':33,'borrowAmount':7,'lendAmount':13},
                {"warehouse":{"id":"S075","name":"國貿倉","note":"專門放材料的地方"},'amountInWarehouse':77,'borrowAmount':6,'lendAmount':44},
                {"warehouse":{"id":"S076","name":"威力科倉","note":"專門放材料的地方"},'amountInWarehouse':861,'borrowAmount':0,'lendAmount':0}
            ]
        }
    ];
}]);

