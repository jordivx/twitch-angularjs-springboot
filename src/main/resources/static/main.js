var app = angular.module("TwitchAngularJsSpringBoot", ["ngRoute"]);

app.config(function ($routeProvider) {
  $routeProvider
    .when("/", {
      templateUrl: "top-games-page",
      controller: "TopGamesController",
    })
    .when("/stream", {
      templateUrl: "streams-page",
      controller: "StreamsController",
    })
    .when("/channel", {
      templateUrl: "channels-page",
      controller: "ChannelsController",
    })
    .otherwise({
      redirectTo: "/",
    });
});

app.controller("TopGamesController", function($scope, $http) {
    $scope.topGames = [];

    _refreshTopGamesData();

    function _refreshTopGamesData() {
        $http({
            method: 'GET',
            url: '/top-games'
        }).then(
            function(res) { // success
                $scope.topGames = res.data.map(function(g) {
                    g.box_art_url = g.box_art_url.replace('{width}', '142');
                    g.box_art_url = g.box_art_url.replace('{height}', '190');
                    return g;
                });
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
});

app.controller("ChannelsController", function($scope, $http) {
    $scope.channels = [];
    $scope.channelQuery = "";

    $scope.searchChannel = function() {
        _refreshChannelsData();
    };


    function _refreshChannelsData() {
        $http({
            method: 'GET',
            url: '/search-channels',
            params: {query: $scope.channelQuery}
        }).then(
            function(res) { // success
                $scope.channels = res.data.map(function(c) {
                    c.thumbnail_url = c.thumbnail_url.replace('{width}', '200');
                    c.thumbnail_url = c.thumbnail_url.replace('{height}', '200');
                    return c;
                });
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
});

app.controller("StreamsController", function($scope, $http) {
    $scope.streams = [];

    _refreshStreamsData();

    function _refreshStreamsData() {
        $http({
            method: 'GET',
            url: '/streams'
        }).then(
            function(res) { // success
                $scope.streams = res.data.map(function(s) {
                    s.thumbnail_url = s.thumbnail_url.replace('{width}', '220');
                    s.thumbnail_url = s.thumbnail_url.replace('{height}', '124');
                    return s;
                });
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
});