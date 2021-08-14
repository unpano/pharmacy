var insuranceApp = angular.module('insuranceApp', []);

/***** Controllers *****/

insuranceApp.controller('TabController', ['$scope', function TabController($scope){
	$scope.active_tab = 1;
	$scope.nextTab = function(){
		$scope.active_tab = $scope.active_tab + 1;
	};
	$scope.resetTabs = function() {
		$scope.active_tab = 1;
	}
}]);

insuranceApp.controller('SearchPageController', ['$scope', function SearchPageController($scope){
	$scope.search = true;
	$scope.lastSearch = '';
	$scope.driver = {};
	$scope.subscriber = {};
	$scope.proposal = {};
	$scope.policy = {};
	
	$scope.setTab = function(tab){
		if (tab === false && $scope.results != null) {
			$scope.search = false;
		}
		if (tab === true) {
			$scope.search = true;
		}
	};
	
	var controller = this;
	$scope.resetForms = function(){
		$scope.driver = angular.copy(controller.emptyDriver);
		$scope.subscriber = angular.copy(controller.emptySubscriber);
		$scope.proposal = angular.copy(controller.emptyProposal);
		$scope.policy = angular.copy(controller.emptyPolicy);
	};
	
	$scope.getSubscribers = function(subscriber) {
		var results = [];
		results[0] = {firstName : 'John', lastName : 'Doe', role: 'Client', birthDate: '1980-06-13', homePhone: '00111 111 111 11', mobilePhone: '00111 111 111 11', email: 'john@example.com'};
		results[1] = {firstName : 'Jane', lastName : 'Doe', role: 'Prospect', birthDate: '1981-04-02', homePhone: '00111 111 111 11', mobilePhone: '00111 222 222 22', email: 'jane@example.com'};
		$scope.results = results;
		$scope.lastSearch = 'subscriber';
		$scope.search = false;
	};
	
	$scope.getDrivers = function(driver) {
		var results = [];
		results[0] = {birthDate: '1980-06-13', licenceNumber: '123456', licenceObtained: '1998-06-13'};
		results[1] = {birthDate: '1981-04-02', licenceNumber: '987654', licenceObtained: '1999-04-02'};
		$scope.results = results;
		$scope.lastSearch = 'driver';
		$scope.search = false;
	};
	
	$scope.getProposals = function(proposal) {
		var results = [];
		results[0] = {id: 'PR#0000001', carBrand: 'Renault', insurancePlan: 'Normal', subscriber:'John Doe', created: '2015-06-13', valid : '1', signed:'1'};
		results[1] = {id: 'PR#0000002', carBrand: 'Peugeot', insurancePlan: 'Basic', subscriber:'Jane Doe', created: '2015-05-13', valid : '0', signed : '0'};
		results[2] = {id: 'PR#0000003', carBrand: 'Peugeot', insurancePlan: 'Normal', subscriber:'Jane Doe', created: '2015-12-03', valid : '1', signed : '0'};
		$scope.results = results;
		$scope.lastSearch = 'proposal';
		$scope.search = false;
	};
	
	$scope.getPolicies = function(policy) {
		var results = [];
		results[0] = {id: 'PO#0000001', proposalId: 'PR#0000001', signed: '2015-06-13', payed:'2015-08-10', amount : '868.00'};
		$scope.results = results;
		$scope.lastSearch = 'policy';
		$scope.search = false;
	};
	
	$scope.setSelected = function(selected) {
		$scope.selected = selected;
	};
	
	$scope.popup = function(type, object) {
		console.log('TODO : popups for ' + type);
	}
	
	$scope.resetPaymentSpecificFields = function(){
		$scope.policy.bank='';
		$scope.policy.accountHolder='';
		$scope.policy.cardHolder='';
		$scope.policy.cardType='';
	}
	
	this.emptyDriver = {birthDate: '', licenceNumber: '', licenceObtained: ''};
	this.emptySubscriber = {firstName : '', lastName : '', role: '', birthDate: '', phone: '', email: ''};
	this.emptyProposal = {id: '', carBrand:'', insurancePlan: '', subscriber:'', created:'', valid:'', signed:''};
	this.emptyPolicy = {id:'', proposalId:'', signed:'', payed:'', amount:'', paymentMode:'', bank:'', accountHolder:'', cardHolder:'', cardType:''};
}]);

insuranceApp.controller('FindPersonController', ['$scope', 'proposalService', function FindPersonController($scope, proposalService){
	this.checkboxCounter = 0;
	
	$scope.isNextAvaliable = function() {
		return $scope.selected!=null;
	};
	
	$scope.getResults = function(search) {
		$scope.results = proposalService.getSubscribers(search);
	};
	
	var controller = this;
	$scope.setSelected = function(selected) {
		if (selected.firstName != null) {
			controller.checkboxCounter = 0;
		}
		else if (++controller.checkboxCounter % 2 == 0) {
			controller.checkboxCounter = 0;
		}
		$scope.selected = selected;
		proposalService.setSubscriber(selected);
	};
}]);

