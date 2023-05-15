var app = angular.module('compes', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});

app.controller("CompesController", function ($scope, $http) {

    $scope.successGetCompesCallback = function (response) {
         $scope.compesList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetCompesCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of compes";
    };

    $scope.getCompes = function () {
        $http.get('/public/rest/compes').then($scope.successGetCompesCallback, $scope.errorGetCompesCallback);
    };

    $scope.successDeleteCompetCallback = function (response) {
        for (var i = 0; i < $scope.compesList.length; i++) {
            var j = $scope.compesList[i];
            if (j.id === $scope.deletedId) {
                $scope.compesList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteCompetCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Unable to delete compet; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteCompet = function (id) {
        $scope.deletedId = id;

        $http.delete('/public/rest/compes' + id).then($scope.successDeleteCompetCallback, $scope.errorDeleteCompetCallback);
    };


    $scope.successGetCompetCallback = function (response) {
        $scope.compesList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetCompetCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on compet number "+$scope.inputnumber;
    };

    $scope.successAddCompetCallback = function (response) {

        $http.get('/public/rest/compes/' + $scope.inputnumber).then($scope.successGetCompetCallback, $scope.errorGetCompetCallback);
        $scope.errormessage="";
    };

    $scope.errorAddCompetCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Impossible to add new school; check if it's number is unique";
    };

    $scope.addCompet = function () {
        $http.post('/public/rest/compes/' + $scope.inputnumber + "/" + $scope.inputname
        + "/" + $scope.inputdate  + "/" + $scope.inputplace).then($scope.successAddCompetCallback,
        $scope.errorAddCompetCallback);
    };
});
