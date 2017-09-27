'use strict';

(() => {
  angular
    .module('neverland.search')
    .controller('ResultsController', ResultsController);

  ResultsController.$inject = [
    'searchService', 'possibleTrips', 'accessService', '$scope', '$state', '$stateParams', '$log'
  ];

  function ResultsController(
    searchService, possibleTrips, accessService, $scope, $state, $stateParams, $log
  ) {
    $log.debug('ResultsController initializing...')

    this.tripsList = possibleTrips;
    $log.debug(this.tripsList)
    $log.debug(this.tripsList.entry)
    this.loggedInUser = accessService.currentUser;
    this.route;

    $log.debug(this.loggedInUser)
    $log.debug(this.loggedInUser.id)
    $log.debug(this.tripsList)

    this.book = (entry) => {
      return this.searchService
        .bookTrip(this.loggedInUser.userId, entry)
        .then($state.go('profile', {'userId': this.loggedInUser.userId}));
    }
  }
})();
