'use strict';

angular.module('iStatControllers').controller('iCalcController',
		[ '$scope', '$http', 'iCalcService',

		function($scope, $http, iCalcService) {

			$scope.request = '';

			$scope.data = '';

			$scope.response = new Object();

			$scope.calculateRowTotal = function($data) {
				console.log($data);
				$scope.data = $data;
				callCalculateRowTotal();
			}
			// Internal function
			function callCalculateRowTotal() {

				var promise = iCalcService.calculateRowTotal($scope.data);

				promise.then(function(response) {

					if (response.data != null) {

						$scope.response = response.data;
						console.log($scope.response);
					}
				}, function(response) {
					console.log('Error to call callCalculateRowTotal');
				});
			}
		}

		]);