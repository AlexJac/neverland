'use strict';

(() => {
  angular
    .module('neverland.search')
    .config(config)

    config.$inject = ['searchRoutes', '$stateProvider']

    function config(searchRoutes, $stateProvider) {
      Object.keys(searchRoutes) // JS built in function
        .forEach(key => {
          $stateProvider
            .state(key, searchRoutes[key]);  //accesses each state object given the key and the object
        })
    }

})();
