//
//  FirstViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "FirstViewController.h"



@interface FirstViewController()

@end

@implementation FirstViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    //@see: https://developers.facebook.com/docs/reference/ios/4.6/class/FBSDKLoginButton/
    FBSDKLoginButton *loginButton = [[FBSDKLoginButton alloc] init];
    // Optional: Place the button in the center of your view.
    loginButton.center = self.view.center;
    loginButton.readPermissions = @[@"public_profile", @"email", @"user_friends" ];
//  @[@"id",@"name",@"email",@"relationship_status",@"work",@"birthday",@"location", @"posts", @"family"];//@"education",
    [self.view addSubview:loginButton];
    
    //NotificationCenter
    ///implements of FBSDKLoginButtonDelegate
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(fbLoginHandler) name:FBSDKAccessTokenDidChangeNotification object:nil] ;
}

- (void)fbLoginHandler{
    NSString *fbAccessTokenStr = [[FBSDKAccessToken currentAccessToken] tokenString];
    NSLog(@"fbAccessTokenStr:%@",fbAccessTokenStr);
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
