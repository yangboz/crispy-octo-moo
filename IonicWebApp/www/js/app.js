// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'ngOpenFB', 'ngLinkedIn',
    'ngCordova', 'ngResource', 'LocalForageModule'])

    .run(function ($ionicPlatform, ngFB, $rootScope, Enum, CacheService,$state,$log) {

        ngFB.init({appId: '759417430835351'});//crispy-octo-moo:1153014161379784

    //
    $log.info("ionic.Platform info(platform,device,version,isWebView):",
      ionic.Platform.platform()
      , ionic.Platform.device()
      , ionic.Platform.version()
      , ionic.Platform.isWebView()
    );

        $ionicPlatform.ready(function () {
            //analytics.startTrackerWithId('UA-XXXX-YY') <br />
            //analytics.trackView('Screen Title')<br />
            //analytics.trackEvent('Category', 'Action', 'Label', Value)<br />
            //analytics.addTransaction('ID', 'Affiliation', Revenue, Tax, Shipping, 'Currency Code')<br />
            //analytics.addTransactionItem('ID', 'Name', 'SKU', 'Category', Price, Quantity, 'Currency Code')<br />
            //analytics.setUserId('my-user-id')<br />
            //analytics.debugMode()<br />
            //GA begin
            if (typeof analytics !== 'undefined'){
                console.log("Google Analytics active!!!");
                analytics.debugMode();
                analytics.startTrackerWithId(DynamicEnvironment.get('ga_track_code'));
                analytics.trackView('Crispy-octo-moo InoicWebApp')
            }//GA end
            else {
                console.log("Google Analytics Unavailable!!!");
            }
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
                cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
                cordova.plugins.Keyboard.disableScroll(true);

            }
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleLightContent();
            }

        });

        //stateChange event
        $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){

            //CacheService.get(Enum.localStorageKeys.OAUTH_OBJ_FB).then(function (data) {
            //    console.log(Enum.localStorageKeys.OAUTH_OBJ_FB, data);
            //    if (data == null) {
            //        // User isn’t authenticated
            //        $state.transitionTo("tab.dash");
            //        event.preventDefault();
            //    }
            //});
        });
    })
    //Support RESTful PATCH
    //@see: http://stackoverflow.com/questions/20305615/configure-angularjs-module-to-send-patch-request
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.patch = {
            'Content-Type': 'application/json;charset=utf-8'
        }
        //@see: http://forum.ionicframework.com/t/ionicloading-in-http-interceptor/4599/7
        $httpProvider.interceptors.push('TrendicityInterceptor');
    }])
    ////$log configure
    .config(['$logProvider', function ($logProvider) {
        $logProvider.debugEnabled(true);
        //TODO:https://github.com/ThomasBurleson/angularjs-logDecorator
    }])
    ///ENV_config
    .constant('CONFIG_ENV', {
        //'api_endpoint_base': 'http://localhost:8083/api/',
        'api_endpoint_base': DynamicEnvironment.get('api_endpoint_base'),
        //'api_endpoint': 'http://localhost:8083/api/v1/',
        'api_endpoint': DynamicEnvironment.get('api_endpoint_base'),
        //'api_version': 'v1'
        'api_version': DynamicEnvironment.get('api_version')
    })

    .config(function ($stateProvider, $urlRouterProvider, $linkedInProvider) {

        //@see:https://github.com/boketto/ngLinkedIn
        $linkedInProvider.set('appKey', '77nayor82qqip3')
            //.set('scope', 'r_basicprofile r_network r_emailaddress rw_company_admin w_share rw_nus');
            //.set('scope', 'r_basicprofile');
            .set('authorize', true);
            //.set('credentials_cookie', true);

        // Ionic uses AngularUI Router which uses the concept of states
        // Learn more here: https://github.com/angular-ui/ui-router
        // Set up the various states which the app can be in.
        // Each state's controller can be found in controllers.js
        $stateProvider

            // setup an abstract state for the tabs directive
            .state('tab', {
                url: '/tab',
                abstract: true,
                templateUrl: 'templates/tabs.html'
            })

            // Each tab has its own nav history stack:

            .state('tab.dash', {
                url: '/dash',
                views: {
                    'tab-dash': {
                        templateUrl: 'templates/tab-dash.html',
                        controller: 'DashCtrl'
                    }
                }
            })

            .state('tab.updates', {
                url: '/updates',
                cache: false,
                views: {
                    'tab-updates': {
                        templateUrl: 'templates/tab-updates.html',
                        controller: 'UpdatesCtrl'
                    }
                }
            })

            .state('tab.accounts', {
                url: '/accounts',
                //cache: false,
                views: {
                    'tab-accounts': {
                        templateUrl: 'templates/tab-accounts.html',
                        controller: 'AccountsCtrl'
                    }
                }
            })
            .state('tab.account-invites', {
                url: '/accounts/:accountId',
                views: {
                    'tab-accounts': {
                        templateUrl: 'templates/account-invites.html',
                        controller: 'AccountInvitesCtrl'
                    }
                }
            })
            .state('tab.account-settings', {
                url: '/accounts/:accountId',
                views: {
                    'tab-accounts': {
                        templateUrl: 'templates/account-settings.html',
                        controller: 'AccountSettingsCtrl'
                    }
                }
            })

            .state('tab.deals', {
                url: '/deals',
                //cache: false,
                views: {
                    'tab-deals': {
                        templateUrl: 'templates/tab-deals.html',
                        controller: 'DealsCtrl'
                    }
                }
            });

        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/tab/dash');
    })
    //Filters
    .filter('sumOfValue', function ($log) {
        return function (data, key) {
            if (typeof (data) === 'undefined' && typeof (key) === 'undefined') {
                return 0;
            }
            var sum = 0;

            //$log.info("data:"+data+",key:"+key);
            //$log.info("data[0]:"+data[0]);

            if(data && data.length) {
                for (var i = 0; i < data.length; i++) {
                    //$log.info(data[i]["deal"][key]);
                    sum = sum + parseInt(data[i]["deal"][key]);
                }
            }
            return sum;
        }
    });
