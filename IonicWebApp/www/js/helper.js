var DynamicEnvironment = DynamicEnvironment || {};
//Helper functions here.
/**
 * You can have as many environments as you like in here
 * just make sure the host matches up to your hostname including port
 */
var _environment;
var _environments = {
    local: {
        host: 'localhost:8100',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://localhost:8083/api/',
            api_version: '/v1/',
            ga_track_code:'UA-70365426-1'
        }
    },
    dev: {
        host: 'http://54.201.26.68',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://54.201.26.68/:8083/api',
            api_version: '/v1/',
            ga_track_code:'UA-70169433-1'
        }
    },
    test: {
        host: 'http:test.com',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://test.com:8083/api',
            api_version: '/v1/'
        }
    },
    stage: {
        host: 'http://stage.com',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://stage.com/api/',
            api_version: '/v1/'
        }
    },
    prod: {
        host: 'http://prod.com',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://prod.com/api/',
            api_version: '/v1/'
        }
    }
};
_getEnvironment = function () {
    var host = window.location.host;
    console.log("_getEnvironment.host:"+host);
    if (_environment) {
        return _environment;
    }
    // console.log("host:",host);
    for (var environment in _environments) {
        if (typeof _environments[environment].host && _environments[environment].host == host) {
            _environment = environment;
            return _environment;
        }
    }

    return "dev";//default
};
DynamicEnvironment.get = function (property) {
    var result = _environments[_getEnvironment()].config[property];
    //var result = _environments["dev"].config[property];
    console.log("_getEnvironment():",_getEnvironment());
    console.log("DynamicEnvironment.get():",result);
    return result;
};
