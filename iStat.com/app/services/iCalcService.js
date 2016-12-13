'use strict';

angular
		.module('iStatServices')
		.factory(
				'iCalcService',
				[
						'$http',
						'$q',

						function($http, $q) {

							return {

								calculateRowTotal : function($data) {

									// TODO: Read the endpoint from file
									var urlRequest = "http://localhost:8080/iStatCalc/calculateRowColumnTotal";

									var request = {
										method : "POST",
										url : urlRequest,
										headers : {
											'Content-Type' : 'application/json;charset=utf-8'
										},
										data : $data
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

								},

								test2Log : function() {
									console
											.log("Call Serive DNA Tools (test2)");
								}

							}

						} ]);