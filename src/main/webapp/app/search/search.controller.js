'use strict';

(() => {
  angular
    .module('neverland.search')
    .controller('SearchController', SearchController);

  SearchController.$inject = [
    'allLocations', 'searchService', 'accessService', '$scope', '$state', '$stateParams', '$log'
  ];

  function SearchController(
    allLocations, searchService, accessService, $scope, $state, $stateParams, $log
  ) {
    $log.debug('SearchController initializing...')
    this.searchService = searchService;
    this.locations = allLocations;
    this.loggedInUser = accessService.currentUser;
    this.route;
    this.startLocation;
    this.endLocation;

    this.simulateQuery = true;
    this.isDisabled    = false;
    this.querySearch   = querySearch;
    this.selectedItemChange = selectedItemChange;
    this.searchTextChange   = searchTextChange;

    var loc1 = 3;
    var loc2 = 11;
    // this.findValue = () => {
    //   $log.debug(this.startLocation.locationId)
    //   $log.debug(this.endLocation.locationId)
    // }
    this.search = (startId, endId) => {
      $log.debug(startId)
      $log.debug(endId)
      $log.debug(this.startLocation.locationId)
      $log.debug(this.endLocation.locationId)
      // $state.go('results', [{'startId': this.startLocation.locationId}, {'endId': this.endLocation.locationId
      //$state.go('results', [{'startId': 5}, {'endId': 15}])
      this.searchService.searchForTrip(startId, endId)

    }

    this.locations.forEach(function (location) {
      $log.debug(location)
    })

    function querySearch (query) {
      var results = query ? this.locations.filter( createFilterFor(query) ) : this.locations,
      deferred;
      if (this.simulateQuery) {
        deferred = $q.defer();
        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
        return deferred.promise;
      } else {
      return results;
  }
}
  function searchTextChange(text) {
    $log.info('Text changed to ' + text);
  }
  function selectedItemChange(item) {
    $log.info('Item changed to ' + JSON.stringify(item));
  }

  function createFilterFor(query) {
    var lowercaseQuery = angular.lowercase(query);
    return function filterFn(allLocations) {
      return (allLocations.toLowerCase().indexOf(lowercaseQuery) === 0);
    };
  }
  }
})();
