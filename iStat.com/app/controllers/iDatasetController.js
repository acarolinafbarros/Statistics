'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iDatasetController',
				[
						'$scope',
						'$http',
						'iDatasetService',
						'ngDialog',

						function($scope, $http, iDatasetController, ngDialog) {

							$scope.request = '';

							$scope.data = "{    \"datasets\": [        {            \"name\": \"dataset_1\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        },        {            \"name\": \"dataset_2\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        }    ]}";

							$scope.response = new Object();

							$scope.clickToOpen = function($name) {

								var newScope = $scope;
								newScope.datasetName = $name;
								ngDialog.open({
									template : 'popUps/popUpDataset.html',
									className : 'ngdialog-theme-default',
									scope : newScope
								});
							};

							$scope.confirm = function($data) {
								convertInputIntoRequest($data);
								switch ($scope.datasetName) {
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
								var datasetCells = getValuesDataset();
								console.log(datasetCells);
								var datasetName = $data.datasetName;
								// TODO Chamar método do Nuno para converter
								// para o JSON correto.
								// $scope.data =
							}

							function getValuesDataset() {
								var values = new Array();
								for (var line = 0; line < hot.countRows(); line++) {
									for (var column = 0; column < hot
											.countCols(); column++) {
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

							function callOpenDataset() {

								console.log("--> Called openDataset!");
								var promise = iDatasetService.execute(
										$scope.data, 'openDataset');

								promise.then(function(response) {

									if (response.data != null) {

										$scope.response = response.data;
										console.log($scope.response);

										hot.setDataAtCell(1, 1,
												$scope.response.value);

									}
								}, function(response) {
									console.log('Error to call openDataset');
								});

							}

							function callSaveDataset() {

								console.log("--> Called saveDataset!");
								var promise = iDatasetService.execute(
										$scope.data, 'saveDataset');

								promise.then(function(response) {

									if (response.data != null) {

										$scope.response = response.data;
										console.log($scope.response);

										hot.setDataAtCell(1, 1,
												$scope.response.value);

									}
								}, function(response) {
									console.log('Error to call saveDataset');
								});

							}
						}

				]);