'use strict';

(() => {
  angular
    .module('neverland.access')
    .constant('accessRoutes', {

        register: {
          url: '/',
          templateUrl: 'app/access/register.template.html',
          controller: 'RegisterController',
          controllerAs: '$register',
          data: {
            loggedIn: false
          }
        },

        login: {
          url: '/login',
          templateUrl: 'app/access/login.template.html',
          controller: 'LoginController',
          controllerAs: '$login',
          data: {
            loggedIn: false
          }
        }
      });
  })();
