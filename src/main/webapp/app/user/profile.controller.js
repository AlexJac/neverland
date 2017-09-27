'use strict';

(() => {
  angular
    .module('neverland.user')
    .controller('ProfileController', ProfileController);

  ProfileController.$inject = [
    'user',
    'accessService',
    'currentTripsList',
    //'completedTrips',
    'userService',
    //'canceledTrips',
    '$scope',
    '$state',
    '$stateParams',
    '$log'
  ];

  function ProfileController(
    user,
    accessService,
    currentTripsList,
    //completedTrips,
    userService,
    //canceledTrips,
    $scope,
    $state,
    $stateParams,
    $log
  ) {
    $log.debug('ProfileController initializing...')
    this.user = user;
    this.currentTrips = currentTripsList;
    this.loggedInUser = accessService.currentUser;
    //this.currentList = currentTrips;
    //this.completedList = completedTrips;
    //this.canceledList = canceledTrips;
    this.route;
    this.userService = userService;

    this.cancel = (route) => {
      return this.userService
        .patchCancelTrip(this.loggedInUser.userId, route.routeId)
        .then($state.go($state.current, {}, {reload: true}));
    }
  }
})();
