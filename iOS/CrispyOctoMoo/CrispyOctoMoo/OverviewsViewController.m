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
static NSString *CellIdentifier = @"TableViewCell4WebObject";

- (void)viewDidLoad {
    [super viewDidLoad];
    //Variables init

//If social login required.
    STPopupController *popupController = [[STPopupController alloc] initWithRootViewController:[[UIStoryboard storyboardWithName:@"Main" bundle:nil] instantiateViewControllerWithIdentifier:@"SocialLoginPopupViewController"]];
    popupController.cornerRadius = 4;
    [popupController presentInViewController:self];

    //NotificationCenter
    ///implements of FBSDKLoginButtonDelegate
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(fbLoginHandler) name:FBSDKAccessTokenDidChangeNotification object:nil];
//    self.tableView.dataSource = self;
//    self.tableView.delegate = self;
    //NotificationCenter handler
    ///implements of FBSDKLoginButtonDelegate
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(fbLoginHandler) name:FBSDKAccessTokenDidChangeNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadMeHandler:) name:kNCpN_load_me object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadOverviewsHandler:) name:kNCpN_load_overviews object:nil];
    //Loading begin
    [MBProgressHUD showHUDAddedTo:self.view animated:YES];
}

- (void)fbLoginHandler{
    NSString *fbUserId = [[FBSDKAccessToken currentAccessToken] userID];
    NSString *fbAccessTokenStr = [[FBSDKAccessToken currentAccessToken] tokenString];
    NSLog(@"fbAccessToken id:%@,token:%@",fbUserId,fbAccessTokenStr);
    //
    Snap415Token *snap415Token = [[Snap415Token alloc] init];
    snap415Token.id = fbUserId;
    snap415Token.token = fbAccessTokenStr;
    snap415Token.provider = @"facebook";
    [Snap415Model sharedInstance].snap415Token = snap415Token;
    NSLog(@"[Snap415Model sharedInstance].snap415Token:%@",[Snap415Model sharedInstance].snap415Token);
    //PostUserProfile with snap415Token.
    [[Snap415API sharedInstance] postUserMe];
}

-(void)viewDidAppear:(BOOL)animated{
    
}

-(void)loadMeHandler:(NSNotification *) notification{
    //If you need to run your long-running task in the main thread, you should perform it with a slight delay, so UIKit will have enough time to update the UI (i.e., draw the HUD) before you block the main thread with your task.
    dispatch_time_t popTime = dispatch_time(DISPATCH_TIME_NOW, 0.01 * NSEC_PER_SEC);
    dispatch_after(popTime, dispatch_get_main_queue(), ^(void){
        //Delegate to Snap415API.
        [[Snap415API sharedInstance] getOverviews];
        [[Snap415API sharedInstance] getTaxEvents];
        [[Snap415API sharedInstance] getDeals];
    });
    //NotificationCenter handler
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadOverviewsHandler:) name:kNCpN_load_overviews object:nil];
}

-(void)loadOverviewsHandler:(NSNotification *) notification{
    //Loading end
    [MBProgressHUD hideHUDForView:self.view animated:YES];

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
    //
    TableViewCell4WebObject *cell  = (TableViewCell4WebObject *) [self.tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if(!cell)
    {
        NSArray *topLevelObjects = [[NSBundle mainBundle] loadNibNamed:@"TableViewCell4WebObject" owner:nil options:nil];
        
        for(id currentObject in topLevelObjects)
        {
            if([currentObject isKindOfClass:[TableViewCell4WebObject class]])
            {
                cell = (TableViewCell4WebObject *)currentObject;
                break;
            }
        }
    }
//
    WebSiteObject *webSiteObject= [self.overviewsResult objectAtIndex:indexPath.row];
    
//    cell.selectionStyle = UITableViewCellStyleSubtitle;
    cell.headerTextV.text = webSiteObject.header;
    NSAttributedString *attributedString_footer = [[NSAttributedString alloc] initWithData:[webSiteObject.footer dataUsingEncoding:NSUnicodeStringEncoding] options:@{ NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType } documentAttributes:nil error:nil];
    cell.footerTextV.attributedText = attributedString_footer;
//    cell.footerTextV.text = webSiteObject.footer;
//    cell.bodyTextV.text = webSiteObject.body;
    
//    cell.textLabel.text = webSiteObject.header;
//    cell.detailTextLabel.text = webSiteObject.footer;
    
    NSAttributedString *attributedString_body = [[NSAttributedString alloc] initWithData:[webSiteObject.body dataUsingEncoding:NSUnicodeStringEncoding] options:@{ NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType } documentAttributes:nil error:nil];
    cell.bodyTextV.attributedText = attributedString_body;
    
    return cell;
}


@end
