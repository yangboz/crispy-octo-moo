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

@synthesize overviewsResult;

- (void)viewDidLoad {
    [super viewDidLoad];
    //Variables init

//If social login required.
    STPopupController *popupController = [[STPopupController alloc] initWithRootViewController:[[UIStoryboard storyboardWithName:@"Main" bundle:nil] instantiateViewControllerWithIdentifier:@"SocialLoginPopupViewController"]];
    popupController.cornerRadius = 4;
    [popupController presentInViewController:self];

    //NotificationCenter
    ///implements of FBSDKLoginButtonDelegate
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(fbLoginHandler) name:FBSDKAccessTokenDidChangeNotification object:nil] ;
//    self.tableView.dataSource = self;
//    self.tableView.delegate = self;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadOverviewsHandler:) name:kNCpN_load_overviews object:nil] ;
}

- (void)fbLoginHandler{
    NSString *fbUserId = [[FBSDKAccessToken currentAccessToken] userID];
    NSString *fbAccessTokenStr = [[FBSDKAccessToken currentAccessToken] tokenString];
    NSLog(@"fbAccessToken id:%@,token:%@",fbUserId,fbAccessTokenStr);
    //@TODO:Cache it.@see:https://developers.facebook.com/tools/debug/accesstoken
    //    CacheService.set(Enum.localStorageKeys.OAUTH_OBJ_SOCIAL, JSON.stringify($rootScope.oauth_obj_fb), 1 * 60 * 60);//1443168000 (in about an hour)
    //retrieve,handlers(parse,assemble,storage)
    //    [self syncFbProfile];
    
    //facebook user profile handler
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
    snap415Token.id = fbUserId;
    snap415Token.token = fbAccessTokenStr;
    snap415Token.provider = @"facebook";
    //
    [Snap415Model sharedInstance].snap415Token = snap415Token;
    //
    [[Snap415API sharedInstance] syncUserProfile];
}

-(void)loadOverviewsHandler:(NSNotification *) notification{
    //Dismiss the social login popup.
    [self.popupController dismiss];
    //    NSLog(@"loadOverviewsHandler:%@",notification.userInfo);
        self.overviewsResult = [(NSDictionary *)notification.object objectForKey:kNCpN_load_overviews];
    NSLog(@"self.overviewsResult:%@",self.overviewsResult.description);
    //
    [self.tableView reloadData];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
//    NSLog(@"self overviewsResult,count:%d",[[self overviewsResult] count]);
    return [[self overviewsResult] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:  (NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = kCellReuseId_overview;
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:CellIdentifier];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
//    if (cell == nil) {
//        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
//    }
    
    WebSiteObject *webSiteObject= [self.overviewsResult objectAtIndex:indexPath.row];
    
    cell.selectionStyle = UITableViewCellStyleSubtitle;
    cell.textLabel.text = webSiteObject.header;
    cell.detailTextLabel.text = webSiteObject.body;
//    cell.detailTextLabel.text = webSiteObject.footer;
    
    return cell;
}

@end
