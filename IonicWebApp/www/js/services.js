angular.module('starter.services', [])
    .factory('EITCCreditService', function () {
        // Might use a resource here that returns a JSON array

        //var numberofChildren = parseInt(Session.get('numberofChildren'));
        //var income = parseInt(Session.get('income'));
        //var relationship_status = Session.get('relationship_status');
        //console.log('numberofchildren income relationship_status:' + numberofChildren + ' ' + income + ' ' + relationship_status);
        var incomeFixture = [20330, 44651, 49974, 53267];
        var numberofChildrenThresholds = [0, 1, 2, 3];
        var creditFixture = ['$503', '$3359', '$5548', '$6242', 'unknown'];
        var eitccreditdetail = 'EITC Credit:';

        return {
            get: function ($relationship_status, $numberofChildren, $income) {

                if ($relationship_status) {//"Married"
                    for (var i = 0; i < numberofChildrenThresholds.length; i++) {
                        if ($income < incomeFixture[i]) {
                            eitccreditdetail = 'EITC Credit:' + creditFixture[i];
                        } else {
                            eitccreditdetail = 'EITC Credit:' + ' unknown';
                        }
                    }
                } else {
                    eitccreditdetail = 'EITC Credit:' + ' unknown';
                }
                return eitccreditdetail;
            }
        }
    })
    .factory('IncomeCategoryService', function () {
        var categoriesFixture = [
            //["0-150000", "300000-700000","700000-10000000","1000000-1500000"],
            //["150000-300000","300000-700000","700000-10000000","1000000-1500000"]
            [{"label": "0-150000"}, {"label": "300000-700000"}, {"label": "700000-10000000"}, {"label": "1000000-1500000"}],
            [{"label": "150000-300000"}, {"label": "300000-700000"}, {"label": "700000-10000000"}, {"label": "1000000-1500000"}]
        ];

        return {
            get: function ($relationship_status) {

                return categoriesFixture[$relationship_status];
            }
        }
    })
    .factory('FilingCategoryService', function () {
        var categoriesFixture = [
            [{"label": "filing jointly"}, {"label": "filing separatly"}],
            [{"label": "single filer"}, {"label": "head of household"}]
        ];

        return {
            get: function ($relationship_status) {

                return categoriesFixture[$relationship_status];
            }
        }
    })
    .factory('ChildrenCategoryService', function () {
        var categoriesFixture = [{"label": "0"}, {"label": "1"}, {"label": "2"}, {"label": "3"}, {"label": "4"},
            {"label": "5"}, {"label": "6"}, {"label": "7"}, {"label": "8"}];

        return {
            all: function () {
                return categoriesFixture;
            }
        }
    })

    //EVCreditService
    .factory('EVCreditService', function ($resource, CONFIG_ENV) {
        var data = $resource(
            CONFIG_ENV.api_endpoint + "evc/:Key",
            {Key: "@Key"},
            {
                "update": {method: "PUT"}
            })
        return data;
    })
    //.factory('EVCreditService', function () {
    //    // Might use a resource here that returns a JSON array
    //
    //    // Some fake testing data
    //    var ElecticVehicleCredits = [
    //        {"label": 'Accord Plug-In Hybrid', "value": 3626},
    //        {"label": 'Azure Dynamics Transit Connect Electric Vehicle', "value": 7500},
    //        {"label": 'BMW i3 Sedan', "value": 7500},
    //        {"label": 'BMW i8', "value": 3793},
    //        {"label": 'Boulder Electric DV-500', "value": 7500},
    //        {"label": 'BYD e6 Electric Vehicle', "value": 7500},
    //        {"label": 'Fiat 500e', "value": 7500},
    //        {"label": 'CODA Sedan', "value": 7500},
    //        {"label": 'Electric Vehicles International (EVI) Electric truck', "value": 7500},
    //        {"label": 'EMC Model E36 (Electric Vehicle Manufactured by Electric Mobile Cars)', "value": 7500},
    //        {"label": 'Fisker Karma', "value": 7500},
    //        {"label": 'Ford Focus Electric', "value": 7500},
    //        {"label": 'Ford C-MAX Energi', "value": 4007},
    //        {"label": 'Ford Fusion Energi', "value": 4007},
    //        {"label": 'Cadillac ELR', "value": 7500},
    //        {"label": 'Chevrolet Volt', "value": 7500},
    //        {"label": 'Chevrolet Spark EV', "value": 7500},
    //        {"label": 'Kia Soul Electric', "value": 7500},
    //        {"label": 'Mercedes-Benz smart Coupe/Cabrio EV', "value": 7500},
    //        {"label": 'Mercedes-Benz B-Class EV', "value": 7500},
    //        {"label": 'Mitsubishi i-MiEV', "value": 7500},
    //        {"label": 'Nissan Leaf', "value": 7500},
    //        {"label": 'Porsche 918 Spyder', "value": 3667},
    //        {"label": 'Porsche Panamera S E Hybrid', "value": 4751.80},
    //        {"label": 'Porsche Caynee S E-Hybrid', "value": 5335.60},
    //        {"label": 'smart fortwo', "value": 7500},
    //        {"label": 'Tesla Roadster', "value": 7500},
    //        {"label": 'Tesla Model S', "value": 7500},
    //        {"label": 'Think City EV', "value": 7500},
    //        {"label": 'Toyota Prius Plug-in Electic Drive Vehicle', "value": 2500},
    //        {"label": 'Toyota RAV4 EV', "value": 7500},
    //        {"label": 'VIA 2500', "value": 7500},
    //        {"label": 'VIA 1500', "value": 7500},
    //        {"label": 'Volkswagen e-Golf', "value": 7500},
    //        {"label": 'Wheego LiFe Electric Vehicle', "value": 7500},
    //        {"label": 'Zenith Electric Van', "value": 7500}
    //    ];
    //
    //    return {
    //        all: function () {
    //            return ElecticVehicleCredits;
    //        },
    //        get: function ($key) {
    //            return ElecticVehicleCredits[$key];
    //        }
    //    };
    //})
    .factory('MortgageInterestService', function () {
        // Might use a resource here that returns a JSON array

        // Some fake testing data
        var MortgageInterests = [{"label": "MORTGAGE"}, {"label": "HOME OWNER"}, {"label": "OUR HOUSE"},
            {"label": "NEW HOUSE"}, {"label": "MOVING TO A NEW HOUSE"}];

        return {
            all: function () {
                return MortgageInterests;
            }
        };
    })
    .factory('ChildrenKeywordsService', function () {
        // Might use a resource here that returns a JSON array

        // Some fake testing data

        var Children = [{"label": "new baby"}, {"label": "my daughter"}, {"label": "my son"}];

        return {
            all: function () {
                return Children;
            }
        };
    })
    //UserProfileService
    .factory('UserProfileService', function ($resource, CONFIG_ENV) {
        var data = $resource(
            CONFIG_ENV.api_endpoint + "user/profile/:Id",
            {Id: "@Id"},
            {
                "update": {method: "PUT"}
            })
        return data;
    })
    //UserMeService
    .factory('UserMeService', function ($resource, CONFIG_ENV) {
        var data = $resource(
            CONFIG_ENV.api_endpoint + "user/me",
            {},
            {
            })
        return data;
    })
    ////ConnectService
    //.factory('ConnectService', function ($resource, CONFIG_ENV) {
    //    var data = $resource(
    //        CONFIG_ENV.api_endpoint  + "connect/:providerId",
    //        {providerId: "@providerId"},
    //        {
    //            "update": {method: "PUT"}
    //        })
    //    return data;
    //})
    //FbUserProfileService
    .factory('FbUserProfileService', function ($resource, CONFIG_ENV) {
        var data = $resource(
            CONFIG_ENV.api_endpoint + "connect/facebook/profile/",
            {},
            {})
        return data;
    })
    //LiUserProfileService
    .factory('LiUserProfileService', function ($resource, CONFIG_ENV) {
        var data = $resource(
            CONFIG_ENV.api_endpoint + "connect/linkedin/profile/",
            {},
            {})
        return data;
    })
