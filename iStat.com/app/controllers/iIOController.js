'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iIOController',
				[
						'$scope',
						'$http',
						'ngDialog',
						'DocumentiStat',

						function($scope, $http, ngDialog, DocumentiStat) {
							
							$scope.clickToOpen = function($name) {
								
								var doc2 = DocumentiStat.createNew();
								doc2.addCell('dataset_100', 2, "A", 10);	
								doc2.addCell('dataset_100', 1, "B", 100);
								doc2.addCell('dataset_1', 1, "B", 1000);
								doc2.addCell('dataset_1000', 1, "B", 1000);
								doc2.addCell('dataset_100', 1, "C", 100000);
								doc2.printDocumentiStat();

								
								var newScope = $scope;
								newScope.ioName = $name;
								ngDialog.open({
									template : 'popUps/popUpIO.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};
							$scope.confirm = function($data,$name) {
								$scope.closeThisDialog();
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