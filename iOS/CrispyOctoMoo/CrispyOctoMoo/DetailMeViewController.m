//
//  DetailMeViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/15/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "DetailMeViewController.h"

@interface DetailMeViewController ()

@end

@implementation DetailMeViewController



- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    //NSNotificationCenter Oberserver
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateCompleteHandler:) name:kNCpN_update_profile object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadFilingCategoriesCompleteHandler:) name:kNCpN_load_filing_categories object:nil];
    //Initial text
    [self updateSlidersPopoverText];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    //API call
    [[Snap415API sharedInstance] getFilingCategories];
}

- (void)updateSlidersPopoverText
{
    self.nyslider_income.popover.textLabel.text = [NSString stringWithFormat:@"%.2f", self.nyslider_income.value];
    self.nyslider_numOfchildren.popover.textLabel.text = [NSString stringWithFormat:@"%.2f", self.nyslider_numOfchildren.value];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)sl_incomeValueChanged:(id)sender{
    [self updateSlidersPopoverText];
}
- (IBAction)sl_numOfChildrenValueChanged:(id)sender{
    [self updateSlidersPopoverText];
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
//Update user profile service.
- (IBAction)doneHandler:(id)sender {
    //
    [Snap415Model sharedInstance].profile = [[Snap415RwUserProfile alloc] init];
    [Snap415Model sharedInstance].profile.rwIncome = [NSNumber numberWithFloat: self.nyslider_income.value];
    NSLog(@"updateUserProfile with simpleProfile.income:%@",[Snap415Model sharedInstance].profile.rwIncome);
    [Snap415Model sharedInstance].profile.rwNumberOfChildren = [NSNumber numberWithFloat:self.nyslider_numOfchildren.value];
    [Snap415Model sharedInstance].profile.rwTaxFilingStatus = self.tf_fillingCategory.text;
    NSLog(@"updateUserProfile with simpleProfile:%@",[Snap415Model sharedInstance].profile.description);
    [[Snap415API sharedInstance] updateUserProfile];
}

//update user profile complete
- (void)updateCompleteHandler:(NSNotification *) notification{
    //UINavigation back.
//    [self dismissViewControllerAnimated:YES completion:nil];
    [self.navigationController popToRootViewControllerAnimated:YES];
    //Update the taxEvents tab view.
    [[Snap415API sharedInstance] getTaxEvents];
}
- (void)loadFilingCategoriesCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *filingCategories = [Snap415Model sharedInstance].filingCategories;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<filingCategories.count; i++) {
        NSString *label = [(LabelGroupObject *)[filingCategories objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of filingCategories label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
    self.downPicker_fillingCategory = [[DownPicker alloc] initWithTextField:self.tf_fillingCategory withData:bindArray];
}

@end
