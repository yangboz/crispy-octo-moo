angular.module('starter.controllers', [])

    .controller('MainCtrl', function ($scope, $rootScope, $ionicModal, $timeout, $ionicLoading, CacheService, Enum,$log
        ,OverviewService,UserTaxEventService,$interval,UserMeService) {
        //GA begin
        if (typeof analytics !== 'undefined') {
            analytics.trackView("MainCtrl");
        }//GA end
        ///Loading
        $rootScope.showLoading = function () {
            $ionicLoading.show({
                //template: 'Loading...'
                template: "<images id='spinner' src='images/spinner.gif'>"
            });
        };
        $rootScope.hideLoading = function () {
            $ionicLoading.hide();
        };
        ///LoginModal
        ////Social
        $rootScope.loginModal_social = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-login-social.html', {
            scope: $scope,
            backdropClickToClose: true,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-login-social.html initialization.");
            $rootScope.loginModal_social = modal;
            //Login Modal show();
            CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL);//Always show login modal,cuz some of invalid access token issue.
            CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL).then(function (data) {
                console.log(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL, data);
                if (data == null) {
                    $rootScope.loginModal_social.show();
                } else {
                    $rootScope.oauth_obj_social = JSON.parse(data);
                    $rootScope.syncFbUserProfile();//Notice:currently Facebook as default.
                }
            });
        });
        ////LinkedIn
        //$rootScope.loginModal_li = undefined;
        //$ionicModal.fromTemplateUrl('templates/modal-login-li.html', {
        //    scope: $scope,
        //    backdropClickToClose: true,
        //    animation: 'slide-in-up'
        //}).then(function (modal) {
        //    console.log("modal-login-li.html initialization.");
        //    $rootScope.loginModal_li = modal;
        //    //Login Modal show();
        //    //CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_LI);//for debugging
        //    CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_LI).then(function (data) {
        //        console.log(Enum.localStorageKeys.OAUTH_OBJ_LI, data);
        //        if (data == null) {
        //            $rootScope.loginModal_li.show();
        //        } else {
        //            $rootScope.oauth_obj_li = JSON.parse(data);
        //            $rootScope.syncLiUserProfile();
        //        }
        //    });
        //});
        //////Facebook
        //$rootScope.loginModal_fb = undefined;
        //$ionicModal.fromTemplateUrl('templates/modal-login-fb.html', {
        //    scope: $scope,
        //    backdropClickToClose: true,
        //    animation: 'slide-in-up'
        //}).then(function (modal) {
        //    console.log("modal-login-fb.html initialization.");
        //    $rootScope.loginModal_fb = modal;
        //    //Login Modal show();
        //    //@see: CacheService
        //    CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_FB);//for debugging
        //    CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_FB).then(function (data) {
        //        console.log(Enum.localStorageKeys.OAUTH_OBJ_FB, data);
        //        if (data == null) {
        //            $rootScope.loginModal_fb.show();
        //        } else {
        //            $rootScope.oauth_obj_fb = JSON.parse(data);
        //            $rootScope.syncFbUserProfile();
        //        }
        //    });
        //});
        ////DealDetail
        $rootScope.detailModal_deal = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-detail-deal.html', {
            scope: $scope,
            backdropClickToClose: true,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-detail-deal.html initialization.");
            $rootScope.detailModal_deal = modal;
            //Login Modal show();
        });
        ////DealDetail
        $rootScope.detailModal_me = undefined;
        $ionicModal.fromTemplateUrl('templates/modal-detail-me.html', {
            scope: $scope,
            backdropClickToClose: true,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-detail-me.html initialization.");
            $rootScope.detailModal_me = modal;
            //Login Modal show();
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
            $rootScope.loginModal_social.remove();
            //$rootScope.loginModal_fb.remove();
            //$rootScope.loginModal_li.remove();
            $rootScope,detailModal_deal.remove();
            $rootScope,detailModal_me.remove();
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
        ///Currently social provider info.
        $rootScope.isNonFacebookActive = false;//LinkedIn or others provider active.
        $rootScope._getProvider = function(){
            return $rootScope.isNonFacebookActive?Enum.socialProviders.LINKEDIN:Enum.socialProviders.FACEBOOK;
        }
        $rootScope._getProviderId = function(){
            return $rootScope.isNonFacebookActive?$rootScope.oauth_obj_li.member_id:$rootScope.fbUser.id;
        }
        $rootScope._getProviderToken = function(){
            return $rootScope.isNonFacebookActive?$rootScope.oauth_obj_li.oauth_token:$rootScope.oauth_obj_fb.accessToken;
        }
        $rootScope.getSnap415Token = function ($provider,$id,$token) {
            //return {
            //    'provider': Enum.socialProviders.FACEBOOK,
            //    'id': $rootScope.fbUser.id,
            //    'token': $rootScope.oauth_obj_fb.accessToken
            //};
            return {
                'provider': $rootScope._getProvider(),
                'id': $rootScope._getProviderId(),
                'token': $rootScope._getProviderToken()
            };
        }
        ////RootScope functions.
        //load overview items.
        $rootScope.loadOverviews = function(){
            OverviewService.get({}, function (response) {
                $log.debug("OverviewService.get() success!", response);
                $scope.overviews = response.data;
            }, function (error) {
                // failure handler
                $log.error("OverviewService.get() failed:", JSON.stringify(error));
            });
        }
        $rootScope.loadTaxEvents = function(){
            UserTaxEventService.save($rootScope.getSnap415Token(), function (response) {
                $log.debug("UserTaxEventService.get() success!", response);
                $scope.taxEvents = response.taxEvents;
            }, function (error) {
                // failure handler
                $log.error("UserTaxEventService.get() failed:", JSON.stringify(error));
            });
            //
            //$scope.timer = $interval ( function ( )
            //{
            //    $rootScope.loadTaxEvents();
            //} , 1*1*200*1000 );//Every 20 s
        }
        $rootScope.me = {};
        $rootScope.loadUserMe = function () {
            UserMeService.save($rootScope.getSnap415Token(), function (response) {
                $log.debug("UserMeService.save() success!", response);
                $rootScope.me = response;
            }, function (error) {
                // failure handler
                $log.error("UserMeService.save() failed:", JSON.stringify(error));
            });
        }
    })
    .controller('LoginModalCtrl', function ($scope, $rootScope, ngFB, $linkedIn, UserProfileService, $log,
                                            FbUserProfileService, LiUserProfileService, $http, CacheService, Enum,$ionicLoading) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("LoginModalCtrl");
        }//GA end
        //!!!FacebookLogin switch here!!!
        $scope.fbLogin = function(){
          if(ionic.Platform.isWebView())
          {
            $scope.fbLogin_mobile();
          }else{
            $scope.fbLogin_web();
          }
        }
        //FacebookLogin_WebApp
        $scope.fbLogin_web = function () {
            $rootScope.showLoading();
            ngFB.login({scope: 'public_profile,email, user_location, user_relationships, user_education_history, user_work_history, user_birthday, user_posts'}).then(
                function (response) {
                    if (response.status === 'connected') {
                        console.log('Facebook User login succeeded');
                        $rootScope.loginModal_social.hide();
                        //TODO:find the access token expire time.
                        //Long term/short term conditions,@see: https://developers.facebook.com/docs/facebook-login/access-tokens
                        $rootScope.oauth_obj_fb = response.authResponse;
                        $log.debug('Facebook login succeeded, response: ', $rootScope.oauth_obj_fb);
                        //$log.debug('Facebook login succeeded, authResponse: ', response.authResponse);
                        //var access_token = response.authResponse.accessToken;
                        //$log.debug('Facebook login succeeded, got access token: ', access_token);
                        //Cache it.@see:https://developers.facebook.com/tools/debug/accesstoken
                        CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL, JSON.stringify($rootScope.oauth_obj_fb), 1 * 60 * 60);//1443168000 (in about an hour)
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
                    FbUserProfileService.save({
                        'provider': Enum.socialProviders.FACEBOOK,
                        'id': user.id,
                        'token': $rootScope.oauth_obj_fb.accessToken
                    }, function (response) {
                        $log.debug("FbUserProfileService.get() success!", response);
                        //Default load overviews.
                        $rootScope.loadOverviews();
                        $rootScope.loadTaxEvents();
                        $rootScope.loadUserMe();
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
        $scope.updateOauthObj_li = function () {
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
            CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL, JSON.stringify($rootScope.oauth_obj_li), $rootScope.oauth_obj_li.oauth_expires_in);
            //
            $rootScope.loginModal_li.hide();
            //
            $rootScope.syncLiUserProfile();
        }
        $rootScope.syncLiUserProfile = function () {
            //Sync the LinkedIn token then get user profile.
            LiUserProfileService.save({
                'provider': Enum.socialProviders.LINKEDIN,
                'id': $rootScope.oauth_obj_li.member_id,
                'token': $rootScope.oauth_obj_li.oauth_token
                //'token': $rootScope.oauth_obj_li.anonymous_token
            }, function (response) {
                $log.debug("LiUserProfileService.get() success!", response);
            }, function (error) {
                // failure handler
                $log.error("LiUserProfileService.get() failed:", JSON.stringify(error));
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
    //FacebookLogin_mobile
    /////@see: https://ionicthemes.com/tutorials/about/native-facebook-login-with-ionic-framework
    // This is the success callback from the login method
    var fbLoginSuccess = function(response) {
      if (!response.authResponse){
        fbLoginError("Cannot find the authResponse");
        return;
      }

      var authResponse = response.authResponse;

      getFacebookProfileInfo(authResponse)
        .then(function(profileInfo) {
          // For the purpose of this example I will store user data on local storage
          //UserService.setUser({
          //  authResponse: authResponse,
          //  userID: profileInfo.id,
          //  name: profileInfo.name,
          //  email: profileInfo.email,
          //  picture : "http://graph.facebook.com/" + authResponse.userID + "/picture?type=large"
          //});
          $ionicLoading.hide();
          //$state.go('app.home');
          console.log("V profileInfo:",profileInfo);
        }, function(fail){
          // Fail get profile info
          console.log('profile info fail', fail);
        });
    };

    // This is the fail callback from the login method
    var fbLoginError = function(error){
      console.log('fbLoginError', error);
      $ionicLoading.hide();
    };

    // This method is to get the user profile info from the facebook api
    var getFacebookProfileInfo = function (authResponse) {
      var info = $q.defer();

      facebookConnectPlugin.api('/me?fields=email,name&access_token=' + authResponse.accessToken, null,
        function (response) {
          console.log(response);
          info.resolve(response);
        },
        function (response) {
          console.log(response);
          info.reject(response);
        }
      );
      return info.promise;
    };

    //This method is executed when the user press the "Login with facebook" button
    $scope.fbLogin_mobile = function() {
      facebookConnectPlugin.getLoginStatus(function(success){
        if(success.status === 'connected'){
          // The user is logged in and has authenticated your app, and response.authResponse supplies
          // the user's ID, a valid access token, a signed request, and the time the access token
          // and signed request each expire
          console.log('getLoginStatus', success.status);

          // Check if we have our user saved
          //var user = UserService.getUser('facebook');

        //  if(!user.userID){
        //    getFacebookProfileInfo(success.authResponse)
        //      .then(function(profileInfo) {
        //        console.log("profileInfo:",profileInfo);
        //        // For the purpose of this example I will store user data on local storage
        //        //UserService.setUser({
        //        //  authResponse: success.authResponse,
        //        //  userID: profileInfo.id,
        //        //  name: profileInfo.name,
        //        //  email: profileInfo.email,
        //        //  picture : "http://graph.facebook.com/" + success.authResponse.userID + "/picture?type=large"
        //        //});
        //        //
        //        //$state.go('app.home');
        //      }, function(fail){
        //        // Fail get profile info
        //        console.log('profile info fail', fail);
        //      });
        //  }else{
        //    //$state.go('app.home');
        //    console.log("No user.userID!");
        //  }
        } else {
          // If (success.status === 'not_authorized') the user is logged in to Facebook,
          // but has not authenticated your app
          // Else the person is not logged into Facebook,
          // so we're not sure if they are logged into this app or not.

          console.log('getLoginStatus', success.status);

          $ionicLoading.show({
            template: 'Logging in...'
          });

          // Ask the permissions you need. You can learn more about
          // FB permissions here: https://developers.facebook.com/docs/facebook-login/permissions/v2.4
          facebookConnectPlugin.login(['email', 'public_profile'], fbLoginSuccess, fbLoginError);
        }
      });
    };
    })
    .controller('DashCtrl', function ($scope, $rootScope, $log,$sce) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("DashCtrl");
        }//GA end
        //
        $scope.trustAsHtml = function(rawHtml)
        {
            return $sce.trustAsHtml(rawHtml);
        }

    })

    .controller('UpdatesCtrl', function ($scope, $rootScope, $log,TaxEventService,$sce) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("UpdatesCtrl");
        }//GA end
        //Default behaviors:

        //
        $scope.trustAsHtml = function(rawHtml)
        {
            return $sce.trustAsHtml(rawHtml);
        }

    })

    .controller('AccountsCtrl', function ($scope, $rootScope, $log, Enum,FilingCategoryService) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("AccountsCtrl");
        }//GA end
        //Synchronize the user info testing
        //UserProfileService.save($rootScope.user, function (response) {
        //    $log.debug("UserProfileService.save() success!", response);
        //}, function (error) {
        //    // failure handler
        //    $log.error("UserProfileService.save() failed:", JSON.stringify(error));
        //});
        //
        //popup detail
        $scope.detail = function(){

            FilingCategoryService.get({}, function (response) {
                $log.debug("FilingCategoryService.get() success!", response);
                $rootScope.filingCategories = response.data;
                console.log("$rootScope.filingCategories:", $rootScope.filingCategories);
                $rootScope.detailModal_me.show();
            }, function (error) {
                // failure handler
                $log.error("FilingCategoryService.get() failed:", JSON.stringify(error));
            });
        }
    })

    .controller('MeModalCtrl', function ($scope, $rootScope, $log, Enum, UserProfileService,$sce) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("MeModalCtrl");
        }//GA end
        $scope.profile = {income:0,children:0,filingCategory:'',iconUrl:''};
        //Submit profile inputs.
        $scope.save = function(){
            $log.info("preSaved me profile:"+",income:"+$scope.profile.income+",children:"+$scope.profile.children
                +",filingCategory(label,group):"+$scope.profile.filingCategory.label+","+$scope.profile.filingCategory.group);
            //Update user profile service.
            $rootScope.me.rwIncome = $scope.profile.income;
            $rootScope.me.rwTaxFilingStatus = $scope.profile.filingCategory.label;
            $rootScope.me.rwNumberOfChildren = $scope.profile.children;
            var rawProfileIconUrl = "http://graph.facebook.com/"+$rootScope.me.fbUserProfile.id+"/picture?width=270&height=270";
            $log.info("rawProfileIconUrl:"+rawProfileIconUrl);
            $scope.profile.iconUrl = $sce.trustAsResourceUrl(rawProfileIconUrl);
            //
            UserProfileService.update({Id:$rootScope.me.id},$rootScope.me, function (response) {
                $log.debug("UserProfileService.update() success!", response);
                $rootScope.me = response;
                $rootScope.detailModal_me.hide();
                //Update the taxEvents.
                $rootScope.loadTaxEvents();
            }, function (error) {
                // failure handler
                $log.error("UserProfileService.update() failed:", JSON.stringify(error));
            });
        }
    })

    .controller('AccountSettingsCtrl', function ($scope, $rootScope, IncomeCategoryService, FilingCategoryService,
                                                 ChildrenCategoryService, EVCreditService, MortgageInterestService,
                                                 ChildrenKeywordsService, EITCCreditService, Enum, $ionicPopup, $log) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("AccountSettingsCtrl");
        }//GA end
        //ng-model
        //@see: http://odetocode.com/blogs/scott/archive/2013/06/19/using-ngoptions-in-angularjs.aspx
        ///IncomeCategory
        //console.log("Enum.relationshipStatus[0].value:", Enum.relationshipStatus[0].value);
        //$rootScope.incomeCategories = IncomeCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        $rootScope.incomeCategories = [];
        console.log("$rootScope.incomeCategories:", $rootScope.incomeCategories);
        $scope.setIncomeCategorySelected = function (value) {
            $rootScope.prefIncomeCategory = value;
            console.log("$rootScope.prefIncomeCategory:", $rootScope.prefIncomeCategory);
        };
        IncomeCategoryService.get({}, function (response) {
            $log.debug("IncomeCategoryService.get() success!", response);
            $rootScope.incomeCategories = response.data;
            console.log("$rootScope.incomeCategories:", $rootScope.incomeCategories);
        }, function (error) {
            // failure handler
            $log.error("IncomeCategoryService.get() failed:", JSON.stringify(error));
        });
        ///FilingCategory
        //$rootScope.filingCategories = FilingCategoryService.get(Enum.relationshipStatus[0].value);//Default setting(Married).
        $rootScope.filingCategories = [];
        //console.log("$rootScope.filingCategories:", $rootScope.filingCategories);
        $scope.setFilingCategorySelected = function (value) {
            $rootScope.prefFilingCategory = value;
            console.log("$rootScope.prefFilingCategory:", $rootScope.prefFilingCategory);
        };
        FilingCategoryService.get({}, function (response) {
            $log.debug("FilingCategoryService.get() success!", response);
            $rootScope.filingCategories = response.data;
            console.log("$rootScope.filingCategories:", $rootScope.filingCategories);
        }, function (error) {
            // failure handler
            $log.error("FilingCategoryService.get() failed:", JSON.stringify(error));
        });
        ///ChildrenCategory
        //$rootScope.childrenCategories = ChildrenCategoryService.all();//Default setting(all).
        $rootScope.childrenCategories = [];
        //console.log("$rootScope.childrenCategories:", $rootScope.childrenCategories);
        $scope.setChildrenCategorySelected = function (value) {
            $rootScope.prefChildrenCategory = value;
            console.log("$rootScope.prefChildrenCategory:", $rootScope.prefChildrenCategory);
        };
        ChildrenCategoryService.get({}, function (response) {
            $log.debug("ChildrenCategoryService.get() success!", response);
            $rootScope.childrenCategories = response.data;
            console.log("$rootScope.childrenCategories:", $rootScope.childrenCategories);
        }, function (error) {
            // failure handler
            $log.error("ChildrenCategoryService.get() failed:", JSON.stringify(error));
        });
        ///ChildrenKeywords
        //$rootScope.childrenKeywords = ChildrenKeywordsService.all();//Default setting(all).
        $rootScope.childrenKeywords = [];
        ChildrenKeywordsService.get({}, function (response) {
            $log.debug("ChildrenKeywordsService.get() success!", response);
            $rootScope.childrenKeywords = response.data;
            console.log("$rootScope.childrenKeywords:", $rootScope.EVCredits);
        }, function (error) {
            // failure handler
            $log.error("ChildrenKeywordsService.get() failed:", JSON.stringify(error));
        });
        $scope.setChildrenKeywordSelected = function (value) {
            $rootScope.prefChildrenKeyword = value;
            console.log("$rootScope.prefChildrenKeyword:", $rootScope.prefChildrenKeyword);
        };
        ///MortgageInterest
        //$rootScope.mortgageInterests = MortgageInterestService.all();//Default setting(all).
        $rootScope.mortgageInterests = [];
        //console.log("$rootScope.mortgageInterests:", $rootScope.mortgageInterests);
        $scope.setMortgageInterestSelected = function (value) {
            $rootScope.prefMortgageInterest = value;
            console.log("$rootScope.prefMortgageInterest:", $rootScope.prefMortgageInterest);
        };
        MortgageInterestService.get({}, function (response) {
            $log.debug("MortgageInterestService.get() success!", response);
            $rootScope.mortgageInterests = response.data;
            console.log("$rootScope.mortgageInterests:", $rootScope.mortgageInterests);
        }, function (error) {
            // failure handler
            $log.error("MortgageInterestService.get() failed:", JSON.stringify(error));
        });
        ///EVCredit
        //$rootScope.EVCredits = EVCreditService.all();//Default setting(all).
        $rootScope.EVCredits = [];
        EVCreditService.get({}, function (response) {
            $log.debug("EVCreditService.get() success!", response);
            $rootScope.EVCredits = response.data;
            console.log("$rootScope.EVCredits:", $rootScope.EVCredits);
        }, function (error) {
            // failure handler
            $log.error("EVCreditService.get() failed:", JSON.stringify(error));
        });

        $scope.setEVCreditSelected = function (value) {
            $rootScope.prefEVCredit = value;
            console.log("$rootScope.prefEVCredit:", $rootScope.prefEVCredit);
        };
        ///EITCCreditService calculate
        $scope.EITCCreditCalculate = function () {
            //var result = EITCCreditService.get($rootScope.userMore, $rootScope.prefChildrenCategory, $rootScope.prefIncomeCategory);
            $scope.result = "";
            EITCCreditService.save({
                "relationshipStatus": $rootScope.me.simplyRelationshipStatus,
                "numberOfChildren": $rootScope.me.rwNumberOfChildren,
                "income": $rootScope.me.rwIncome
            }, function (response) {
                $log.debug("EITCCreditService.save() success!", response);
                $scope.result = response.data;
                //
                $ionicPopup.alert({
                    title: 'Congratulations!',
                    content: $scope.result
                }).then(function (res) {
                    console.log('EITCCreditService calculated result:', $scope.result);
                });
            }, function (error) {
                // failure handler
                $log.error("EITCCreditService.save() failed:", JSON.stringify(error));
            });

        }
    })
    .controller('AccountInvitesCtrl', function ($scope, $rootScope, $stateParams) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("AccountInvitesCtrl");
        }//GA end
        $scope.settings = {
            enableFriends: true
        };
    })

    .controller('DealsCtrl', function ($scope,$rootScope,$log,DealService,CategoryDealService,$sce) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("DealsCtrl");
        }//GA end
        //load deal items.
        //DealService.get({}, function (response) {
        //    $log.debug("DealService.get() success!", response);
        //    $scope.deals = response.data;
        //}, function (error) {
        //    // failure handler
        //    $log.error("DealService.get() failed:", JSON.stringify(error));
        //});
        CategoryDealService.get({Keywords:'cars'}, function (response) {
            $log.debug("CategoryDealService.get() success!", response);
            $scope.deals = response.deals;
        }, function (error) {
            // failure handler
            $log.error("CategoryDealService.get() failed:", JSON.stringify(error));
        });
        //popup detail
        $scope.detail = function($index){
            //DealService.get({Index:$index}, function (response) {
            //    $log.debug("DealService.get($index) success!", response);
            //    $rootScope.deal = response;
            //    $rootScope.detailModal_deal.show();
            //}, function (error) {
            //    // failure handler
            //    $log.error("DealService.get($index) failed:", JSON.stringify(error));
            //});
            $rootScope.deal = $index;
            $rootScope.deal.image_url = $sce.trustAsResourceUrl($rootScope.deal.image_url);
            //for (prop in $index) {
            //    $log.debug(prop + ' = ' + $index[prop]);
            //}
            //$log.debug("$rootScope.deal:"+$rootScope.deal);
            $rootScope.detailModal_deal.show();
        }

        //
        $scope.trustAsHtml = function(rawHtml)
        {
            return $sce.trustAsHtml(rawHtml);
        }

    })
    .controller('DealModalCtrl', function ($scope,$rootScope,$log,DealService,CategoryDealService,$sce) {
        //GA start
        if (typeof analytics !== 'undefined') {
            analytics.trackView("DealModalCtrl");
        }//GA end
        //
    })
;
