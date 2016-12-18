'use strict';

angular.module('iStatControllers').controller('iTransformController',
    ['$scope', '$http', 'iTransformService','ngDialog',

        function($scope, $http, iTransformController,ngDialog) {

            $scope.request = '';

            //$scope.data = "{    \"datasets\": [        {            \"name\": \"dataset_1\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        },        {            \"name\": \"dataset_2\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        }    ]}";

            $scope.response = new Object();
            
            $scope.clickToOpen = function($name) {

				var newScope = $scope;
				newScope.transformName = $name;
				ngDialog.open({
					template : 'popUpTransform.html',
					className : 'ngdialog-theme-default',
					scope : newScope
				});
			};
			
            $scope.clickToOpenDatasets = function($name) {

				var newScope = $scope;
				newScope.transformName = $name;
				ngDialog.open({
					template : 'popUpTransformDatasets.html',
					className : 'ngdialog-theme-default',
					scope : newScope
				});
			};
			
            $scope.clickToOpenScale = function($name) {

				var newScope = $scope;
				newScope.transformName = $name;
				ngDialog.open({
					template : 'popUpTransformScale.html',
					className : 'ngdialog-theme-default',
					scope : newScope
				});
			};

			$scope.confirm = function($data) {
				console.log($scope.transformName);
				convertInputIntoRequest($data);
				switch ($scope.transformName) {
				case 'Transpose Dataset':
					callTransformTranspose();
					break;
				case 'Scale':
					callTransformScale();
					break;
				case 'Add a Scalar':
					callTransformAddScalar();
					break;
				case 'Add Two Datasets':
					callTransformAddTwoDatasets();
					break;
				case 'Multiply Two Datasets':
					callTransformMultiplyTwoDatasets();
					break;
				case 'Linear Interpolation':
					callTransformLinearInterpolation();
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
                var promise = iTransformController.execute($scope.data, 'transformTranspose');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformTranspose');
                });

            }


            function callTransformScale(){

                console.log("--> Called transformScale!");
                var promise = iTransformController.execute($scope.data, 'transformScale');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformScale');
                });

            }

            function callTransformAddScalar() {

                console.log("--> Called transformAddScalar!");
                var promise = iTransformController.execute($scope.data, 'transformAddScalar');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformAddScalar');
                });

            }

            function callTransformAddTwoDatasets() {

                console.log("--> Called transformAddTwoDatasets!");
                var promise = iTransformController.execute($scope.data, 'transformAddTwoDatasets');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformAddTwoDatasets');
                });

            }

            function callTransformMultiplyTwoDatasets() {

                console.log("--> Called transformMultiplyTwoDatasets!");
                var promise = iTransformController.execute($scope.data, 'transformMultiplyTwoDatasets');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformMultiplyTwoDatasets');
                });

            }
            
            function callTransformLinearInterpolation() {

                console.log("--> Called transformLinearInterpolation!");
                var promise = iTransformController.execute($scope.data, 'transformLinearInterpolation');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call transformLinearInterpolation');
                });

            }
        }

    ]);