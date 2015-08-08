/**
 * Created by yangboz on 15/8/8.
 */
angular.module('starter.services', ['ngResource'])
    .factory('Session', function ($resource) {
        return $resource('http://localhost:5000/sessions/:sessionId');
    });