'use strict';

angular.module('iStatControllers').controller('iTransformController',
    ['$scope', '$http', 'iTransformService',

        function($scope, $http, iTransformController) {

            $scope.request = '';

            $scope.data = "{    \"datasets\": [        {            \"name\": \"dataset_1\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        },        {            \"name\": \"dataset_2\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        }    ]}";

            $scope.response = new Object();

            $scope.transformTranspose = function($data) {

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


            $scope.transformScale = function($data) {

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

            $scope.transformAddScalar = function($data) {

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

            $scope.transformAddTwoDatasets = function($data) {

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

            $scope.transformMultiplyTwoDatasets = function($data) {

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
        }

    ]);