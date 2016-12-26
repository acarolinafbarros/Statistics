'use strict';

angular
		.module('iStatControllers')
		.controller(
				'iIOController',
				[
						'$scope',
						'$http',
						'ngDialog',
						'DocumentiStat',
						'Upload',
						'$timeout',
						
						function($scope, $http, ngDialog, DocumentiStat, Upload, $timeout) {
							
							$scope.clickToExport = function($name) {
								var newScope = $scope;
                                newScope.ioName = $name;
                                ngDialog.open({
                                    template : 'popUps/popUpIO.html',
                                    className : 'ngdialog-theme-default',
                                    scope : newScope
                                });			
							};

							$scope.clickToImport = function(file, errFiles) {
								 	$scope.f = file;
									$scope.errFile = errFiles && errFiles[0];
									if (file) {
										
										console.log(file.type);

										var reader = new FileReader();
										reader.onload = function(e) {
											
											var content = e.target.result;
											
											if(file.type == 'application/json'){ 
												uploadJSON(content);
											}else if(file.type == 'text/xml'){
												uploadXML(content);
											}else if(file.type == 'application/csv'){
												console.log("csv");
											}
										}
										reader.readAsText(file);
										
									}   	

									

							};

							$scope.confirm = function($data, $name) {
								$scope.closeThisDialog();
								switch ($name) {
								case 'Export Dataset':

									var dataset = DocumentiStat.createNew();

									for (var row = 0; row <= hot.countRows(); row++) {
										for (var column = 0; column <= hot.countCols(); column++) {
											dataset.addCell('dataset', 
											hot.getRowHeader(row), 
											hot.getColHeader(column), 
											hot.getDataAtCell(row, column));
										}
									}
									
									if($data.fileType == 'xml'){
										downloadXML(dataset.data);
									}else if($data.fileType == 'json'){
										downloadJSON(dataset.data);
									}else if($data.fileType == 'csv'){
										downloadCSV(dataset.data);
									}
									break;
								default:
									break;
								}
							};

							function downloadXML($json){
								var x2js = new X2JS();
								var xmlAsStr = x2js.json2xml_str($json);	

								var blob = new Blob([xmlAsStr], { type : 'application/xml;charset=utf-8;' });
								var downloadLink = angular.element('<a></a>');
								downloadLink.attr('href',window.URL.createObjectURL(blob));
                        		downloadLink.attr('download', 'dataset.xml');
								downloadLink[0].click();
							} 	

							function downloadJSON($json){
								var blob = new Blob([JSON.stringify($json)], { type : 'application/json;charset=utf-8;' });
								var downloadLink = angular.element('<a></a>');
								downloadLink.attr('href',window.URL.createObjectURL(blob));
                        		downloadLink.attr('download', 'dataset.json');
								downloadLink[0].click();
							} 	

							function downloadCSV($json){
								console.log("Unsupported operation!");
							}

							function uploadJSON($content){
								var jsonObject = JSON.parse($content);
								var resultCells = jsonObject.datasets[0].cells;
								populateHotTable(resultCells);
							}

							function uploadXML($content){
								var x2js = new X2JS();
								var jsonObject = x2js.xml_str2json($content);
								var resultCells = jsonObject.datasets.cells;
								populateHotTable(resultCells);
							}

							function populateHotTable($cells){
								
								for (var cellIndex = 0; cellIndex < $cells.length; cellIndex++) {
									hot.setDataAtCell(
											getLineFromName($cells[cellIndex].line),
											getColFromName($cells[cellIndex].column),
											$cells[cellIndex].value);
								}
							}

							function getColFromName(name) {
								var n_cols = hot.countCols();
								var i = 1;

								for (i = 0; i <= n_cols; i++) {
									if (name.toLowerCase() == hot.getColHeader(i).toLowerCase()) {
										return i;
									}
								}
								return -1; // return -1 if nothing can be found
							}

							function getLineFromName(name) {
								var n_rows = hot.countRows();
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