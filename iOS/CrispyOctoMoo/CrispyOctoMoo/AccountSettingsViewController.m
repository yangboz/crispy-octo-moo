//
//  AccountSettingsViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/13/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "AccountSettingsViewController.h"

@interface AccountSettingsViewController ()

@end

@implementation AccountSettingsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    //Snap415API testing
    [[Snap415API sharedInstance] getIncomeCategories];
    [[Snap415API sharedInstance] getFilingCategories];
    [[Snap415API sharedInstance] getChildrenCategories];
    [[Snap415API sharedInstance] getChildrenKeywords];
    [[Snap415API sharedInstance] getMortgageInterests];
    [[Snap415API sharedInstance] getEVCredits];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
