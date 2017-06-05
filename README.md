# cordova-plugin-in-app-youtube

Open YouTube video in app or use the browser as fallback for Android & iOS. Plugin goal is to show YouTube video via native app or mobile website to count for views.

If you need to stream YouTube video content without using its own player, just check [Glitchbone/CordovaYoutubeVideoPlayer](https://github.com/Glitchbone/CordovaYoutubeVideoPlayer) project.

## Getting Started

This plugin has been tested with Cordova 6.5.0, Android 7.1.1 and iOS 10.3.

Install via Cordova:

```
$ cordova plugin add https://github.com/davidefavia/cordova-plugin-in-app-youtube
```

## Reference

### window.InAppYouTube.openVideo(videoId, [options, successCallback, errorCallback])

It opens a YouTube video.

#### Parameters

|Parameter|Type|Default|Description|
|-|:-:|:-:|-|
|videoId|string||YouTube video identifier, _e.g._ `9bZkp7q19f0`.|
|options|object|{}|Use `fullscreen = true` to enable fullscreen.|
|successCallback|function|function() {}|Plugin returns result object with `videoId` (string), `options` (object) and `type` (string) key. `type` can be `application` or `webview` depending on which application opened the video.|
|errorCallback|function|function() {}|Plugin returns error or exception message (string).|

Platforms have different behaviors.

|Platform|YouTube app installed|fullscreen option|Behavior|
|:-:|:-:|:-:|-|
|Android|Yes|true|YouTube app plays video in landscape mode (even if your app forbids this orientation) in fullscreen inside your app. Back button lets Cordova WebView take focus back.|
|Android|Yes|false|YouTube app plays video in portrait mode inside your app. Some related content is shown below the video player. Back button lets the Cordova WebView take focus back.|
|Android|No|true|URL `https://www.youtube.com/embed/<video-id>` is opened in the default browser in full window size, browser toolbar is visible. Video is stopped by default due to Chrome for Android policies.|
|Android|No|false|URL `https://www.youtube.com/watch?v=<video-id>` is opened in the default browser, browser toolbar is visible. Video is stopped by default due to Chrome for Android policies.|
|iOS|Yes|true|YouTube app plays video in portrait mode outside your app. Some related content is shown below the video player. Back button in top corner lets the Cordova WebView take focus back.|
|iOS|Yes|false|YouTube app plays video in portrait mode outside your app. Some related content is shown below the video player. Back button in top corner lets the Cordova WebView take focus back.|
|iOS|No|true|URL `https://www.youtube.com/embed/<video-id>` is opened in the Safari in full window size, toolbar is visible. Video is stopped by default due to Apple policies.|
|iOS|No|false|URL `https://www.youtube.com/watch?v=<video-id>` is opened in the default browser, browser toolbar is visible. Video is stopped by default due to Apple policies.|

## Example

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Device Ready Example</title>

    <script type="text/javascript" charset="utf-8" src="cordova.js"></script>
    <script type="text/javascript" charset="utf-8">

    // Wait for device API libraries to load
    function onLoad() {
      document.addEventListener("deviceready", onDeviceReady, false);
    }

    // device APIs are available
    function onDeviceReady() {
      // Now safe to use device APIs
      try {
        window.InAppYouTube.openVideo('9bZkp7q19f0', {
          fullscreen: true
        }, function(result) {
          // console.log(JSON.stringify(result));
        }, function(reason) {
          // console.log(reason);
        });
      } catch(e) {
        // Exception!
      }
    }

    </script>
  </head>
  <body onload="onLoad()">
  </body>
</html>
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
