'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iTransformController',
				[
						'$scope',
						'$http',
						'iTransformService',
						'ngDialog',

						function($scope, $http, iTransformController, ngDialog) {

							$scope.request = '';

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
								newScope.transformName = $name;
								ngDialog.open({
									template : 'popUps/popUpTransform.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.clickToOpenDatasets = function($name) {

								var newScope = $scope;
								newScope.transformName = $name;
								ngDialog.open({
									template : 'popUps/popUpTransformDatasets.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.clickToOpenScale = function($name) {

								var newScope = $scope;
								newScope.transformName = $name;
								ngDialog.open({
									template : 'popUps/popUpTransformScale.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.confirm = function($data) {
								$scope.outputBeginLine = $data.outputBeginLine;
								$scope.outputBeginColumn = $data.outputBeginColumn;
								switch ($scope.transformName) {
								case 'Transpose Dataset':
									convertInputIntoRequest($data);
									callTransformTranspose();
									break;
								case 'Scale':
									convertInputIntoRequestScale($data);
									callTransformScale();
									break;
								case 'Add a Scalar':
									convertInputIntoRequestScale($data);
									callTransformAddScalar();
									break;
								case 'Add Two Datasets':
									convertInputIntoRequestDatasets($data);
									callTransformAddTwoDatasets();
									break;
								case 'Multiply Two Datasets':
									convertInputIntoRequestDatasets($data);
									callTransformMultiplyTwoDatasets();
									break;
								case 'Linear Interpolation':
									convertInputIntoRequest($data);
									callTransformLinearInterpolation();
									break;
								default:
									break;
								}
							};

							function convertInputIntoRequest($data) {
								var columnIndexInputBegin = getColFromName($data.inputBeginColumn);
								var lineIndexInputBegin = getLineFromName($data.inputBeginLine);
								var columnIndexInputEnd = getColFromName($data.inputEndColumn);
								var lineIndexInputEnd = getLineFromName($data.inputEndLine);
								var datasetCells = getValuesDataset(
										columnIndexInputBegin,
										lineIndexInputBegin,
										columnIndexInputEnd, lineIndexInputEnd);
								console.log(datasetCells);
								// TODO Chamar método do Nuno para converter
								// para o JSON correto.
								// $scope.data =
							}

							function convertInputIntoRequestScale($data) {
								var columnIndexInputBegin = getColFromName($data.inputBeginColumn);
								var lineIndexInputBegin = getLineFromName($data.inputBeginLine);
								var columnIndexInputEnd = getColFromName($data.inputEndColumn);
								var lineIndexInputEnd = getLineFromName($data.inputEndLine);
								var datasetCells = getValuesDataset(
										columnIndexInputBegin,
										lineIndexInputBegin,
										columnIndexInputEnd, lineIndexInputEnd);
								console.log(datasetCells);
								var scalar = $data.scalar;
								console.log(scalar);
								// TODO Chamar método do Nuno para converter
								// para o JSON correto.
								// $scope.data =
							}

							function convertInputIntoRequestDatasets($data) {
								var matrix1ColumnIndexInputBegin = getColFromName($data.matrix1.inputBeginColumn);
								var matrix1LineIndexInputBegin = getLineFromName($data.matrix1.inputBeginLine);
								var matrix1ColumnIndexInputEnd = getColFromName($data.matrix1.inputEndColumn);
								var matrix1LineIndexInputEnd = getLineFromName($data.matrix1.inputEndLine);
								var matrix1DatasetCells = getValuesDataset(
										matrix1ColumnIndexInputBegin,
										matrix1LineIndexInputBegin,
										matrix1ColumnIndexInputEnd,
										matrix1LineIndexInputEnd);
								console.log(matrix1DatasetCells);
								var matrix2ColumnIndexInputBegin = getColFromName($data.matrix2.inputBeginColumn);
								var matrix2LineIndexInputBegin = getLineFromName($data.matrix2.inputBeginLine);
								var matrix2ColumnIndexInputEnd = getColFromName($data.matrix2.inputEndColumn);
								var matrix2LineIndexInputEnd = getLineFromName($data.matrix2.inputEndLine);
								var matrix2DatasetCells = getValuesDataset(
										matrix2ColumnIndexInputBegin,
										matrix2LineIndexInputBegin,
										matrix2ColumnIndexInputEnd,
										matrix1LineIndexInputEnd);
								console.log(matrix2DatasetCells);
								// TODO Chamar método do Nuno para converter
								// para o JSON correto.
								// $scope.data =
							}

							function getValuesDataset(columnIndexInputBegin,
									lineIndexInputBegin, columnIndexInputEnd,
									lineIndexInputEnd) {
								var values = new Array();
								for (var line = lineIndexInputBegin; line <= lineIndexInputEnd; line++) {
									for (var column = columnIndexInputBegin; column <= columnIndexInputEnd; column++) {
										var cell = {
											"line" : hot.getRowHeader(line),
											"column" : hot.getColHeader(column),
											"value" : hot.getDataAtCell(line,
													column)
										};
										values.push(cell);
									}
								}
								return values;
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

							function callTransformTranspose() {

								console.log("--> Called transformTranspose!");
								var promise = iTransformController.execute(
										$scope.data, 'transformTranspose');

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
															.log('Error to call transformTranspose');
												});

							}

							function callTransformScale() {

								console.log("--> Called transformScale!");
								var promise = iTransformController.execute(
										$scope.data, 'transformScale');

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
															.log('Error to call transformScale');
												});

							}

							function callTransformAddScalar() {

								console.log("--> Called transformAddScalar!");
								var promise = iTransformController.execute(
										$scope.data, 'transformAddScalar');

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
															.log('Error to call transformAddScalar');
												});

							}

							function callTransformAddTwoDatasets() {

								console
										.log("--> Called transformAddTwoDatasets!");
								var promise = iTransformController.execute(
										$scope.data, 'transformAddTwoDatasets');

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
															.log('Error to call transformAddTwoDatasets');
												});

							}

							function callTransformMultiplyTwoDatasets() {

								console
										.log("--> Called transformMultiplyTwoDatasets!");
								var promise = iTransformController.execute(
										$scope.data,
										'transformMultiplyTwoDatasets');

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
															.log('Error to call transformMultiplyTwoDatasets');
												});

							}

							function callTransformLinearInterpolation() {

								console
										.log("--> Called transformLinearInterpolation!");
								var promise = iTransformController.execute(
										$scope.data,
										'transformLinearInterpolation');

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
															.log('Error to call transformLinearInterpolation');
												});

							}
						}

				]);