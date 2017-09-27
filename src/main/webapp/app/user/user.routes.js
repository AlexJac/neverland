'use strict';

(() => {
  angular
    .module('neverland.user')
    .constant('userRoutes', {
      profile: {
        url: '/user/{userId}',
        templateUrl: 'app/user/profile.template.html',
        controller: 'ProfileController',
        controllerAs: '$profileCtrl',
        resolve: {
          user: ['accessService', function(accessService){
            return accessService.currentUser;
          }],
          currentTripsList: ['userService', 'accessService', function(userService, accessService){
            return userService.getCurrentTrips(accessService.currentUser.userId);
          }]
          // currentTrips: ['userService', '$stateParams', function(userService, $stateParams){
          //   return userService.getCurrentTrips($stateParams.id);
          // }],
          // completedTrips: ['userService', '$stateParams', function (userService, $stateParams) {
          //   return userService.getCompletedTrips($stateParams.id);
          // }],
          // canceledTrips: ['userService', '$stateParams', function(userService, $stateParams){
          //   return userService.getCanceledTrips($stateParams.id);
          // }]
        },
      data: {
        loggedIn: true
      }
    }
  })
})();
