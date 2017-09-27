'use strict';

(() => {
  angular
    .module('neverland.notifications')
    .controller('NotificationsController', NotificationsController);

    NotificationsController.$inject = [
      '$scope', '$log', 'accessService'
    ];

    function NotificationsController($scope, $log, accessService) {
			accessService.socket.onclose = function() {
				//periodically try to reconnect
			}
			accessService.socket.onmessage =(message) => {
        this.message = (JSON.parse(message.data));
        // this.message.username = this.message.username + 'lala';
        //if(this.message.username !== accessService.currentUser.firstName + ' ' + accessService.currentUser.lastName){
				this.messages.push(this.message);
				$scope.$apply();
			};

			this.messages = [];
			// this.sendMessage = (text) => {
			// 	$log.debug('trying to send a message: '+ accessService.currentUser.firstName)
			// 	if (text !== '') {
			// 		var message = {
			// 			'username' : accessService.currentUser.firstName + ' ' + accessService.currentUser.lastName,
			// 			'content' : text
			// 		};
				// 	$log.debug(message)
				// 	accessService.socket.send(JSON.stringify(message));
				// 	$log.debug('sent a message')
				// 	// $log.debug()
				// }
				// };
    }
})();
