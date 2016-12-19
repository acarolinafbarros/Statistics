'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iIOController',
				[
						'$scope',
						'$http',
						'ngDialog',

						function($scope, $http, ngDialog) {

							$scope.clickToOpen = function($name) {

								var newScope = $scope;
								newScope.ioName = $name;
								ngDialog.open({
									template : 'popUps/popUpIO.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};
							$scope.confirm = function($data,$name) {
								switch ($name) {
								case 'Export Dataset':
									console.log('export...');
									break;
								case 'Import Dataset':
									console.log('import...');
									break;
								default:
									break;
								}
							};	
						}

				]);