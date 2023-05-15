var app = angular.module('competitions', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});

app.controller("CompetitionsController", function ($scope, $http) {

    $scope.successGetCompetitionsCallback = function (response) {
         $scope.competitionsList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetCompetitionsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of competitions";
    };

    $scope.getCompetitions = function () {
        $http.get('/public/rest/competitions').then($scope.successGetCompetitionsCallback, $scope.errorGetCompetitionsCallback);
    };

    $scope.successDeleteCompetitionCallback = function (response) {
        for (var i = 0; i < $scope.competitionsList.length; i++) {
            var j = $scope.competitionsList[i];
            if (j.id === $scope.deletedId) {
                $scope.competitionsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteCompetitionCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Unable to delete competition; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteCompetition = function (id) {
        $scope.deletedId = id;

        $http.delete('/public/rest/competitions/' + id).then($scope.successDeleteCompetitionCallback, $scope.errorDeleteCompetitionCallback);
    };


    $scope.successGetCompetitionCallback = function (response) {
        $scope.competitionsList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetCompetitionCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on competition number "+$scope.inputnumber;
    };

    $scope.successAddCompetitionCallback = function (response) {

        $http.get('/public/rest/competitions/' + $scope.inputnumber).then($scope.successGetCompetitionCallback, $scope.errorGetCompetitionCallback);
        $scope.errormessage="";
    };

    $scope.errorAddCompetitionCallback = function (error) {
        console.log(error);
        if (error.status === 405) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
        if (error.status === 403) {
            $scope.errormessage = "You do not have permissions to do that";
        } else
            $scope.errormessage = "Impossible to add new school; check if it's number is unique";
    };

    $scope.addCompetition = function () {
        $http.post('/public/rest/competitions/' + $scope.inputnumber + "/" + $scope.inputname
        + "/" + $scope.inputdate  + "/" + $scope.inputplace).then($scope.successAddCompetitionCallback,
        $scope.errorAddCompetitionCallback);
    };
});
