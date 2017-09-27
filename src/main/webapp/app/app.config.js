'use strict';

(() => {
  angular
  .module('neverland')
  .config(config);

  config.$inject = [
    '$urlRouterProvider',
    '$locationProvider',
    '$mdThemingProvider',
    '$mdIconProvider'
  ];

  function config(
    $urlRouterProvider,
    $locationProvider,
    $mdThemingProvider,
    $mdIconProvider
  ) {
    $urlRouterProvider.otherwise('/');
    $locationProvider.html5Mode(true);

    $mdThemingProvider.theme('default')
      .primaryPalette('grey', {
        'default': '800'
      })
      .accentPalette('green', {
        'default': 'A400'
      })
      .warnPalette('orange', {
        'default': '600'
      })
      //.dark();

    $mdIconProvider.fontSet('md', 'material-icons');
  }
})();
