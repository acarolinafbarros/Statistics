iStat.config([ '$routeProvider', '$locationProvider',

    function($routeProvider, $locationProvider) {
        $routeProvider.when('/', {
            templateUrl : 'index.html'
        }).otherwise({
            redirectTo : '/'
        });
    }

]);