insuranceApp.controller('VehicleController', ['$scope', 'proposalService', function VehicleController($scope, proposalService){	
	$scope.getVehicle = function() {
		return proposalService.vehicle;
	};
	
	$scope.setVehicle = function() {
		proposalService.vehicle=$scope.vehicle;
	};
}]);

insuranceApp.controller('IdentificationController', ['$scope', 'proposalService', function IdentificationController($scope, proposalService){	
	$scope.$watch(function(){
		return proposalService.subscriber;
	}, function (newVal, oldVal) {
		$scope.subscriber = proposalService.subscriber;
		$scope.subscriber.birthDate = new Date(proposalService.subscriber.birthDate);
	});
	
	$scope.drivers = [{}];
	$scope.driverIndexes = [];
	
	$scope.validDrivers = [false, false, false, false];
	$scope.validSubscriber = false;
	
	$scope.resetDrivers = function() {
		$scope.driverIndexes = [];
		for (var i = 0; i < $scope.driversNumber; i++) {
			$scope.driverIndexes.push(i);
		}
	}	
	
	$scope.setData = function() {
		proposalService.setSubscriber($scope.subscriber);
		proposalService.drivers = $scope.drivers;
		proposalService.risks = $scope.savedRAState.risks;
		proposalService.accidents = $scope.savedRAState.accidents;
		$scope.$parent.nextTab();
	}
	
	$scope.setDriver1 = function(subscriberForm) {
		if($scope.subscriberIsDriver) {
			$scope.drivers[0].birthDate = $scope.subscriber.birthDate;
			$scope.drivers[0].gender = $scope.subscriber.gender;
			$scope.drivers[0].maritalStatus = $scope.subscriber.maritalStatus;
		}
	}
	
	$scope.isNextAvaliable = function() {
		if (!$scope.driversNumber) {
			return false;
		}
		for (var i = 0; i < $scope.driversNumber; i++) {
			if (!$scope.validDrivers[i]) {
				return false;
			}
		}
		return $scope.validSubscriber;
	}
	
	$scope.risks = proposalService.getRisks();
	$scope.selectedRisks = [];	
	$scope.accidents = [];
	$scope.savedRAState = {risks : [], accidents : []};
	
	$scope.openRiskAndAccidentDialog = function() {
		$scope.selectedRisks = angular.copy($scope.savedRAState.risks);
		$scope.accidents = angular.copy($scope.savedRAState.accidents);
	}
	$scope.addAccident = function() {
		$scope.accidents.push({});
	}
	$scope.removeAccident = function() {
		$scope.accidents.pop();
	}
	$scope.saveRiskAndAccidentState = function() {
		$scope.savedRAState.risks = angular.copy($scope.selectedRisks);
		$scope.savedRAState.accidents = angular.copy($scope.accidents);
	}
}]);

insuranceApp.controller('DriverFormController', ['$scope', function DriverFormController($scope) {
	
	$scope.driver = {};
	
	if ($scope.$parent.$index==0) {
		$scope.$watch(function(){
			return $scope.$parent.$parent.drivers[0];
		}, function (newVal, oldVal) {
			$scope.driver = $scope.$parent.$parent.drivers[0];
		});	
	}
		
	$scope.publishChange = function(driverForm) {
		$scope.$parent.$parent.drivers[$scope.$parent.$index] = $scope.driver;
		$scope.$parent.$parent.validDrivers[$scope.$parent.$index] = driverForm.$valid;
	}	
	
	$scope.amIDisabled = function() {
		return $scope.$parent.$index==0 && $scope.$parent.$parent.subscriberIsDriver;
	}
}]);

insuranceApp.controller('TarificationController', ['$scope', 'proposalService', function TarificationController($scope, proposalService){	

	$scope.insurancePlans = proposalService.getInsurancePlans();
	$scope.insuranceItems = proposalService.getInsuranceItems();
	$scope.selectedCol = -1;
	$scope.amount = 0;
	
	$scope.setSelectedColumn = function(index) {
		$scope.selectedCol = index;
		$scope.amount = (index + 1) * 100;
	}
	
	$scope.isNextAvaliable = function() {
		return $scope.selectedCol != -1;
	}
	
	$scope.setData= function() {
		proposalService.insurancePlan = $scope.insurancePlans[$scope.selectedCol];
		proposalService.amount =  $scope.amount;
		$scope.$parent.nextTab();
	}
}]);

