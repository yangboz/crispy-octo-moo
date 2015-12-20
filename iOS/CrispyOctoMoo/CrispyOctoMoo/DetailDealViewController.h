//
//  DetailDealViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/20/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailDealViewController : UITableViewController
@property(nonatomic,strong) NSDictionary *dealData;

@property (weak, nonatomic) IBOutlet UILabel *lbl_title;
@property (weak, nonatomic) IBOutlet UILabel *lbl_category;
@property (weak, nonatomic) IBOutlet UILabel *lbl_provider;
@property (weak, nonatomic) IBOutlet UILabel *lbl_created;
@property (weak, nonatomic) IBOutlet UILabel *lbl_expires;
@property (weak, nonatomic) IBOutlet UILabel *lbl_price;
@property (weak, nonatomic) IBOutlet UILabel *lbl_value;
@property (weak, nonatomic) IBOutlet UILabel *lbl_dAmount;
@property (weak, nonatomic) IBOutlet UILabel *lbl_dPercentage;
@property (weak, nonatomic) IBOutlet UILabel *lbl_nSold;
@property (weak, nonatomic) IBOutlet UILabel *lbl_description;
@property (weak, nonatomic) IBOutlet UILabel *lbl_image_url;
@property (weak, nonatomic) IBOutlet UILabel *lbl_link_url;
@property (weak, nonatomic) IBOutlet UILabel *lbl_fine_print;
@property (weak, nonatomic) IBOutlet UILabel *lbl_merchant;
@property (weak, nonatomic) IBOutlet UILabel *lbl_share;
//IBActions
-(IBAction) dismiss:(id) sender;
@end
