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
#import "NYSliderPopover.h"
@interface DetailMeViewController : UITableViewController

@property (nonatomic, strong) IBOutlet UITextField *tf_fillingCategory;
//@property (strong, nonatomic) IBOutlet UISlider *sl_numberOfChildren;
//@property (strong, nonatomic) IBOutlet UISlider *sl_income;
@property (nonatomic, strong) IBOutlet NYSliderPopover *nyslider_income;
@property (nonatomic, strong) IBOutlet NYSliderPopover *nyslider_numOfchildren;
@property (strong, nonatomic) DownPicker *downPicker_fillingCategory;
- (IBAction)doneHandler:(id)sender;
//
- (IBAction)sl_incomeValueChanged:(id)sender;
- (IBAction)sl_numOfChildrenValueChanged:(id)sender;
@end