//@see http://stackoverflow.com/questions/16627860/angular-js-and-ng-swith-when-emulating-enum
    .factory('Enum', [function () {
        var service = {
            getUUID: function () {
                // http://www.ietf.org/rfc/rfc4122.txt
                var s = [];
                var hexDigits = "0123456789abcdef";
                for (var i = 0; i < 36; i++) {
                    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                }
                s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
                s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
                s[8] = s[13] = s[18] = s[23] = "-";

                var uuid = s.join("");
                return uuid;
            }
            , getTimestamp: function () {
                var now = new Date;
                var utc_timestamp = Date.UTC(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate(),
                    now.getUTCHours(), now.getUTCMinutes(), now.getUTCSeconds(), now.getUTCMilliseconds());
                return utc_timestamp;
            }
            , relationshipStatus: [
                //married:
                {
                    label: "Married",
                    value: 0
                },
                //single:
                {
                    label: "Single",
                    value: 1
                }
            ]
            , localStorageKeys: {
                OAUTH_OBJ_FB: 'oauth_obj_4_facebook',
                OAUTH_OBJ_LI: 'oauth_obj_4_linkedin'
            }
            , socialProviders: {
                FACEBOOK: 'facebook',
                LINKEDIN: 'linkedin'
            }
        };
        return service;
    }])
///@see: http://forum.ionicframework.com/t/ionicloading-in-http-interceptor/4599/7
    .factory('TrendicityInterceptor',
    function ($injector, $q, $log) {

        var hideLoadingModalIfNecessary = function () {
            var $http = $http || $injector.get('$http');
            if ($http.pendingRequests.length === 0) {
                $injector.get('$ionicLoading').hide();
            }
        };

        return {
            request: function (config) {
                $injector.get('$ionicLoading').show();

                // Handle adding the access_token or auth request.

                return config;
            },
            requestError: function (rejection) {
                hideLoadingModalIfNecessary();
                return $q.reject(rejection);
            },
            response: function (response) {
                hideLoadingModalIfNecessary();
                return response;
            },
            responseError: function (rejection) {
                hideLoadingModalIfNecessary();
                //http status code check
                $log.error("detected what appears to be an Instagram auth error...", rejection);
                if (rejection.status == 400) {
                    rejection.status = 401; // Set the status to 401 so that angular-http-auth inteceptor will handle it
                }
                return $q.reject(rejection);
            }
        };
    })
    //@see: http://stackoverflow.com/questions/2326943/when-do-items-in-html5-local-storage-expire/17632458#17632458
    //@see: https://github.com/mozilla/localForage
    //@see: https://github.com/ocombe/angular-localForage
    .factory('CacheService', function ($localForage) {

        return {
            set: function (key, value, expireTimeInSeconds) {
                return $localForage.setItem(key, {
                    data: value,
                    timestamp: new Date().getTime(),
                    expireTimeInMilliseconds: expireTimeInSeconds * 1000
                })
            },
            get: function (key) {
                return $localForage.getItem(key).then(function (item) {
                    if (!item || new Date().getTime() > (item.timestamp + item.expireTimeInMilliseconds)) {
                        return null
                    } else {
                        return item.data
                    }
                })
            },
            remove: function (key) {
                return $localForage.removeItem(key);
            }
        }

    });
