//
//  AccountViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "Snap415API.h"
#import "Snap415UserProfile.h"
#import "STPopup.h"
#import "Snap415Model.h"

@interface AccountViewController : UITableViewController

@property (strong, nonatomic) Snap415UserProfile * snap415UserProfileResult;

@end
