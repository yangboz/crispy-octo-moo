var DynamicEnvironment = DynamicEnvironment || {};
//Helper functions here.
/**
 * You can have as many environments as you like in here
 * just make sure the host matches up to your hostname including port
 */
var _environment;
var _environments = {
    local: {
        host: 'http://localhost:63342',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://localhost:8083/api/',
            api_version: '/v1/'
        }
    },
    dev: {
        host: 'http://ec2-52-27-252-152.us-west-2.compute.amazonaws.com',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://ec2-52-27-252-152.us-west-2.compute.amazonaws.com:8083/api',
            api_version: '/v1/'
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

    if (_environment) {
        return _environment;
    }

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
    //console.log("_getEnvironment():",_getEnvironment());
    console.log("DynamicEnvironment.get():",result);
    return result;
};
