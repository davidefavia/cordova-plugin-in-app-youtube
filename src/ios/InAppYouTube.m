#import "InAppYouTube.h"

#import <Cordova/CDVAvailability.h>

@implementation InAppYouTube

- (void)pluginInitialize {
}

- (void)openVideo:(CDVInvokedUrlCommand *)command {
    NSString* videoId = [command.arguments objectAtIndex:0];
    NSDictionary* options = [command.arguments objectAtIndex:1];
    BOOL isFullScreen = [options[@"fullscreen"] boolValue];

    NSURL *linkToAppURL = [NSURL URLWithString:[NSString stringWithFormat:@"youtube://watch?v=%@",videoId]];
    NSURL *linkToWebURL = [NSURL URLWithString:[NSString stringWithFormat:@"https://www.youtube.com/watch?v=%@",videoId]];
    if(isFullScreen) {
      linkToWebURL = [NSURL URLWithString:[NSString stringWithFormat:@"https://www.youtube.com/embed/%@",videoId]];
    }

    NSMutableDictionary *dictionary = [NSMutableDictionary dictionary];
    [dictionary setObject:videoId forKey:@"videoId"];
    [dictionary setObject:options forKey:@"options"];

    // http://stackoverflow.com/a/35409477
    if ([[UIApplication sharedApplication] canOpenURL:linkToAppURL]) {
      // @TODO Check for fullscreen
      // Can open the youtube app URL so launch the youTube app with this URL
      [[UIApplication sharedApplication] openURL:linkToAppURL];
      [dictionary setObject:@"application" forKey:@"type"];
    } else {
      // Can't open the youtube app URL so launch Safari instead
      // https://useyourloaf.com/blog/openurl-deprecated-in-ios10/
      UIApplication *application = [UIApplication sharedApplication];
      if ([application respondsToSelector:@selector(openURL:options:completionHandler:)]) {
        [application openURL:linkToWebURL options:@{} completionHandler:nil];
      } else {
        [application openURL:linkToWebURL];
      }
      [dictionary setObject:@"webview" forKey:@"type"];
    }
    
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:dictionary];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

@end
