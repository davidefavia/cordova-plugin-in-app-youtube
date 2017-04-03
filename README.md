# cordova-plugin-in-app-youtube

Open YouTube video in app or using the browser as fallback for Android & iOS.

## Getting Started

This plugin has been tested with Cordova 6.5.0, Android 7.1.1 and iOS 10.3.

Install via Cordova:

```
$ cordova plugin install https://github.com/davidefavia/cordova-plugin-in-app-youtube
```

## Reference

### window.InAppYouTube.openVideo(videoId, [options, successCallback, errorCallback])

It opens a YouTube video.

#### Parameters

|Parameter|Type|Default|Description|
|-|:-:|:-:|-|
|videoId|string||YouTube video identifier, _e.g._ `9bZkp7q19f0`.|
|options|object|{}|Use `fullscreen = true` to enable fullscreen (only Android).|
|successCallback|function|function() {}|Returns plugin result object with videoId, options and type key. type can be `application` or `webview` depending on which application opened the video.|
|errorCallback|function|function() {}|Returns error or exception message.|

Platforms have different behaviors.

#### Android

- It shows the video using the YouTube activity if the native app is installed. On back button press the native video closes and the WebView gets focus. It is possible to play fullscreen video.
- It opens default web browser if the native YouTube app is not installed.

#### iOS

- It shows the video using the native YouTube app if it is installed. It is NOT possible to play fullscreen video.
- It opens default web browser if the native YouTube app is not installed. There is a back link in the statusbar to go back to Cordova application.

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/davidefavia/cordova-plugin-in-app-youtube/tags).

## Author

[davidefavia](https://github.com/davidefavia)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

* [https://github.com/Glitchbone/CordovaYoutubeVideoPlayer]
