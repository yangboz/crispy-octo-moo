angular.module('starter.controllers', [])

    .controller('MainCtrl', function ($scope, $rootScope, $ionicModal, $timeout, $ionicLoading, CacheService, Enum,$log
        ,OverviewService,UserTaxEventService,$interval,UserMeService) {

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
            //CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_LI);//for debugging
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
            backdropClickToClose: true,
            animation: 'slide-in-up'
        }).then(function (modal) {
            console.log("modal-login-fb.html initialization.");
            $rootScope.loginModal_fb = modal;
            //Login Modal show();
            //@see: CacheService
            CacheService.remove(Enum.localStorageKeys.OAUTH_OBJ_FB);//for debugging
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
            $rootScope.loginModal_fb.remove();
            $rootScope.loginModal_li.remove();
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
                        //Cache it.@see:https://developers.facebook.com/tools/debug/accesstoken
                        CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_FB, JSON.stringify($rootScope.oauth_obj_fb), 1 * 60 * 60);//1443168000 (in about an hour)
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
            CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_LI, JSON.stringify($rootScope.oauth_obj_li), $rootScope.oauth_obj_li.oauth_expires_in);
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
    })
    .controller('DashCtrl', function ($scope, $rootScope, $log,$sce) {
        //
        $scope.trustAsHtml = function(rawHtml)
        {
            return $sce.trustAsHtml(rawHtml);
        }

    })

    .controller('UpdatesCtrl', function ($scope, $rootScope, $log,TaxEventService,$sce) {
        //
        $scope.trustAsHtml = function(rawHtml)
        {
            return $sce.trustAsHtml(rawHtml);
        }

    })

    .controller('AccountsCtrl', function ($scope, $rootScope, $log, Enum,FilingCategoryService) {
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

    .controller('MeModalCtrl', function ($scope, $rootScope, $log, Enum, UserProfileService) {
        $scope.profile = {income:0,children:0,filingCategory:''};
        //Submit profile inputs.
        $scope.save = function(){
            $log.info("preSaved me profile:"+",income:"+$scope.profile.income+",children:"+$scope.profile.children
                +",filingCategory(label,group):"+$scope.profile.filingCategory.label+","+$scope.profile.filingCategory.group);
            //Update user profile service.
            $rootScope.me.rwIncome = $scope.profile.income;
            $rootScope.me.rwTaxFilingStatus = $scope.profile.filingCategory.label;
            $rootScope.me.rwNumberOfChildren = $scope.profile.children;
            //
            UserProfileService.update({Id:$rootScope.me.id},$rootScope.me, function (response) {
                $log.debug("UserProfileService.update() success!", response);
                $rootScope.me = response;
                $rootScope.detailModal_me.hide();
            }, function (error) {
                // failure handler
                $log.error("UserProfileService.update() failed:", JSON.stringify(error));
            });
        }
    })

    .controller('AccountSettingsCtrl', function ($scope, $rootScope, IncomeCategoryService, FilingCategoryService,
                                                 ChildrenCategoryService, EVCreditService, MortgageInterestService,
                                                 ChildrenKeywordsService, EITCCreditService, Enum, $ionicPopup, $log) {
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
        $scope.settings = {
            enableFriends: true
        };
    })

    .controller('DealsCtrl', function ($scope,$rootScope,$log,DealService,CategoryDealService,$sce) {
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
        //
    })
;
