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

@interface AccountViewController :  UITableViewController

@property (strong, nonatomic) Snap415UserProfile * snap415UserProfileResult;
-(IBAction)addMore:(id)sender;
//Basic
@property (nonatomic, weak) IBOutlet UILabel *lbl_firstName;
@property (nonatomic, weak) IBOutlet UILabel *lbl_gender;
@property (nonatomic, weak) IBOutlet UILabel *lbl_simplyRelationshipStatus;
@property (nonatomic, weak) IBOutlet UILabel *lbl_simplyEducation;
@property (nonatomic, weak) IBOutlet UILabel *lbl_simplyBirthday;
@property (nonatomic, weak) IBOutlet UILabel *lbl_simplyWork;
//More
@property (nonatomic, weak) IBOutlet UILabel *lbl_city;
@property (nonatomic, weak) IBOutlet UILabel *lbl_residents;
@property (nonatomic, weak) IBOutlet UILabel *lbl_family;
@property (nonatomic, weak) IBOutlet UILabel *lbl_location;
@property (nonatomic, weak) IBOutlet UILabel *lbl_workStatus;
@property (nonatomic, weak) IBOutlet UILabel *lbl_education;
@end
