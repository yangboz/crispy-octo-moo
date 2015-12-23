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
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    //API call
    [[Snap415API sharedInstance] getFilingCategories];
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
//Update user profile service.
- (IBAction)doneHandler:(id)sender {
    //
    [Snap415Model sharedInstance].me.profileBase.rwIncome = [NSNumber numberWithFloat: self.sl_income.value];
    [Snap415Model sharedInstance].me.profileBase.rwNumberOfChildren = [NSNumber numberWithFloat:self.sl_numberOfChildren.value];
    [Snap415Model sharedInstance].me.profileBase.rwTaxFilingStatus = self.tf_fillingCategory.text;
    NSLog(@"updateUserProfile with income:%@,numberOfChildren:%@,taxFillingStatus:%@",[Snap415Model sharedInstance].me.profileBase.rwIncome,[Snap415Model sharedInstance].me.profileBase.rwNumberOfChildren,[Snap415Model sharedInstance].me.profileBase.rwTaxFilingStatus);
    [[Snap415API sharedInstance] updateUserProfile];
}
//update user profile complete
- (void)updateCompleteHandler:(NSNotification *) notification{
    [self dismissViewControllerAnimated:YES completion:nil];
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
