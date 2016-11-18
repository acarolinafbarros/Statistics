iStat.config([ '$routeProvider', '$locationProvider',

    function($routeProvider, $locationProvider) {
        $routeProvider.when('/', {
            templateUrl : 'views/index.html',
            controller : 'indexController'
        }).otherwise({
            redirectTo : '/'
        });
    }

]);