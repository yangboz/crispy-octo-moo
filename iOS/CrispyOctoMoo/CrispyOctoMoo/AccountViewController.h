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

@interface AccountViewController :  UITableViewController<UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) Snap415UserProfile * snap415UserProfileResult;
-(IBAction)addMore:(id)sender;
@property (nonatomic, weak) IBOutlet UILabel *lbl_first_name;
@end
