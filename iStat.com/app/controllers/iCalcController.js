'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iCalcController',
				[
						'$scope',
						'$http',
						'iCalcService',
						'ngDialog',

						function($scope, $http, iCalcService, ngDialog) {
							$scope.clickToOpen = function($name) {
								
								$scope.calculateName = $name;
								console.log($scope.calculateName);
								ngDialog.open({
									template : 'Popup.html',
									className : 'ngdialog-theme-default'
								});
							};

							$scope.confirm = function($data) {
								console.log('confirm');
								console.log($scope.data);
								convertInputIntoRequest($data);
								//callCalculateRowTotal();
							};

							$scope.request = '';

							//$scope.data = "{    \"datasets\": [        {            \"name\": \"dataset_1\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        },        {            \"name\": \"dataset_2\",            \"cells\": [                {                    \"line\": \"1\",                    \"column\": \"A\",                    \"value\": 100                },                {                    \"line\": \"2\",                    \"column\": \"A\",                    \"value\": 200                }            ]        }    ]}";

							$scope.response = new Object();

							$scope.calculateRowTotal = function($data) {
								convertInputIntoRequest($data);
								callCalculateRowTotal();
							}
							
							function convertInputIntoRequest($data){
								console.log('convertInputIntoRequest');
								console.log($data.inputBeginColumn);
								var columnIndexInputBegin = getColFromName($data.inputBeginColumn);
								var lineIndexInputBegin = $data.inputBeginLine;
								var columnIndexInputEnd = getColFromName($data.inputEndColumn);
								var lineIndexInputEnd = $data.inputEndLine;
								var datasetData = getValuesDataset(columnIndexInputBegin,lineIndexInputBegin,columnIndexInputEnd,lineIndexInputEnd);
								console.log(datasetData);
								//$scope.data = 
							}
							
							function getValuesDataset(columnIndexInputBegin,lineIndexInputBegin,columnIndexInputEnd,lineIndexInputEnd){
								var sameLine = false;
								var sameColumn = false;
								if(columnIndexInputBegin==columnIndexInputEnd){
									console.log(columnIndexInputBegin);
									console.log("equal columns");
									sameColumn = true;
								}
								if(lineIndexInputBegin==lineIndexInputEnd){
									console.log(lineIndexInputBegin);
									console.log("equal lines");
									sameLine = true;
								}
								if(sameColumn && sameLine){
									return hot.getDataAtCell(lineIndexInputBegin,columnIndexInputBegin);
								}
								return null;
							}
							
							function getColFromName(name)
							{
							    var n_cols  =   hot.countCols();
							    console.log(n_cols);
							    var i       =   1;     

							    for (i=0; i<=n_cols; i++)
							    {
							        if (name.toLowerCase() == hot.getColHeader(i).toLowerCase()) {
							            return i;
							        }
							    }
							    return -1; //return -1 if nothing can be found
							}
							
							// Internal function
							function callCalculateRowTotal() {
								console.log('calculate')

								var promise = iCalcService
										.calculateRowTotal($scope.data);

								promise
										.then(
												function(response) {

													if (response.data != null) {

														$scope.response = response.data;
														console
																.log($scope.response);

														hot
																.setDataAtCell(
																		1,
																		1,
																		$scope.response.value);

													}
												},
												function(response) {
													console
															.log('Error to call callCalculateRowTotal');
												});
							}
						}

				]);