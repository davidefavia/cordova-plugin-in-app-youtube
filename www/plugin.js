var exec = require('cordova/exec');

var PLUGIN_NAME = 'InAppYouTube';

var noop = function() {};

var InAppYouTube = {
  openVideo: function(videoId, options, cbSuccess, cbError) {
    exec(cbSuccess || noop, cbError || noop, PLUGIN_NAME, 'openVideo', [videoId, options || {}]);
  }
};

module.exports = InAppYouTube;