insuranceApp.controller('ProposalController', ['$window', '$location', '$scope', 'proposalService', function ProposalController($window, $location, $scope, proposalService){	
	
	var confirmed = false;
	
	$scope.$watch(function(){
		return proposalService.subscriber;
	}, function (newVal, oldVal) {
		$scope.subscriber = proposalService.subscriber;
	});
	
	$scope.$watch(function(){
		return proposalService.vehicle;
	}, function (newVal, oldVal) {
		$scope.vehicle = proposalService.vehicle;
	});
	
	$scope.$watch(function(){
		return proposalService.drivers;
	}, function (newVal, oldVal) {
		$scope.drivers = proposalService.drivers;
	});
	
	$scope.$watch(function(){
		return proposalService.insurancePlan;
	}, function (newVal, oldVal) {
		$scope.insurancePlan = proposalService.insurancePlan;
	});
	
	$scope.isConfirmed = function() {
		return confirmed;
	}
	
	$scope.confirmProposal = function() {
		confirmed = true;
		proposalService.saveProposal();
	}
	
	$scope.startOver = function() {
		$scope.$parent.resetTabs();
	}
	
	$scope.proceedToPolicy = function() {
		$window.location.href= $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/policy/policy.html#?proposalId=" + convertId(proposalService.id) + "&amount=" + proposalService.amount;
	}
	
	convertId = function(id) {
		var arr = id.split('#');
		return arr[0] + 'sign' + arr[1];
	}
}]);

insuranceApp.controller('PolicyController', ['$window', '$location', '$filter', '$scope', 'proposalService', function PolicyController($window, $location, $filter, $scope, proposalService){
	
	convertId = function(id) {
		var arr = id.split('sign');
		return arr[0] + '#' + arr[1];
	}
	
	var search = $location.search();
	var proposalId = search.proposalId ? convertId(search.proposalId) : '';
	var amount = search.amount ? $filter('currency')(search.amount, '€') : '';
	$scope.policy = {proposalId : proposalId, amount : amount};	
	
	$scope.resetPaymentSpecificFields = function() {
		$scope.policy.bank = '';
		$scope.policy.accountHolder = '';
		$scope.policy.cardHolder = '';
		$scope.policy.cardType = '';
	}
	
	$scope.isDoneAvaliable = function() {
		switch ($scope.policy.paymentMode) {
			case '1' : return $scope.policyForm.$valid && $scope.creditCardForm.$valid;
			case '2' : return $scope.policyForm.$valid && $scope.checkForm.$valid;
			case '3' : return $scope.policyForm.$valid;
		}
	}
	
	$scope.setData = function() {
		// TODO : send data to server
		$window.location.href= $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/index.html";
	}
}])

/***** Services *****/

insuranceApp.factory('proposalService', function proposalService(){
	var proposalService = {};
	var emptySubscriber = {firstName : '', lastName : '', role: '', birthDate: new Date(), phone: '', email: ''};
	
	proposalService.subscriber = emptySubscriber;
	proposalService.drivers = [];
	proposalService.optionalItems = [];
	
	proposalService.setSubscriber = function(subscriber) {
		if(subscriber.firstName==null) {
			proposalService.subscriber = emptySubscriber;
		}
		else {
			proposalService.subscriber = subscriber;
		}	
	}
	
	proposalService.getSubscribers = function(search) {
		var results = [];
		results[0] = {firstName : 'John', lastName : 'Doe', role: 'client', birthDate: '1980-06-13', homePhone: '00111 111 111 11', mobilePhone: '00111 111 111 11', email: 'john@example.com'};
		results[1] = {firstName : 'Jane', lastName : 'Doe', role: 'prospect', birthDate: '1981-04-02', homePhone: '00111 111 111 11', mobilePhone: '00111 222 222 22', email: 'jane@example.com'};
		return results;
	}
	
	proposalService.getInsurancePlans = function() {
		var results = [
			{name :'Basic 1', items : ['Item 1', 'Item 2', 'Item 3']},
			{name :'Basic 2', items : ['Item 1', 'Item 2', 'Item 3', 'Item 4']},
			{name :'Basic 3', items : ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5']},
			{name :'Premium 1', items : ['Item 1', 'Item 2', 'Item 3', 'Item 6', 'Item 7']},
			{name :'Premium 2', items : ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 6', 'Item 7']},
			{name :'Premium 3', items : ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5', 'Item 6', 'Item 7']}
		];
		return results;
	}
	
	proposalService.getInsuranceItems = function() {
		var results = [];
		for (var i = 1; i <= 7; i++) {
			results.push('Item ' + i);
		}
		return results;
	}
	
	proposalService.getRisks = function() {
		var results = [];
		for (var i = 1; i <= 3; i++) {
			results.push({id : i, description : 'Risk ' + i});
		}
		return results;
	}
	
	proposalService.saveProposal = function() {
		// TODO : send proposal to server
		proposalService.id = 'PR#0000005';
	}
	
	return proposalService;
});

/***** Filters *****/

insuranceApp.filter('dateFilter', function(){
	return function (date) {
		var dateString = date.toISOString();
		return dateString.substring(0, dateString.indexOf('T'));
	}
});

insuranceApp.filter('addressFilter', function(){
	return function (addressLine1, addressLine2, city, country) {
		return addressLine1 + ", " + (addressLine2 ? (addressLine2 + ", ") : "") + city + ", " + country
	}
});