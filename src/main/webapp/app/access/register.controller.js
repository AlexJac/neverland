'use strict';

(() => {
  angular
    .module('neverland.access')
    .controller('RegisterController', RegisterController);

  RegisterController.$inject = ['$scope', 'accessService', '$state', '$log'];

  function RegisterController($scope, accessService, $state, $log) {

    $log.debug('Creating $register');

    this.register = () => {
      this.errorMessage = null;
      accessService
        .register(this.user)
        .then(result => {
          // user already exists
          this.errorMessage = 'User already exists!';
        })
    }

    this.goToLogin = () => {
      $log.debug('Clicked goToLogin');
      $state.go('login');
    }
  }

})();
