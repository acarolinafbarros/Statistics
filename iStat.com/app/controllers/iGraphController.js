'use strict';
var valuesChart = '';
var labelsChart = '';
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
								var inputType = $data.inputType;
								var result = null;
								if (inputType == 'row') {
									var rowIndex = getLineFromName($data.inputID);
									result = getValuesDataset(hot.countCols(),
											null, rowIndex, null);
								} else if (inputType == 'column') {
									var columnIndex = getColFromName($data.inputID);
									result = getValuesDataset(null, hot
											.countRows(), null, columnIndex);
								} else if (inputType == 'dataset') {
									result = getValuesDataset(hot.countCols(),
											hot.countRows(), null, null);
								}
								valuesChart = result[1];
								labelsChart = result[0];
								console.log(valuesChart);
								console.log(labelsChart);
								$scope.closeThisDialog();
								var newScopeChart = $scope;
								newScopeChart.graphName = $name;

								switch ($name) {
								case 'Pie Chart':
									ngDialog.open({
										template : 'popUps/popUpPieChart.html',
										className : 'ngdialog-theme-default',
										scope : newScopeChart
									});
									break;
								case 'Bar Chat':
									ngDialog.open({
										template : 'popUps/popUpBarChart.html',
										className : 'ngdialog-theme-default',
										scope : newScopeChart
									});
									break;
								case 'Line Chart':
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

							};

							function getValuesDataset(maxColumn, maxRow,
									rowIndex, columnIndex) {
								var values = new Array();
								var labels = new Array();
								console.log(rowIndex);
								if (rowIndex != null && columnIndex == null) {
									console.log('rows only');
									for (var column = 0; column <= maxColumn; column++) {
										var value = hot.getDataAtCell(rowIndex,
												column);
										if (value != null && value != '') {
											labels.push(hot.getColHeader(column));
											values.push(value);
										}
									}

								} else if (rowIndex == null
										&& columnIndex != null) {
									console.log('columns only');
									for (var row = 0; row <= maxRow; row++) {
										var value = hot.getDataAtCell(row,
												columnIndex);
										if (value != null && value != '') {
											labels.push(hot.getRowHeader(row));
											values.push(value);
										}
									}

								} else {
									console.log('dataset');
									for (var row = 0; row <= maxRow; row++) {
										for (var column = 0; column <= maxColumn; column++) {
											var value = hot.getDataAtCell(row,
													column);
											if (value != null && value != '') {
												values.push(value);
											}
										}
									}

								}
								var result = new Array();
								result.push(labels);
								result.push(values);
								return result;
							}

							function getColFromName(name) {
								var n_cols = hot.countCols();
								console.log(n_cols);
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
						}

				]);