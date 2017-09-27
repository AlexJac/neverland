'use strict';

(() => {
  angular
    .module('neverland.search')
    .service('searchService', SearchService);

    SearchService.$inject = ['$http', '$state', '$log', 'accessService'];

    function SearchService($http, $state, $log, accessService) {
      this.accessService = accessService;

      this.searchForGoesNowhereFlight = (startId) => {
        return $http
          .get('./api/routes/find/' + startId + '/nowhere')
          .then(response => response.data)
      }

      this.searchForTrip = (startId, endId) => {
        $log.debug(startId)
        $log.debug(endId)
        return $http
            .get('./api/routes/find/' + startId + '/' + endId)
            .then(response => response.data)
      }

      this.bookTrip = (userId, cf) => {
        return $http
          .post('./api/routes/book/' + userId, cf)
          .then(response => response.data)
      }

      this.getAllLocations = () => {
        return $http
          .get('./api/locations')
          .then(response => response.data)
      }

      this.getLocationById = (locationId) => {
        return $http
          .get('./api/locations/' + locationId)
          .then(response => response.data)
      }

      this.getLocationsByState = (state) => {
        return $http
          .get('./api/locations/' + state)
          .then(response => response.data)
      }
}
})();
