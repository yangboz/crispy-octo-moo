angular.module('starter.controllers', [])

    .controller('MainCtrl', function ($scope, $rootScope, $ionicModal, $timeout, $ionicLoading, CacheService, Enum) {

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
            CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_LI);//for debugging
            CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_LI).then(function (data) {
                console.log(Enum.localStorageKeys.OAUTH_OBJ_LI, data);
                if (data == null) {
                    $rootScope.loginModal_li.show();
                } else {
                    $rootScope.oauth_obj_li = JSON.parse(data);
                    $rootScope.syncLiUserProfile();
                }
            });
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
            //@see: CacheService
            //CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_FB);//for debugging
            CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_FB).then(function (data) {
                console.log(Enum.localStorageKeys.OAUTH_OBJ_FB, data);
                if (data == null) {
                    $rootScope.loginModal_fb.show();
                } else {
                    $rootScope.oauth_obj_fb = JSON.parse(data);
                    $rootScope.syncFbUserProfile();
                }
            });
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
        $rootScope.fbUser = null;
        $rootScope.fbUserMore = null;
        $rootScope.fbUserPermissions = null;
        $rootScope.oauth_obj_fb = {};
        //LinkedIn
        $rootScope.oauth_obj_li = {};
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
        ///Currently only for Facebook
        $rootScope.getSnap415Token = function()
        {
            return {'provider':Enum.socialProviders.FACEBOOK,'id': $rootScope.fbUser.id, 'token': $rootScope.oauth_obj_fb.accessToken};
        }
        ////test post.

