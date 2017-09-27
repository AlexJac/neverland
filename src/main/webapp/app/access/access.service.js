'use strict';

(() => {
  angular
    .module('neverland.access')
    .service('accessService', AccessService);

  AccessService.$inject = ['bcrypt', '$http', '$log', '$location', '$state'];

  function AccessService(bcrypt, $http, $log, $location, $state) {
    this.currentUser;
    this.socket = new WebSocket('ws://localhost:8080/neverland/notifications')
    $log.debug('web socket opened for chat');

    this.register = (user) => {
      let salt = bcrypt.genSaltSync(4);
      let hash = bcrypt.hashSync(user.password, salt);
      user.password = hash;

      return $http
        .post('./api/user', user)
        .then(response => response.data)
        .then(user => {
          if (user.userId == null) {
            //  user already exists
            return null;
          } else {
            this.currentUser = user;
            $location.path('user/' + this.currentUser.userId);
            // this.socket = new WebSocket("ws://localhost:8080/fastbook/chat");
          }
        });
    };

    this.login = (credentials) => {
      $log.debug('Calling AccessService.login()');
      return $http
        .post('./api/user/login', credentials.username) // returns response
        .then(response => response.data) // t response, r user
        .then(user => {
          if (user.userId == null) {
            // user not found
            $log.debug('accessService.login-user not found')
            return null;
          } else {
            if (bcrypt.compareSync(credentials.password, user.password)) {
              $log.debug('User Authenticated');
              this.currentUser = user;
              delete this.currentUser.password;
              $log.debug(this.currentUser);
              credentials = undefined;
              $location.path('user/' + this.currentUser.userId);
              // this.socket = new WebSocket("ws://localhost:8080/fastbook/chat");

            } else {
              $log.debug('invalid username or password');
              this.currentuser = undefined;
            }
          }
        })
        .catch(error => $log.debug(JSON.stringify(error)));
    }

    this.logout = () => {
      this.currentUser = undefined;
      $state.go('register')
    }

    this.isLoggedOut = () => this.currentUser == undefined;

    this.isLoggedIn = () => this.currentUser !== undefined;


  }

})();
