'use strict';

(() => {
  angular
    .module('neverland.search')
    .constant('searchRoutes', {
      homeSearch: {
        url: '/search',
        templateUrl: 'app/search/search.template.html',
        controller: 'SearchController',
        controllerAs: '$searchCtrl',
        resolve: {
          allLocations: ['searchService', function(searchService){
            return searchService.getAllLocations();
        }]
      },
      data: {
        loggedIn: true
      }
    },

    results: {
      url: '/routes/{startId}/{endId}',
      templateUrl: 'app/search/search-results.template.html',
      controller: 'ResultsController',
      controllerAs: '$resultsCtrl',
      resolve: {
        possibleTrips: ['searchService', '$stateParams', function(searchService, $stateParams){
          return searchService.searchForTrip($stateParams.startId, $stateParams.endId);
        }]
      },
      data: {
        loggedIn: true
      }

    }
    })
  })();
