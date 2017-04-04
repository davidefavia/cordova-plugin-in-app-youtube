var exec = require('cordova/exec');

var PLUGIN_NAME = 'InAppYouTube';

var noop = function() {};

var InAppYouTube = {
  openVideo: function(videoId, options, successCallback, errorCallback) {
    exec(successCallback || noop, errorCallback || noop, PLUGIN_NAME, 'openVideo', [videoId, options || {}]);
  }
};

module.exports = InAppYouTube;
