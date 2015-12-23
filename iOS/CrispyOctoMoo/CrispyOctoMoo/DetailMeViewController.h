//
//  DetailMeViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/15/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Snap415API.h"
#import "DownPicker.h"
@interface DetailMeViewController : UITableViewController

@property (nonatomic, strong) IBOutlet UITextField *tf_fillingCategory;
@property (strong, nonatomic) IBOutlet UISlider *sl_numberOfChildren;
@property (strong, nonatomic) IBOutlet UISlider *sl_income;
@property (strong, nonatomic) DownPicker *downPicker_fillingCategory;
- (IBAction)doneHandler:(id)sender;

@end
