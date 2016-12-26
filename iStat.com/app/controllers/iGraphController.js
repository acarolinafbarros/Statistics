'use strict';
var labelsChart = '';
var datasetChart = '';
angular
		.module('iStatControllers')
		.controller(
				'iGraphController',
				[
						'$scope',
						'$http',
						'ngDialog',

						function($scope, $http, ngDialog) {

							$scope.dataset = null;

							$scope.clickToOpen = function($name) {

								var newScope = $scope;
								newScope.graphName = $name;
								ngDialog.open({
									template : 'popUps/popUpGraph.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.clickToOpenChart = function($data, $name) {
								var validInput = validateInput($data);
								if (validInput) {
									var inputType = $data.inputType;
									var result = null;
									if (inputType == 'row') {
										var rowIndex = getLineFromName($data.inputID);
										result = getValuesDataset(hot
												.countCols(), null, rowIndex,
												null);
									} else if (inputType == 'column') {
										var columnIndex = getColFromName($data.inputID);
										result = getValuesDataset(null, hot
												.countRows(), null, columnIndex);
									} else if (inputType == 'dataset') {
										result = getValuesDataset(hot
												.countCols(), hot.countRows(),
												null, null);
									}
									labelsChart = result[0];
									datasetChart = result[1];
									$scope.closeThisDialog();
									var newScopeChart = $scope;
									newScopeChart.graphName = $name;

									switch ($name) {
									case 'Pie Chart':
										ngDialog
												.open({
													template : 'popUps/popUpPieChart.html',
													className : 'ngdialog-theme-default',
													scope : newScopeChart
												});
										break;
									case 'Bar Chart':
										if (datasetChart.length == 1) {
											datasetChart[0].backgroundColor = datasetChart[0].backgroundColor[0];
											datasetChart[0].borderColor = datasetChart[0].borderColor[0];
										}
										ngDialog
												.open({
													template : 'popUps/popUpBarChart.html',
													className : 'ngdialog-theme-default',
													scope : newScopeChart
												});
										break;
									case 'Line Chart':
										for (var datasetIndex = 0; datasetIndex < datasetChart.length; datasetIndex++) {
											datasetChart[datasetIndex].backgroundColor = datasetChart[datasetIndex].backgroundColor[0];
											datasetChart[datasetIndex].borderColor = datasetChart[datasetIndex].borderColor[0];
										}
										ngDialog
												.open({
													template : 'popUps/popUpLineChart.html',
													className : 'ngdialog-theme-default',
													scope : newScopeChart
												});
										break;
									default:
										break;
									}
								}
							};
							
							function validateInput($data) {
								if ($data) {
									if ($data.inputType) {
										switch ($data.inputType) {
										case 'row':
										case 'column':
											if(!$data.inputID){
												alert("Invalid input! You must fill the field ID!");
												return false;
											}
											break;
										case 'dataset':
											break;
										default:
											break;
										}
									} else {
										alert("Invalid input! You must select a input type!");
										return false;
									}
									return true;
								} else {
									alert("Invalid input! All the fields must be fill!");
									return false;
								}
							}

							function getValuesDataset(maxColumn, maxRow,
									rowIndex, columnIndex) {
								var datasets = new Array();
								var serieLabel = new Array();
								var values = new Array();
								var labels = new Array();

								if (rowIndex != null && columnIndex == null) {
									serieLabel.push(hot.getRowHeader(rowIndex));
									for (var column = 0; column <= maxColumn; column++) {
										var value = hot.getDataAtCell(rowIndex,
												column);
										if (value != null && value != '') {
											labels.push(hot
													.getColHeader(column));
											values.push(value);
										}
									}
									if (values[0]) {
										datasets.push({
											"label" : serieLabel,
											"data" : values,
											"backgroundColor" : getColor(
													values.length, false),
											"borderColor" : getColor(
													values.length, false),
											"borderWidth" : 1
										});
									}

								} else if (rowIndex == null
										&& columnIndex != null) {
									serieLabel.push(hot
											.getColHeader(columnIndex));
									for (var row = 0; row <= maxRow; row++) {
										var value = hot.getDataAtCell(row,
												columnIndex);
										if (value != null && value != '') {
											labels.push(hot.getRowHeader(row));
											values.push(value);
										}
									}
									if (values[0]) {
										datasets.push({
											"label" : serieLabel,
											"data" : values,
											"backgroundColor" : getColor(
													values.length, false),
											"borderColor" : getColor(
													values.length, false),
											"borderWidth" : 1
										});
									}

								} else {
									var first = true;
									var labelsAux = new Array();
									for (var column = 0; column <= maxColumn; column++) {
										values = new Array();
										labelsAux = new Array();
										for (var row = 0; row <= maxRow; row++) {
											var value = hot.getDataAtCell(row,
													column);
											if (value != null && value != '') {
												if (first) {
													serieLabel
															.push(hot
																	.getColHeader(column));
													first = false;
												}
												if (labels.indexOf(hot
														.getRowHeader(row)) == -1) {
													labels.push(hot
															.getRowHeader(row));
												}
												labelsAux.push(hot
														.getRowHeader(row));
												values.push(value);
											}
										}
										first = true;
										var colors = getColor(values.length,
												true);
										if (serieLabel[0]) {
											datasets.push({
												"label" : serieLabel,
												"data" : values,
												"backgroundColor" : colors,
												"borderColor" : colors,
												"borderWidth" : 1
											});
										}
										serieLabel = new Array();
									}
								}
								var result = new Array();
								result.push(labels);
								result.push(datasets);
								return result;
							}

							function getColor(size, equalLines) {
								var result = new Array();
								if (equalLines) {
									var rgb = [];

									for (var i = 0; i < 3; i++) {
										rgb.push(Math
												.floor(Math.random() * 255));
									}
									for (var numberColors = 0; numberColors < size; numberColors++) {
										result.push('rgba(' + rgb.join(',')
												+ ', 0.4)');
									}
								} else {

									for (var numberColors = 0; numberColors < size; numberColors++) {
										var rgb = [];

										for (var i = 0; i < 3; i++) {
											rgb
													.push(Math.floor(Math
															.random() * 255));
										}

										result.push('rgba(' + rgb.join(',')
												+ ', 0.4)');
									}
								}
								return result;
							}

							function getColFromName(name) {
								var n_cols = hot.countCols();
								var i = 1;
								for (i = 0; i <= n_cols; i++) {
									if (name.toLowerCase() == hot.getColHeader(
											i).toString().toLowerCase()) {
										return i;
									}
								}
								return -1; // return -1 if nothing can be found
							}

							function getLineFromName(name) {
								var n_rows = hot.countRows();
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
						}

				]);