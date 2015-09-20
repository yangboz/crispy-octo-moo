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
        ////LinkedIn
        $rootScope.loginModal_li = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-login-li.html', {
            scope: $scope,
            backdropClickToClose: true,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-login-li.html initialization.");
            $rootScope.loginModal_li = modal;
            //Login Modal show();
            if (!window.localStorage["li_ls_crispy_octo_moo"]) {
                $rootScope.loginModal_li.show();
            }
        });
        ////Facebook
        $rootScope.loginModal_fb = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-login-fb.html', {
            scope: $scope,
            backdropClickToClose: false,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-login-fb.html initialization.");
            $rootScope.loginModal_fb = modal;
            //Login Modal show();
            if (!window.localStorage["fb_ls_crispy_octo_moo"]) {
                $rootScope.loginModal_fb.show();
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
            $rootScope.loginModal_fb.remove();
            $rootScope.loginModal_li.remove();
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
        ////Category values(single selection)
        $rootScope.incomeCategories = [];
        $rootScope.prefIncomeCategory = null;
        $rootScope.filingCategories = [];
        $rootScope.prefFilingCategory = null;
        $rootScope.childrenCategories = [];
        $rootScope.prefChildrenCategory = null;
        $rootScope.childrenKeywords = [];
        $rootScope.prefChildrenKeyword = null;
        $rootScope.mortgageInterests = [];
        $rootScope.prefMortgageInterest = null;
        $rootScope.EVCredits = [];
        $rootScope.prefEVCredit = null;
        ////test post.

///
    })
    .controller('LoginModalCtrl', function ($scope, $rootScope,ngFB ,$linkedIn, UserService,$log,
                                            FbUserService,LiUserService,$http) {
        $scope.fbLogin = function () {
            $rootScope.showLoading();
            ngFB.login({scope: 'public_profile,email, user_location, user_relationships, user_education_history, user_work_history, user_birthday, user_posts'}).then(
                function (response) {
                    if (response.status === 'connected') {
                        console.log('Facebook User login succeeded');
                        $rootScope.loginModal_fb.hide();
                        //
                        var access_token =  response.authResponse.accessToken;
                        $log.debug('Facebook login succeeded, got access token: ',access_token);
                        ///
                        ngFB.api({
                            path: '/me',
                            params: {fields: 'id,name'}
                        }).then(
                            function (user) {
                                $rootScope.user = user;
                                console.log("$rootScope.user:",$rootScope.user);
                                $rootScope.hideLoading();
                                //Synchronize the user info testing
                                //UserService.save($rootScope.user, function (response) {
                                //    $log.debug("UserService.save() success!", response);
                                //}, function (error) {
                                //    // failure handler
                                //    $log.error("UserService.save() failed:", JSON.stringify(error));
                                //});
                                //Sync the Facebook user profile.
                                FbUserService.save({'userId':user.id,'token':access_token}, function (response) {
                                    $log.debug("FbUserService.get() success!", response);
                                }, function (error) {
                                    // failure handler
                                    $log.error("FbUserService.get() failed:", JSON.stringify(error));
                                });
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
                });
            ///
            //ngFB.api('/me/permissions', function (response) {
            //    console.log(response);
            //} );
        };
        //@see: https://developer.linkedin.com/docs/js-sdk
        $scope.liLogin = function () {
            //$linkedIn.refresh();
            $rootScope.showLoading();
            //
            //@see: http://blog.ionic.io/oauth-ionic-ngcordova/
            //@see: https://github.com/nraboy/ng-cordova-oauth
            //$cordovaOauth.linkedin("77nayor82qqip3", "UJOUycxP5UgdD3da", ["r_basicprofile", "r_network", "r_emailaddress",
            //    "rw_company_admin", "w_share rw_nus"]).then(function(result) {
            //    console.log("Response LinkedIn Object -> " + JSON.stringify(result));
            //}, function(error) {
            //    console.log("Error -> " + error);
            //});
            $linkedIn.authorize();//No promise handler!
            //var linkedInScope = {};
            //IN.User.authorize(function OnLinkedInAuth($resp){
            //    console.log($resp);
            //}, linkedInScope);
            //console.log("linkedInScope:",linkedInScope);
            //Listen to Auth event to find oauth_token
            //@see: http://stackoverflow.com/questions/31553463/javascript-get-linkedin-access-token
            var oauth_token = "";
            IN.Event.on(IN, "auth", function OnLinkedInAuth() {
                for (var property in IN.ENV.auth) {
                    //output += property + ': ' + object[property]+'; ';
                    console.debug("IN.ENV.auth:" + property + ': ' + IN.ENV.auth[property]+'; ');
                }

                oauth_token = IN.ENV.auth.oauth_token;
                console.debug("oauth token:" + oauth_token);
            });
            $linkedIn.isAuthorized().then(function (resp) {
                console.log("$linkedIn.isAuthorized():",resp);    // $linkedIn.isAuthorized
                $rootScope.hideLoading();
                $rootScope.loginModal_li.hide();
                ///$linkedIn.profile(ids, field, params) // get user(s) profile(s).
                //@see:https://github.com/boketto/ngLinkedIn
                //@see:https://developer.linkedin.com/docs/fields
                var liUserProfile = null;
                //@see:https://spring.io/understanding/javascript-promises
                $linkedIn.profile('me',['id','first-name']).then(function (resp) {
                    liUserProfile = resp;
                    console.log("$linkedIn.profile:",liUserProfile);    // $linkedIn.profile
                    //Sync the Facebook user profile.
                    LiUserService.save({'userId':liUserProfile.id,'token':oauth_token}, function (response) {
                        $log.debug("LiUserService.get() success!", response);
                    }, function (error) {
                        // failure handler
                        $log.error("LiUserService.get() failed:", JSON.stringify(error));
                    });
                }, function (error) {
                    console.error('$linkedIn.profile() error: ', error);   // 'uh oh: something bad happened’
                });
            }, function (error) {
                console.error('$linkedIn.isAuthorized() error: ', error);   // 'uh oh: something bad happened’
                $rootScope.hideLoading();
                alert('LinkedIn authorized fail: ' + error);
            });
        }
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

    .controller('AccountSettingsCtrl', function ($scope, $rootScope,IncomeCategoryService,FilingCategoryService,
                                                 ChildrenCategoryService,EVCreditService,MortgageInterestService,
                                                 ChildrenKeywordsService,EITCCreditService,Enum,$ionicPopup) {
        //ng-model
        //@see: http://odetocode.com/blogs/scott/archive/2013/06/19/using-ngoptions-in-angularjs.aspx
        ///IncomeCategory
        console.log("Enum.relationshipStatus[0].value:",Enum.relationshipStatus[0].value);
        $rootScope.incomeCategories = IncomeCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        console.log("$rootScope.incomeCategories:",$rootScope.incomeCategories);
        $scope.setIncomeCategorySelected = function (value) {
            $rootScope.prefIncomeCategory = value;
            console.log("$rootScope.prefIncomeCategory:",$rootScope.prefIncomeCategory);
        };
        ///FilingCategory
        $rootScope.filingCategories = FilingCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        console.log("$rootScope.filingCategories:",$rootScope.filingCategories);
        $scope.setFilingCategorySelected = function (value) {
            $rootScope.prefFilingCategory = value;
            console.log("$rootScope.prefFilingCategory:",$rootScope.prefFilingCategory);
        };
        ///ChildrenCategory
        $rootScope.childrenCategories = ChildrenCategoryService.all();//Default setting(all).
        console.log("$rootScope.childrenCategories:",$rootScope.childrenCategories);
        $scope.setChildrenCategorySelected = function (value) {
            $rootScope.prefChildrenCategory = value;
            console.log("$rootScope.prefChildrenCategory:",$rootScope.prefChildrenCategory);
        };
        ///ChildrenKeywords
        $rootScope.childrenKeywords = ChildrenKeywordsService.all();//Default setting(all).
        console.log("$rootScope.childrenKeywords:",$rootScope.childrenKeywords);
        $scope.setChildrenKeywordSelected = function (value) {
            $rootScope.prefChildrenKeyword = value;
            console.log("$rootScope.prefChildrenKeyword:",$rootScope.prefChildrenKeyword);
        };
        ///MortgageInterest
        $rootScope.mortgageInterests = MortgageInterestService.all();//Default setting(all).
        console.log("$rootScope.mortgageInterests:",$rootScope.mortgageInterests);
        $scope.setMortgageInterestSelected = function (value) {
            $rootScope.prefMortgageInterest = value;
            console.log("$rootScope.prefMortgageInterest:",$rootScope.prefMortgageInterest);
        };
        ///EVCredit
        $rootScope.EVCredits = EVCreditService.all();//Default setting(all).
        console.log("$rootScope.EVCredits:",$rootScope.EVCredits);
        $scope.setEVCreditSelected = function (value) {
            $rootScope.prefEVCredit = value;
            console.log("$rootScope.prefEVCredit:",$rootScope.prefEVCredit);
        };
        ///EITCCreditService calculate
        $scope.EITCCreditCalculate = function(){
            var result = EITCCreditService.get($rootScope.userMore,$rootScope.prefChildrenCategory,$rootScope.prefIncomeCategory);
            $ionicPopup.alert({
                title: 'Congratulations!',
                content: result
            }).then(function(res) {
                console.log('EITCCreditService calculated result:',result);
            });
        }
    })
    .controller('AccountInvitesCtrl', function ($scope, $rootScope, $stateParams) {
        $scope.settings = {
            enableFriends: true
        };
    })

    .controller('DealsCtrl', function ($scope) {

    });
