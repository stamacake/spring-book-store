var app = angular.module('app', []);
var contextPath = 'http://localhost:8189/store'


app.controller('booksController', function ($scope, $http) {
    $scope.page = {};
    $scope.books = { title: "",minPrice: 0,maxPrice: 50000, publishYear: "" };

    fillTable = function () {
        $http.get(contextPath + '/api/v1/books/filter')
            .then(function (response) {
                $scope.page = response.data;
            });
    }
    fillTable();

    $scope.filter = function () {
        $http({
            method: 'GET',
            url: contextPath + '/api/v1/books/filter',
            params: {
                minPrice: $scope.books.minPrice,
                maxPrice: $scope.books.maxPrice,
                publishYear: $scope.books.publishYear,
                title: $scope.books.title
            }
        }).then(function (response) {
            $scope.page = response.data;
        });
    }

    $scope.pagin = function (pageNumber) {
        $http({
            method: 'GET',
            url: contextPath + '/api/v1/books/filter',
            params: {
                p: pageNumber,
                minPrice: $scope.books.minPrice,
                maxPrice: $scope.books.maxPrice,
                publishYear: $scope.books.publishYear,
                title: $scope.books.title
            }
        }).then(function (response) {
            $scope.page = response.data;
        });
    }
});