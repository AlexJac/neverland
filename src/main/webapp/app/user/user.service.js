'use strict';

(() => {
  angular
    .module('neverland.user')
    .service('userService', UserService);

    UserService.$inject = ['$http', '$state', '$log', 'accessService'];

    function UserService($http, $state, $log, accessService) {
        this.accessService = accessService;

        this.getUserById = (userId) => {
          return $http
            .get('./api/user/' + userId)
            .then(response => response.data)
        }

        this.getCurrentTrips = (userId) => {
          return $http
            .get('./api/user/' + userId + '/currentTrips')
            .then(response => response.data)
        }

        this.getCompletedTrips = (userId) => {
          return $http
            .get('./api/user/' + userId + '/routes/completed')
            .then(response => response.data)
        }

        this.getCanceledTrips = (userId) => {
          return $http
            .get('./api/user/' + userId + '/routes/canceled')
            .then(response => response.data)
        }

        this.patchCancelTrip = (userId, routeId) => {
          return $http
            .patch('./api/user/' + userId + '/routes/' + routeId + '/cancel')
            .then(response => response.data)
        }
    }
})();
