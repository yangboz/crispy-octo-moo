angular.module('starter.controllers', [])

    .controller('MainCtrl', function ($scope, $rootScope, $ionicModal, $timeout, $ionicLoading) {

        ///Loading
        $rootScope.showLoading = function () {
            $ionicLoading.show({
                //template: 'Loading...'
                template: "<img id='spinner' src='img/spinner.gif'>"
            });
        };
        $rootScope.hideLoading = function () {
            $ionicLoading.hide();
        };
        ///LoginModal
        $rootScope.loginModal = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-login.html', {
            scope: $scope,
            backdropClickToClose: false,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-login.html init!!!");
            $rootScope.loginModal = modal;
            //Login Modal show();
            if (!window.localStorage["fb_ls_crispy_octo_moo"]) {
                $rootScope.loginModal.show();
            }
        });
        ///Modal related clean up.
        $rootScope.$on("$stateChangeStart", function () {

            //ShowLoading
            $rootScope.showLoading();
        });
        $rootScope.$on("$stateChangeSuccess", function () {
            //HideLoading
            $rootScope.hideLoading();
        });
        //Cleanup the modal when we're done with it!
        $scope.$on('$destroy', function () {
            $rootScope.loginModal.remove();
        });
        // Execute action on hide modal
        $scope.$on('modal.hidden', function () {
            // Execute action
        });
        // Execute action on remove modal
        $scope.$on('modal.removed', function () {
            // Execute action
        });
        ///Default behaviors

///
    })
    .controller('LoginModalCtrl', function ($scope, $rootScope,ngFB) {
        $scope.fbLogin = function () {
            ngFB.login({scope: 'public_profile,email, user_location, user_relationships, user_education_history, user_work_history, user_birthday, user_posts'}).then(
                function (response) {
                    if (response.status === 'connected') {
                        console.log('Facebook login succeeded');
                        $rootScope.loginModal.hide();
                    } else {
                        alert('Facebook login failed');
                    }
                });
        };
    })
    .controller('DashCtrl', function ($scope, $rootScope) {

    })

    .controller('UpdatesCtrl', function ($scope) {
    })

    .controller('AccountsCtrl', function ($scope, Chats) {
        // With the new view caching in Ionic, Controllers are only called
        // when they are recreated or on app start, instead of every page change.
        // To listen for when this page is active (for example, to refresh data),
        // listen for the $ionicView.enter event:
        //
        //$scope.$on('$ionicView.enter', function(e) {
        //});

        $scope.chats = Chats.all();
        $scope.remove = function (chat) {
            Chats.remove(chat);
        };
    })

    .controller('AccountSettingsCtrl', function ($scope, $stateParams, Chats) {
        $scope.chat = Chats.get($stateParams.chatId);
    })

    .controller('DealsCtrl', function ($scope) {
        $scope.settings = {
            enableFriends: true
        };
    });