///
    })
    .controller('LoginModalCtrl', function ($scope, $rootScope, ngFB, $linkedIn, UserProfileService, $log,
                                            FbUserProfileService, LiUserProfileService, $http, CacheService, Enum) {
        $scope.fbLogin = function () {
            $rootScope.showLoading();
            ngFB.login({scope: 'public_profile,email, user_location, user_relationships, user_education_history, user_work_history, user_birthday, user_posts'}).then(
                function (response) {
                    if (response.status === 'connected') {
                        console.log('Facebook User login succeeded');
                        $rootScope.loginModal_fb.hide();
                        //TODO:find the access token expire time.
                        //Long term/short term conditions,@see: https://developers.facebook.com/docs/facebook-login/access-tokens
                        $rootScope.oauth_obj_fb = response.authResponse;
                        $log.debug('Facebook login succeeded, response: ', $rootScope.oauth_obj_fb);
                        //$log.debug('Facebook login succeeded, authResponse: ', response.authResponse);
                        //var access_token = response.authResponse.accessToken;
                        //$log.debug('Facebook login succeeded, got access token: ', access_token);
                        //Cache it.
                        CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_FB, JSON.stringify($rootScope.oauth_obj_fb), 1);//60 days?60 * 24 * 60 * 60
                        //
                        $rootScope.syncFbUserProfile();
                    } else {
                        alert('Facebook login failed');
                    }
                });
        };
        $rootScope.syncFbUserProfile = function () {
            ngFB.api({
                path: '/me',
                params: {fields: 'id,name'}
            }).then(
                function (user) {
                    $rootScope.fbUser = user;
                    console.log("$rootScope.fbUser:", $rootScope.fbUser);
                    $rootScope.hideLoading();
                    //Sync the Facebook with token then get user profile.
                    FbUserProfileService.save({'provider':Enum.socialProviders.FACEBOOK,'id': user.id, 'token': $rootScope.oauth_obj_fb.accessToken}, function (response) {
                        $log.debug("FbUserProfileService.get() success!", response);

                    }, function (error) {
                        // failure handler
                        $log.error("FbUserProfileService.get() failed:", JSON.stringify(error));
                    });
                },
                function (error) {
                    alert('Facebook error: ' + error);
                    $rootScope.hideLoading();
                });
        }
        //For testing.
        $scope.loadMoreFbInfo = function () {
            $rootScope.showLoading();
            ///
            ngFB.api({
                path: '/me/permissions',
                params: {}
            }).then(
                function (response) {
                    $rootScope.fbUserPermissions = response;
                    console.log("$rootScope.fbUserPermissions:", $rootScope.fbUserPermissions);
                    $rootScope.hideLoading();
                    //if can load more informations
                    $scope.loadMoreFbInfo();
                },
                function (error) {
                    alert('Facebook error: ' + error.error_description);
                    $rootScope.hideLoading();
                });
            //
            ngFB.api({
                path: '/me',
                params: {fields: 'id,name,email,relationship_status,work,birthday,location,education, posts, family'}
            }).then(
                function ($response) {
                    $rootScope.fbUserMore = $response;
                    console.log("$rootScope.fbUserMore:", $rootScope.fbUserMore);
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
            $linkedIn.isAuthorized().then(function (resp) {
                //
                $scope.updateOauthObj_li();//For debugging.
            }, function (error) {
                console.log('$linkedIn.authorize() now...');
                $linkedIn.authorize();//No promise handler!
                //Listen to Auth event to find oauth_token
                //@see: http://stackoverflow.com/questions/31553463/javascript-get-linkedin-access-token
                IN.Event.on(IN, "auth", function OnLinkedInAuth() {
                    //
                    $scope.updateOauthObj_li();
                });
            });
            //$linkedIn.api()
        }
        $scope.updateOauthObj_li = function(){
            //Dump the auth response object.
            //for (var property in IN.ENV.auth) {
            //    //output += property + ': ' + object[property]+'; ';
            //    console.debug("IN.ENV.auth:" + property + ': ' + IN.ENV.auth[property] + '; ');
            //}
            $rootScope.oauth_obj_li = IN.ENV.auth;
            //var oauth_token = IN.ENV.auth.oauth_token;
            //var member_id = IN.ENV.auth.member_id;
            //var oauth_expires_in = IN.ENV.auth.oauth_expires_in;//seconds
            //Cache it.
            CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_LI, JSON.stringify($rootScope.oauth_obj_li), $rootScope.oauth_obj_li.oauth_expires_in);
            //
            $rootScope.syncLiUserProfile();
        }
        $rootScope.syncLiUserProfile = function () {
            //Sync the LinkedIn token then get user profile.
            LiUserProfileService.save({
                'provider':Enum.socialProviders.LINKEDIN,
                'id': $rootScope.oauth_obj_li.member_id,
                'token': $rootScope.oauth_obj_li.oauth_token
                //'token': $rootScope.oauth_obj_li.anonymous_token
            }, function (response) {
                $log.debug("LiUserProfileService.get() success!", response);
                $rootScope.loginModal_li.hide();
            }, function (error) {
                // failure handler
                $log.error("LiUserProfileService.get() failed:", JSON.stringify(error));
                $rootScope.loginModal_li.hide();
            });
        }
        //For testing.
        $scope.loadMoreLiInfo = function () {
            $linkedIn.isAuthorized().then(function (resp) {
                console.log("$linkedIn.isAuthorized():", resp);    // $linkedIn.isAuthorized
                $rootScope.hideLoading();
                ///$linkedIn.profile(ids, field, params) // get user(s) profile(s).
                //@see:https://github.com/boketto/ngLinkedIn
                //@see:https://developer.linkedin.com/docs/fields
                var liUserProfile = null;
                //@see:https://spring.io/understanding/javascript-promises
                $linkedIn.profile('me', ['id', 'first-name']).then(function (resp) {
                    liUserProfile = resp;
                    console.log("$linkedIn.profile:", liUserProfile);    // $linkedIn.profile
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
    .controller('DashCtrl', function ($scope,$rootScope,$log,Enum) {

    })

    .controller('UpdatesCtrl', function ($scope,$rootScope,$log,Enum) {

    })

    .controller('AccountsCtrl', function ($scope,$rootScope,$log,Enum,UserMeService) {
        //Synchronize the user info testing
        //UserProfileService.save($rootScope.user, function (response) {
        //    $log.debug("UserProfileService.save() success!", response);
        //}, function (error) {
        //    // failure handler
        //    $log.error("UserProfileService.save() failed:", JSON.stringify(error));
        //});
        $scope.me = {};
        $scope.loadUserMe = function(){
            UserMeService.save($rootScope.getSnap415Token(), function (response) {
                $log.debug("UserMeService.save() success!", response);
                $scope.me = response;
            }, function (error) {
                // failure handler
                $log.error("UserMeService.save() failed:", JSON.stringify(error));
            });
        }
        //Default behaviours:
        $scope.loadUserMe();
    })

    .controller('AccountSettingsCtrl', function ($scope, $rootScope, IncomeCategoryService, FilingCategoryService,
                                                 ChildrenCategoryService, EVCreditService, MortgageInterestService,
                                                 ChildrenKeywordsService, EITCCreditService, Enum, $ionicPopup) {
        //ng-model
        //@see: http://odetocode.com/blogs/scott/archive/2013/06/19/using-ngoptions-in-angularjs.aspx
        ///IncomeCategory
        console.log("Enum.relationshipStatus[0].value:", Enum.relationshipStatus[0].value);
        $rootScope.incomeCategories = IncomeCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        console.log("$rootScope.incomeCategories:", $rootScope.incomeCategories);
        $scope.setIncomeCategorySelected = function (value) {
            $rootScope.prefIncomeCategory = value;
            console.log("$rootScope.prefIncomeCategory:", $rootScope.prefIncomeCategory);
        };
        ///FilingCategory
        $rootScope.filingCategories = FilingCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        console.log("$rootScope.filingCategories:", $rootScope.filingCategories);
        $scope.setFilingCategorySelected = function (value) {
            $rootScope.prefFilingCategory = value;
            console.log("$rootScope.prefFilingCategory:", $rootScope.prefFilingCategory);
        };
        ///ChildrenCategory
        $rootScope.childrenCategories = ChildrenCategoryService.all();//Default setting(all).
        console.log("$rootScope.childrenCategories:", $rootScope.childrenCategories);
        $scope.setChildrenCategorySelected = function (value) {
            $rootScope.prefChildrenCategory = value;
            console.log("$rootScope.prefChildrenCategory:", $rootScope.prefChildrenCategory);
        };
        ///ChildrenKeywords
        $rootScope.childrenKeywords = ChildrenKeywordsService.all();//Default setting(all).
        console.log("$rootScope.childrenKeywords:", $rootScope.childrenKeywords);
        $scope.setChildrenKeywordSelected = function (value) {
            $rootScope.prefChildrenKeyword = value;
            console.log("$rootScope.prefChildrenKeyword:", $rootScope.prefChildrenKeyword);
        };
        ///MortgageInterest
        $rootScope.mortgageInterests = MortgageInterestService.all();//Default setting(all).
        console.log("$rootScope.mortgageInterests:", $rootScope.mortgageInterests);
        $scope.setMortgageInterestSelected = function (value) {
            $rootScope.prefMortgageInterest = value;
            console.log("$rootScope.prefMortgageInterest:", $rootScope.prefMortgageInterest);
        };
        ///EVCredit
        $rootScope.EVCredits = EVCreditService.all();//Default setting(all).
        console.log("$rootScope.EVCredits:", $rootScope.EVCredits);
        $scope.setEVCreditSelected = function (value) {
            $rootScope.prefEVCredit = value;
            console.log("$rootScope.prefEVCredit:", $rootScope.prefEVCredit);
        };
        ///EITCCreditService calculate
        $scope.EITCCreditCalculate = function () {
            var result = EITCCreditService.get($rootScope.userMore, $rootScope.prefChildrenCategory, $rootScope.prefIncomeCategory);
            $ionicPopup.alert({
                title: 'Congratulations!',
                content: result
            }).then(function (res) {
                console.log('EITCCreditService calculated result:', result);
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
