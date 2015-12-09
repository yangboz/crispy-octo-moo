//
//  OverviewsViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "OverviewsViewController.h"



@interface OverviewsViewController()

@end

@implementation OverviewsViewController

Snap415API *_snap415API;

- (void)viewDidLoad {
    [super viewDidLoad];
    //Variables init
    _snap415API = [Snap415API sharedInstance];
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
    //API call testing here.
    //        $rootScope.loadOverviews();
    [_snap415API loadOverviews];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadOverviewsHandler:) name:kNCpN_load_overviews object:nil] ;
    //        $rootScope.loadTaxEvents();
//    [_snap415API loadTaxEvents];
    //        $rootScope.loadUserMe();
    
}


- (void)fbLoginHandler{
    NSString *fbUserId = [[FBSDKAccessToken currentAccessToken] userID];
    NSString *fbAccessTokenStr = [[FBSDKAccessToken currentAccessToken] tokenString];
    NSLog(@"fbAccessToken id:%@,token:%@",fbUserId,fbAccessTokenStr);
    //@TODO:Cache it.@see:https://developers.facebook.com/tools/debug/accesstoken
//    CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL, JSON.stringify($rootScope.oauth_obj_fb), 1 * 60 * 60);//1443168000 (in about an hour)
    //retrieve,handlers(parse,assemble,storage)
//    [self syncFbProfile];
    [self fbProfileHandler:fbUserId withToken:fbAccessTokenStr];
}

//facebook user profile handler
-(void)fbProfileHandler:(NSString *)userId withToken:(NSString *)token
{
//    $rootScope.fbUser = user;
//    console.log("$rootScope.fbUser:", $rootScope.fbUser);
//    $rootScope.hideLoading();
    //Sync the Facebook with token then get user profile.
//    FbUserProfileService.save({
//        'provider': Enum.socialProviders.FACEBOOK,
//        'id': user.id,
//        'token': $rootScope.oauth_obj_fb.accessToken
//    }, function (response) {
//        $log.debug("FbUserProfileService.get() success!", response);
//        //Default load overviews.
//        $rootScope.loadOverviews();
//        $rootScope.loadTaxEvents();
//        $rootScope.loadUserMe();
//    }, function (error) {
//        // failure handler
//        $log.error("FbUserProfileService.get() failed:", JSON.stringify(error));
//    });
    Snap415Token *snap415Token = [[Snap415Token alloc] init];
    snap415Token.id = userId;
    snap415Token.token = token;
    snap415Token.provider = @"facebook";
    
    [_snap415API syncUserProfile:snap415Token];
}

-(void)loadOverviewsHandler:(NSNotification *) notification{
    NSLog(@"loadOverviewsHandler:%@",notification.userInfo);
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
