//
//  DetailMePopupViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/15/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DownPicker.h"
@interface DetailMePopupViewController : UIViewController
@property (nonatomic, weak) IBOutlet UITextField *tf_fillingCategory;
@property (strong, nonatomic) DownPicker *downPicker_fillingCategory;
@end
