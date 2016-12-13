'use strict';

angular.module('iStatControllers').controller(
		'indexController',
		[
				'$scope',
				'$http',

				function($scope, $http) {
					$(window).resize(function() {
						location.reload();
					});
					$('.dropdown-toggle').dropdown();
					var dataObject = [ {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					}, {
						A : ''
					} ];
					var hotElement = document.querySelector('#hot');
					var hotElementContainer = hotElement.parentNode;
					var hotSettings = {
						data : dataObject,
						columns : [ {
							data : 'A',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'B',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'C',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'D',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'E',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'F',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'G',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'H',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'I',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'J',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'K',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'L',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'M',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'N',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'O',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'P',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'Q',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'R',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'S',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'T',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'U',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'V',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'W',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'X',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'Y',
							type : 'numeric',
							format : '0.0000'
						}, {
							data : 'Z',
							type : 'numeric',
							format : '0.0000'
						} ],
						stretchH : 'all',
						width : $(document).width(),
						autoWrapRow : true,
						height : $(document).height(),
						maxRows : 200,
						rowHeaders : true,
						colHeaders : [ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
								'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
								'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ],
						columnSorting : true,
						sortIndicator : true,
						autoColumnSize : {
							samplingRatio : 23
						},
						mergeCells : true,
						manualRowResize : true,
						manualColumnResize : true,
						manualRowMove : true,
						manualColumnMove : true,
						contextMenu : true,
						multiSelect : true
					};
					var hot = new Handsontable(hotElement, hotSettings);

				}

		]);