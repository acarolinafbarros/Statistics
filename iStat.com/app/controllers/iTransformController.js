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
						'DocumentiStat',

						function($scope, $http, iTransformController, ngDialog,DocumentiStat) {

							$scope.request = '';

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
								ngDialog
										.open({
											template : 'popUps/popUpTransformDatasets.html',
											className : 'ngdialog-theme-default',
											scope : newScope
										});
							};

							$scope.clickToOpenScale = function($name) {

								var newScope = $scope;
								newScope.transformName = $name;
								ngDialog
										.open({
											template : 'popUps/popUpTransformScale.html',
											className : 'ngdialog-theme-default',
											scope : newScope
										});
							};

							$scope.confirm = function($data) {
								$scope.outputBeginLine = $data.outputBeginLine;
								$scope.outputBeginColumn = $data.outputBeginColumn;
								$scope.closeThisDialog();
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
								$scope.data = datasetCells.data;
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
								var scalar = $data.inputScalar;
								console.log(scalar);
								$scope.data = datasetCells.data;
								$scope.scalar = scalar;
							}

							function convertInputIntoRequestDatasets($data) {
								var matrix1ColumnIndexInputBegin = getColFromName($data.matrix1.inputBeginColumn);
								var matrix1LineIndexInputBegin = getLineFromName($data.matrix1.inputBeginLine);
								var matrix1ColumnIndexInputEnd = getColFromName($data.matrix1.inputEndColumn);
								var matrix1LineIndexInputEnd = getLineFromName($data.matrix1.inputEndLine);
								var matrix2ColumnIndexInputBegin = getColFromName($data.matrix2.inputBeginColumn);
								var matrix2LineIndexInputBegin = getLineFromName($data.matrix2.inputBeginLine);
								var matrix2ColumnIndexInputEnd = getColFromName($data.matrix2.inputEndColumn);
								var matrix2LineIndexInputEnd = getLineFromName($data.matrix2.inputEndLine);
								var matrixDatasetCells = getValuesDatasetMatrixs(
										matrix1ColumnIndexInputBegin,
										matrix1LineIndexInputBegin,
										matrix1ColumnIndexInputEnd,
										matrix1LineIndexInputEnd,matrix2ColumnIndexInputBegin,
										matrix2LineIndexInputBegin,
										matrix2ColumnIndexInputEnd,
										matrix1LineIndexInputEnd);
								console.log(matrixDatasetCells);
								$scope.data = matrixDatasetCells.data;
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
							
							function getValuesDatasetMatrixs(matrix1ColumnIndexInputBegin,
									matrix1LineIndexInputBegin, matrix1ColumnIndexInputEnd,
									matrix1LineIndexInputEnd,matrix2ColumnIndexInputBegin,
									matrix2LineIndexInputBegin, matrix2ColumnIndexInputEnd,
									matrix2LineIndexInputEnd) {
								var dataset = DocumentiStat.createNew();
								for (var line = matrix1LineIndexInputBegin; line <= matrix1LineIndexInputEnd; line++) {
									for (var column = matrix1ColumnIndexInputBegin; column <= matrix1ColumnIndexInputEnd; column++) {
										dataset.addCell('dataset_1', hot
												.getRowHeader(line), hot
												.getColHeader(column), hot
												.getDataAtCell(line, column));
									}
								}
								for (var line = matrix2LineIndexInputBegin; line <= matrix2LineIndexInputEnd; line++) {
									for (var column = matrix2ColumnIndexInputBegin; column <= matrix2ColumnIndexInputEnd; column++) {
										dataset.addCell('dataset_2', hot
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

							function callTransformTranspose() {

								console.log("--> Called transformTranspose!");
								var promise = iTransformController.execute(
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn, 'transformTranspose');

								promise
										.then(
												function(response) {

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
												},
												function(response) {
													console
															.log('Error to call transformTranspose');
												});

							}

							function callTransformScale() {

								console.log("--> Called transformScale!");
								var promise = iTransformController.execute(
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn, 'transformScale');

								promise
										.then(
												function(response) {

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
												},
												function(response) {
													console
															.log('Error to call transformScale');
												});

							}

							function callTransformAddScalar() {

								console.log("--> Called transformAddScalar!");
								var promise = iTransformController.execute(
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn, 'transformAddScalar');

								promise
										.then(
												function(response) {

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
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn, 'transformAddTwoDatasets');

								promise
										.then(
												function(response) {

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
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn,
										'transformMultiplyTwoDatasets');

								promise
										.then(
												function(response) {

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
										$scope.data,$scope.scalar,$scope.outputBeginLine,$scope.outputBeginColumn,
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