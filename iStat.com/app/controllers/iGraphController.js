'use strict';

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
									template : 'popUpGraph.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.clickToOpenChart = function($data, $name) {
								var inputType = $data.inputType;
								var values = null;
								if (inputType == 'row') {
									var rowIndex = getLineFromName($data.inputID);
									values = getValuesDataset(hot.countCols(),
											null, rowIndex, null);
								} else if (inputType == 'column') {
									var columnIndex = getColFromName($data.inputID);
									values = getValuesDataset(null, hot
											.countRows(), null, columnIndex);
								} else if (inputType == 'dataset') {
									values = getValuesDataset(hot.countCols(),
											hot.countRows(), null, null);
								}
								$scope.dataset = values;
								console.log($scope.dataset);

								var newScopeChart = $scope;
								newScopeChart.graphName = $name;
								// FIXME scope.dataset not changing
								newScopeChart.dataset = values;
								console.log(newScopeChart);

								switch ($name) {
								case 'Pie Chart':
									ngDialog.open({
										template : 'popUpPieChart.html',
										className : 'ngdialog-theme-default',
										scope : newScopeChart,
									});
									break;
								case 'Bar Chat':
									ngDialog.open({
										template : 'popUpBarChart.html',
										className : 'ngdialog-theme-default',
										scope : newScopeChart
									});
									break;
								case 'Line Chart':
									ngDialog.open({
										template : 'popUpLineChart.html',
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
								console.log(rowIndex);
								if (rowIndex != null && columnIndex == null) {
									console.log('rows only');
									for (var column = 0; column <= maxColumn; column++) {
										var value = hot.getDataAtCell(rowIndex,
												column);
										if (value != null || value != '') {
											values.push(value);
										}
									}

								} else if (rowIndex == null
										&& columnIndex != null) {
									console.log('columns only');
									for (var row = 0; row <= maxRow; row++) {
										var value = hot.getDataAtCell(row,
												columnIndex);
										if (value != null || value != '') {
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
								return values;
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