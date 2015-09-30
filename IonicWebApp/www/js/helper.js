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
            api_endpoint_base: 'http://localhost:8083/api/'
        }
    },
    dev: {
        host: 'http://localhost:8100',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://192.168.2.28:8083/api/'
        }
    },
    test: {
        host: 'http://localhost:8100',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://localhost:8083/api/'
        }
    },
    stage: {
        host: 'http://localhost:8100',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://localhost:8083/api/'
        }
    },
    prod: {
        host: 'http://localhost:8100',
        config: {
            /**
             * Add any config properties you want in here for this environment
             */
            api_endpoint_base: 'http://localhost:8083/api/'
        }
    },
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

    return "local";//default
};
DynamicEnvironment.get = function (property) {
    //var result = _environments[_getEnvironment()].config[property];
    var result = _environments["dev"].config[property];
    console.log("DynamicEnvironment.get():",result);
    return result;
};
