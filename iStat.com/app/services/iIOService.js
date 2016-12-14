'use strict';

angular
	.module('iStatServices')
	.factory(
				'iIOService',
				[
		'$http',
		'$q',

		function ($http, $q) {

			var mainRoute = 'http://localhost:8080/iStatCalc';

			return {

				execute: function ($data, $nameOfMethod) {

					var urlRequest = mainRoute + "/" + $nameOfMethod;

					var request = {
						method: "POST",
						url: urlRequest,
						headers: {
							'Content-Type': 'application/json;charset=utf-8'
						},
						data: $data
					};

					var def = $q.defer();

					$http(request).then(
						function success(response, status) {
							console.log(status);
							def.resolve(response);
						},
						function error(response, status) {
							console.log(status);
							def.reject("Reject");
						});

					return def.promise;

				}
			}
		}]);