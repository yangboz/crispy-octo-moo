//
//  AccountSettingsViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/13/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Snap415API.h"
#import "MBProgressHUD.h"
#import "DownPicker.h"

@interface AccountSettingsViewController : UITableViewController

@property (nonatomic, strong) IBOutlet UITextField *tf_incomeCategory;
@property (strong, nonatomic) DownPicker *downPicker_incomeCategory;

@property (nonatomic, strong) IBOutlet UITextField *tf_fillingCategory;
@property (strong, nonatomic) DownPicker *downPicker_fillingCategory;

@property (nonatomic, strong) IBOutlet UITextField *tf_childrenCategory;
@property (strong, nonatomic) DownPicker *downPicker_childrenCategory;

@property (nonatomic, strong) IBOutlet UITextField *tf_childrenKeywords;
@property (strong, nonatomic) DownPicker *downPicker_childrenKeywords;

@property (nonatomic, strong) IBOutlet UITextField *tf_mortgageInterest;
@property (strong, nonatomic) DownPicker *downPicker_mortgageInterest;

@property (nonatomic, strong) IBOutlet UITextField *tf_EVCredit;
@property (strong, nonatomic) DownPicker *downPicker_EVCredit;

-(IBAction) EITCCreditCalculate:(id)sender;
@end
