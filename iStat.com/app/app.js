'use strict';

var iStat = angular.module("iStat", [ 'ngRoute', 'iStatControllers','iStatServices','ngDialog', 'ngFileUpload']);

// Active the controllers available
var iStatControllers = angular.module('iStatControllers', []);
var iStatServices = angular.module('iStatServices', []);
