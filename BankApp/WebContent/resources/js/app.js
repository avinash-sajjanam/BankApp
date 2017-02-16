var myApp=angular.module("myApp",["ngRoute"]);
myApp.config(function($routeProvider){
	$routeProvider
	.when("/accounts",{
		templateUrl:"resources/partials/accounts.html",
		controller:"AccountCtrl"
	})
	.when("/addrecipients",{
		templateUrl:"resources/partials/addrecipients.html",
		controller:"AddrecipientCtrl"
	})
	.when("/transfer",{
		templateUrl:"resources/partials/transfer.html",
		controller:"TransferCtrl"
	})
	.otherwise({
		redirectTo:"/accounts"
	})
});
myApp.controller("HeaderCtrl",function($scope){
	$scope.appDetails={};
	$scope.appDetails.title="Bank Application";
	$scope.appDetails.tagline="Welcome User!!"
});
myApp.controller("AccountCtrl",function($scope, HeaderService){
	$scope.message="this is accounts page";
	HeaderService.getAccountData().then(function(data){
		var j = data;
		$scope.data = JSON.parse(j.data);
		$scope.user = JSON.parse($scope.data.accountdatas);
		$scope.account=JSON.Parse($scope.data.accountdatas.account);
		
	});
});
myApp.controller("UserCtrl",function($scope,AccountService){
	AccountService.getAccountData().then(function(data){
		var k=data;
		$scope.data=JSON.parse(k.data);
		$scope.user=JSON.parse($scope.data.userdata);
	});
	
	
});
myApp.controller("AddrecipientCtrl",function($scope){
	$scope.message="this is recipients page";
});
myApp.controller("TransferCtrl",function($scope){
	$scope.message="this is transfer page";
});