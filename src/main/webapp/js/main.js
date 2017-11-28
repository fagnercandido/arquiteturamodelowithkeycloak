'use strict';

var app = angular.module('myapp', ['ngRoute', 'appControllers','restangular']);

app.config(['$routeProvider', 'RestangularProvider', function($routeProvider, RestangularProvider) {
	RestangularProvider.setBaseUrl('http://localhost:8000');
	$routeProvider.
	when('/main', {
		templateUrl: 'views/main.html',
		controller: 'MainController'
	}).
	otherwise({
		redirectTo: '/main'
	});
}]);

// use bearer token when calling backend
app.config(['$httpProvider', function($httpProvider) {
	var isExpired = window._keycloak.isTokenExpired();
	var token = window._keycloak.token;
	
	if (isExpired) {
		window._keycloak.updateToken(5)
		.success(function() {
			$httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
		})
		.error(function() {
			console.error('Failed to refresh token');
		});
	}
	$httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
}]);


// application startup hook
app.run(['$rootScope', '$location', '$http', '$route',
         function ($rootScope, $location, $http, usersService, $route) {

}]);

var appControllers = angular.module('appControllers', []);

// provide keycloak as factory to make it injectable
angular.module('myapp').factory('authorization', function ($window) {
	return $window._keycloak;
});

// on every request, authenticate user first
angular.element(document).ready(() => {
	window._keycloak = Keycloak('keycloak/keycloak.json');

	window._keycloak.init({
		onLoad: 'login-required'
	})
	.success((authenticated) => {
		if(authenticated) {
			window._keycloak.loadUserProfile().success(function(profile){
				angular.bootstrap(document, ['myapp']); // manually
															// bootstrap Angular
			});
		}
		else {
			window.location.reload();
		}
	})
	.error(function () {
		window.location.reload();
	});
});