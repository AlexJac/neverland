'use strict';

(() => {
  angular
    .module('neverland.user')
    .config(config)

    config.$inject = ['userRoutes', '$stateProvider']

    function config(userRoutes, $stateProvider) {
      Object.keys(userRoutes) // JS built in function
        .forEach(key => {
          $stateProvider
            .state(key, userRoutes[key]);  //accesses each state object given the key and the object
        })
    }

})();
