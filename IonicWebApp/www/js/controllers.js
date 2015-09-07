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
        ///FB User profile vars
        $rootScope.user = null;
        $rootScope.userMore = null;
        $rootScope.userPermissions = null;
        ////test post.

///
    })
    .controller('LoginModalCtrl', function ($scope, $rootScope,ngFB) {
        $scope.fbLogin = function () {
            $rootScope.showLoading();
            ngFB.login({scope: 'public_profile,email, user_location, user_relationships, user_education_history, user_work_history, user_birthday, user_posts'}).then(
                function (response) {
                    if (response.status === 'connected') {
                        console.log('Facebook User login succeeded');
                        $rootScope.loginModal.hide();
                        ///
                        ngFB.api({
                            path: '/me',
                            params: {fields: 'id,name'}
                        }).then(
                            function (user) {
                                $rootScope.user = user;
                                console.log("$rootScope.user:",$rootScope.user);
                                $rootScope.hideLoading();
                            },
                            function (error) {
                                alert('Facebook error: ' + error.error_description);
                                $rootScope.hideLoading();
                            });
                        ///
                        ngFB.api({
                            path: '/me/permissions',
                            params: {}
                        }).then(
                            function (response) {
                                $rootScope.userPermissions = response;
                                console.log("$rootScope.userPermissions:",$rootScope.userPermissions);
                                $rootScope.hideLoading();
                                //if can load more informations
                                $scope.loadMoreFbInfo();
                            },
                            function (error) {
                                alert('Facebook error: ' + error.error_description);
                                $rootScope.hideLoading();
                            });
                    } else {
                        alert('Facebook login failed');
                    }
                });
        };
        $scope.loadMoreFbInfo = function() {
            $rootScope.showLoading();
            ngFB.api({
                path: '/me',
                params: {fields: 'id,name,email,relationship_status,work,birthday,location,education, posts, family'}
            }).then(
                function ($response) {
                    $rootScope.userMore = $response;
                    console.log("$rootScope.userMore:",$rootScope.userMore);
                    $rootScope.hideLoading();
                    //Accept the more fbInfo
                },
                function (error) {
                    alert('Facebook error: ' + error.error_description);
                    $rootScope.hideLoading();
                });
            ///
            //ngFB.api('/me/permissions', function (response) {
            //    console.log(response);
            //} );
        };
    })
    .controller('DashCtrl', function ($scope, $rootScope) {

    })

    .controller('UpdatesCtrl', function ($scope) {
    })

    .controller('AccountsCtrl', function ($scope) {
        // With the new view caching in Ionic, Controllers are only called
        // when they are recreated or on app start, instead of every page change.
        // To listen for when this page is active (for example, to refresh data),
        // listen for the $ionicView.enter event:
        //
        //$scope.$on('$ionicView.enter', function(e) {
        //});

        //$scope.chats = Chats.all();
    })

    .controller('AccountSettingsCtrl', function ($scope, $stateParams) {
    })

    .controller('DealsCtrl', function ($scope) {
        $scope.settings = {
            enableFriends: true
        };
    });
