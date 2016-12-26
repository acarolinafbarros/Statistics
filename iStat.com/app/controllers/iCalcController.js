'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iCalcController',
				[
						'$scope',
						'$http',
						'iCalcService',
						'ngDialog',
						'DocumentiStat',

						function($scope, $http, iCalcService, ngDialog,
								DocumentiStat) {

							$scope.request = '';
							var outputBeginLine = '';
							var outputBeginColumn = '';

							// $scope.data = "{ \"datasets\": [ { \"name\":
							// \"dataset_1\", \"cells\": [ { \"line\": \"1\",
							// \"column\": \"A\", \"value\": 100 }, { \"line\":
							// \"2\", \"column\": \"A\", \"value\": 200 } ] }, {
							// \"name\": \"dataset_2\", \"cells\": [ { \"line\":
							// \"1\", \"column\": \"A\", \"value\": 100 }, {
							// \"line\": \"2\", \"column\": \"A\", \"value\":
							// 200 } ] } ]}";

							$scope.response = new Object();

							$scope.clickToOpen = function($name) {

								var newScope = $scope;
								newScope.calculateName = $name;
								ngDialog.open({
									template : 'popUps/popUpCalculate.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.confirm = function($data) {
								outputBeginLine = $data.outputBeginLine;
								outputBeginColumn = $data.outputBeginColumn;
								convertInputIntoRequest($data);
								$scope.closeThisDialog();
								switch ($scope.calculateName) {
								case 'Column\'s total':
									callCalculateRowTotal();
									break;
								case 'Row\'s Total':
									callCalculateRowTotal();
									break;
								case 'Median':
									callCalculateMedian();
									break;
								case 'Mode':
									callCalculateMode();
									break;
								case 'Midrange':
									callCalculateMidrange();
									break;
								case 'Variance':
									callCalculateVariance();
									break;
								case 'Standard Deviation':
									callCalculateStandardDeviation();
									break;
								case 'Geometric Mean':
									callCalculateGeometricMean();
									break;
								default:
									break;
								}
							};

							function convertInputIntoRequest($data) {
								console.log('convertInputIntoRequest');
								var columnIndexInputBegin = getColFromName($data.inputBeginColumn);
								var lineIndexInputBegin = getLineFromName($data.inputBeginLine);
								var columnIndexInputEnd = getColFromName($data.inputEndColumn);
								var lineIndexInputEnd = getLineFromName($data.inputEndLine);
								var datasetCells = getValuesDataset(
										columnIndexInputBegin,
										lineIndexInputBegin,
										columnIndexInputEnd, lineIndexInputEnd);
								console.log(datasetCells);
								$scope.data = datasetCells.data;
							}

							function getValuesDataset(columnIndexInputBegin,
									lineIndexInputBegin, columnIndexInputEnd,
									lineIndexInputEnd) {
								var dataset = DocumentiStat.createNew();

								for (var line = lineIndexInputBegin; line <= lineIndexInputEnd; line++) {
									for (var column = columnIndexInputBegin; column <= columnIndexInputEnd; column++) {
										dataset.addCell('dataset_100', hot
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

							// Internal function
							function callCalculateRowTotal() {
								console
										.log("--> Called calculateRowColumnTotal!");
								console.log($scope.data);
								var promise = iCalcService.execute($scope.data,
										'calculateRowColumnTotal');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		outputBeginLine,
																		outputBeginColumn,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateRowColumnTotal');
													console.log(response);
													alert(response);
												});
							}

							function callCalculateStandardDeviation() {

								console
										.log("--> Called calculateStandardDeviation!");
								var promise = iCalcService.execute($scope.data,
										'calculateStandardDeviation');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateStandardDeviation');
												});

							}

							function callCalculateVariance() {

								console.log("--> Called calculateVariance!");
								var promise = iCalcService.execute($scope.data,
										'calculateVariance');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateVariance');
												});

							}

							function callCalculateMidrange() {

								console.log("--> Called calculateMidrange!");
								var promise = iCalcService.execute($scope.data,
										'calculateMidrange');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateMidrange');
												});

							}

							function callCalculateMode() {

								console.log("--> Called calculateMode!");
								var promise = iCalcService.execute($scope.data,
										'calculateMode');

								promise.then(function(response) {

									if (response.data != null) {

										$scope.response = response.data;
										console.log($scope.response);

										hot.setDataAtCell(1, 1,
												$scope.response.value);

									}
								}, function(response) {
									console.log('Error to call calculateMode');
								});

							}

							function callCalculateGeometricMean() {

								console
										.log("--> Called calculateGeometricMean!");
								var promise = iCalcService.execute($scope.data,
										'calculateGeometricMean');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateGeometricMean');
												});

							}

							function callCalculateMedian() {

								console.log("--> Called calculateMedian!");
								var promise = iCalcService.execute($scope.data,
										'calculateMedian');

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call calculateMedian');
												});

							}
						}

				]);