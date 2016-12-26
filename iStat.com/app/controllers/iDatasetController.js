'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iDatasetController',
				[
						'$scope',
						'$http',
						'iDatasetService',
						'ngDialog','DocumentiStat',

						function($scope, $http, iDatasetController, ngDialog,DocumentiStat) {

							$scope.request = '';

							$scope.response = new Object();

							$scope.clickToOpen = function($name) {

								var newScope = $scope;
								newScope.name = $name;
								ngDialog.open({
									template : 'popUps/popUpDataset.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.confirm = function($data) {
								convertInputIntoRequest($data);
								$scope.closeThisDialog();
								console.log($scope.name);
								switch ($scope.name) {
								case 'Open Dataset':
									callOpenDataset();
									break;
								case 'Save Dataset':
									callSaveDataset();
									break;
								default:
									break;
								}
							};

							function convertInputIntoRequest($data) {
								console.log('convertInputIntoRequest');
								var datasetName = $data.datasetName;
								var datasetCells = getValuesDataset(datasetName);
								console.log(datasetCells);
								$scope.datasetName = datasetName;
								$scope.data = datasetCells.data;
							}

							function getValuesDataset($datasetName) {
								var dataset = DocumentiStat.createNew();
								for (var line = 0; line < hot.countRows(); line++) {
									for (var column = 0; column < hot
											.countCols(); column++) {
										dataset.addCell($datasetName, hot
												.getRowHeader(line), hot
												.getColHeader(column), hot
												.getDataAtCell(line, column));
									}
								}
								return dataset;
							}

							function getColFromName(name) {
								var n_cols = hot.countCols();
								console.log(n_cols);
								var i = 1;

								for (i = 0; i <= n_cols; i++) {
									if (name.toLowerCase() == hot.getColHeader(
											i).toLowerCase()) {
										return i;
									}
								}
								return -1; // return -1 if nothing can be found
							}

							function getLineFromName(name) {
								var n_rows = hot.countRows();
								console.log(n_rows);
								var i = 1;
								name = name.toString();
								for (i = 0; i <= n_rows; i++) {
									if (name.toLowerCase() == hot.getRowHeader(
											i).toString().toLowerCase()) {
										return i;
									}
								}
								return -1; // return -1 if nothing can be found
							}

							function callOpenDataset() {

								console.log("--> Called openDataset!");
								var promise = iDatasetController.execute(
										$scope.data,$scope.datasetName, 'openDataset');

								promise.then(function(response) {

									if (response.data != null) {

										$scope.response = response.data;
										console
												.log($scope.response.datasets[0].cells);
										var resultCells = $scope.response.datasets[0].cells;
										for(var cellIndex=0;cellIndex < resultCells.length;cellIndex++){
											hot
											.setDataAtCell(
													getLineFromName(resultCells[cellIndex].line),
													getColFromName(resultCells[cellIndex].column),
													resultCells[cellIndex].value);
										}

									}
								}, function(response) {
									console.log('Error to call openDataset');
								});

							}

							function callSaveDataset() {

								console.log("--> Called saveDataset!");
								var promise = iDatasetController.execute(
										$scope.data,$scope.datasetName, 'saveDataset');

								promise.then(function(response) {

									if (response.data != null) {

										alert('Dataset Saved!');

									}
								}, function(response) {
									console.log('Error to call saveDataset');
								});

							}
						}

				]);