'use strict';

angular.module('iStatControllers').controller('iCalcController',
    ['$scope', '$http', 'iCalcService',

        function($scope, $http, iCalcService) {

            $scope.request = '';

            $scope.data = "{    \"datasets\": [        {            \"name\": \"dataset_1\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        },        {            \"name\": \"dataset_2\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        }    ]}";

            $scope.response = new Object();

            $scope.calculateRowColumnTotal = function($data) {

				console.log("--> Called calculateRowColumnTotal!");
                console.log($data);
                $scope.data = $data;
                callCalculateRowTotal();
            }

            $scope.calculateStandardDeviation = function($data) {

				console.log("--> Called calculateStandardDeviation!");
                var promise = iCalcService.execute($scope.data, 'calculateStandardDeviation');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateStandardDeviation');
                });

            }

            $scope.calculateVariance = function($data) {

				console.log("--> Called calculateVariance!");
                var promise = iCalcService.execute($scope.data, 'calculateVariance');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateVariance');
                });

            }

            $scope.calculateMidrange = function($data) {

				console.log("--> Called calculateMidrange!");
                var promise = iCalcService.execute($scope.data, 'calculateMidrange');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateMidrange');
                });

            }

            $scope.calculateMode = function($data) {

				console.log("--> Called calculateMode!");
                var promise = iCalcService.execute($scope.data, 'calculateMode');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateMode');
                });

            }

            $scope.calculateGeometricMean = function($data) {

				console.log("--> Called calculateGeometricMean!");
                var promise = iCalcService.execute($scope.data, 'calculateGeometricMean');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateGeometricMean');
                });

            }

            $scope.calculateMedian = function($data) {

				console.log("--> Called calculateMedian!");
                var promise = iCalcService.execute($scope.data, 'calculateMedian');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call calculateMedian');
                });

            }

			/****************************
			 * 		INTERNAL METHOD		*
			 ****************************/

            function callCalculateRowTotal() {

                var promise = iCalcService.execute($scope.data, 'calculateRowColumnTotal');

                promise.then(function(response) {

                    if (response.data != null) {

                        $scope.response = response.data;
                        console.log($scope.response);

                        hot.setDataAtCell(1, 1, $scope.response.value);

                    }
                }, function(response) {
                    console.log('Error to call callCalculateRowTotal');
                });
            }
        }

    ]);