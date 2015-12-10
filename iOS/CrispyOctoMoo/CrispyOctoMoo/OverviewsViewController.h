//
//  OverviewsViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <FBSDKCoreKit/FBSDKCoreKit.h>
#import <FBSDKLoginKit/FBSDKLoginKit.h>
#import <FBSDKLoginKit/FBSDKLoginButton.h>

#import "Snap415API.h"
#import "WebSiteObject.h"

#define kCellReuseId_overview @"ReuseCellName_overview";

@interface OverviewsViewController : UITableViewController

@property (strong, nonatomic) NSArray *overviewsResult;

@end

