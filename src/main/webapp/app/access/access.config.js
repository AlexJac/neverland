'use strict';

(() => {
  angular
    .module('neverland.access')
    .config(config)

    config.$inject = ['accessRoutes', '$stateProvider']

    function config(accessRoutes, $stateProvider) {
      Object.keys(accessRoutes) // JS built in function
        .forEach(key => {
          $stateProvider
            .state(key, accessRoutes[key]);  //accesses each state object given the key and the object
        })
    }

})();
