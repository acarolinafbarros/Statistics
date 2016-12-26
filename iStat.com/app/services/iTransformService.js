'use strict';

angular
	.module('iStatServices')
	.factory(
				'iTransformService',
				[
		'$http',
		'$q',

		function ($http, $q) {

			var mainRoute = 'http://localhost:8080/iStatTransform';

			return {

				execute: function ($data,$scalar,$outputBeginLine,$outputBeginColumn, $nameOfMethod) {
					var urlRequest = '';
					console.log($scalar);
					if($scalar){
						urlRequest = mainRoute + "/" + $nameOfMethod+"?scale="+$scalar+"&finalLine="+$outputBeginLine+"&finalColumn="+$outputBeginColumn;
					}else{
						urlRequest = mainRoute + "/" + $nameOfMethod+"?finalLine="+$outputBeginLine+"&finalColumn="+$outputBeginColumn;
					}

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
							console.log(response.data.status.code);
							def.resolve(response);
						},
						function error(response, status) {
							var errorMessage = 'Error!';
							var errorCode = response.data.status.code;
							if(errorCode == '9999'){
								errorMessage = 'Service temporarily unavailable. Please try again later!'
							}else if(errorCode== '1001'){
								errorMessage = 'Invalid input!'
							}
							def.reject(errorMessage);
						});

					return def.promise;

				}
			}
		}]);