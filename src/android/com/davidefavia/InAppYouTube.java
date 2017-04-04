/**
 */
package com.davidefavia;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.content.ActivityNotFoundException;

import java.util.Date;

public class InAppYouTube extends CordovaPlugin {
  private static final String TAG = "InAppYouTube";

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing InAppYouTube");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("openVideo")) {
      String videoId = args.getString(0);
      JSONObject options = args.getJSONObject(1);
      this.openVideo(videoId, options, callbackContext);
    }
    return true;
  }

  private void openVideo(String videoId, JSONObject options, CallbackContext callbackContext) {
    Boolean isFullScreen = options.optBoolean("fullscreen");
    String webUrl;
    if(isFullScreen) {
      webUrl = "https://www.youtube.com/embed/" + videoId;
    } else {
      webUrl = "https://www.youtube.com/watch?v=" + videoId;
    }
    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
    Log.d(TAG, "fullscreen: " + String.valueOf(options.optBoolean("fullscreen")));
    String type;
    try {
      appIntent.putExtra("force_fullscreen", isFullScreen);
      this.cordova.getActivity().startActivity(appIntent);
      type = "application";
    } catch (ActivityNotFoundException ex) {
      this.cordova.getActivity().startActivity(webIntent);
      type = "webview";
    }
    try {
      JSONObject json = new JSONObject();
      json.put("videoId", videoId);
      json.put("options", options);
      json.put("type", type);
      callbackContext.success(json);
    } catch (JSONException e) {
      Log.e(TAG, e.toString());
      callbackContext.error(e.toString());
    }
  }

}
