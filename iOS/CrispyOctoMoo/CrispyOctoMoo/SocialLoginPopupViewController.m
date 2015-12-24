//
//  SocialLoginPopupViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/11/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "SocialLoginPopupViewController.h"

@interface SocialLoginPopupViewController ()

@end

@implementation SocialLoginPopupViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    //Variables init
    //
    // Do any additional setup after loading the view from its nib.
    // Do any additional setup after loading the view, typically from a nib.
    //@see: https://developers.facebook.com/docs/reference/ios/4.6/class/FBSDKLoginButton/
    FBSDKLoginButton *loginButton = [[FBSDKLoginButton alloc] init];
    // Optional: Place the button in the center of your view.
    loginButton.center = self.view.center;
    loginButton.readPermissions = @[@"public_profile", @"email", @"user_friends" ];
    //  @[@"id",@"name",@"email",@"relationship_status",@"work",@"birthday",@"location", @"posts", @"family"];//@"education",
    [self.view addSubview:loginButton];
    ///implements of FBSDKLoginButtonDelegate
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(fbLoginHandler) name:FBSDKAccessTokenDidChangeNotification object:nil] ;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (void)fbLoginHandler{
    //
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
