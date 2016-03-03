/**
 * Created by P0015475 on 2015/9/11.
 */
var myApp = angular.module('myApp', ['ngRoute']);
myApp.config(function ($routeProvider, $httpProvider) {


});
myApp.controller('centerController', function ($rootScope, $location) {
    $rootScope.articleListPage = function () {
        $location.url('article/list.html');
    };
});