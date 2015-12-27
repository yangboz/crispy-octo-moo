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
    //Loading begin
    [MBProgressHUD showHUDAddedTo:self.view animated:YES];
    //If you need to run your long-running task in the main thread, you should perform it with a slight delay, so UIKit will have enough time to update the UI (i.e., draw the HUD) before you block the main thread with your task.
    dispatch_time_t popTime = dispatch_time(DISPATCH_TIME_NOW, 0.01 * NSEC_PER_SEC);
    dispatch_after(popTime, dispatch_get_main_queue(), ^(void){
        //Delegate to Snap415API.
        [[Snap415API sharedInstance] getIncomeCategories];
        [[Snap415API sharedInstance] getFilingCategories];
        [[Snap415API sharedInstance] getChildrenCategories];
        [[Snap415API sharedInstance] getChildrenKeywords];
        [[Snap415API sharedInstance] getMortgageInterests];
        [[Snap415API sharedInstance] getEVCredits];
        //Loading end
        [MBProgressHUD hideHUDForView:self.view animated:YES];
        //Table view data source config:
        // Do any additional setup after appearing the view.
    });
    //
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadIncomeCategoriesCompleteHandler:) name:kNCpN_load_income_categories object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadFilingCategoriesCompleteHandler:) name:kNCpN_load_filing_categories object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadChildrenCategoriesCompleteHandler:) name:kNCpN_load_children_categories object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadChildrenKeywordsCompleteHandler:) name:kNCpN_load_children_keywords object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadMortgageInterestsCompleteHandler:) name:kNCpN_load_mortgage_interests object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadEVCreditsCompleteHandler:) name:kNCpN_load_EVCredits object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(postEITCCreditCompleteHandler:) name:kNCpN_get_EITCCredit object:nil];
    
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

- (void)loadIncomeCategoriesCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *incomeCategories = [Snap415Model sharedInstance].incomeCategories;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<incomeCategories.count; i++) {
        NSString *label = [(LabelObject *)[incomeCategories objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of incomeCategories label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
            self.downPicker_incomeCategory = [[DownPicker alloc] initWithTextField:self.tf_incomeCategory withData:bindArray];
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

- (void)loadChildrenCategoriesCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *childrenCategories = [Snap415Model sharedInstance].childrenCategories;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<childrenCategories.count; i++) {
        NSString *label = [(LabelObject *)[childrenCategories objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of childrenCategories label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
    self.downPicker_childrenCategory = [[DownPicker alloc] initWithTextField:self.tf_childrenCategory withData:bindArray];
}

- (void)loadChildrenKeywordsCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *childrenKeywords = [Snap415Model sharedInstance].childrenKeywords;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<childrenKeywords.count; i++) {
        NSString *label = [(LabelObject *)[childrenKeywords objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of childrenKeywords label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
    self.downPicker_childrenKeywords = [[DownPicker alloc] initWithTextField:self.tf_childrenKeywords withData:bindArray];
}

- (void)loadMortgageInterestsCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *mortgageInterests = [Snap415Model sharedInstance].mortgageInterests;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<mortgageInterests.count; i++) {
        NSString *label = [(LabelObject *)[mortgageInterests objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of mortgageInterests label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
    self.downPicker_mortgageInterest = [[DownPicker alloc] initWithTextField:self.tf_mortgageInterest withData:bindArray];
}

- (void)loadEVCreditsCompleteHandler:(NSNotification *) notification{
    // Do any additional setup after appearing the view.
    NSArray *EVCredits = [Snap415Model sharedInstance].EVCredits;
    // create the array of data
    NSMutableArray* bindArray = [[NSMutableArray alloc] init];
    for (int i=0; i<EVCredits.count; i++) {
        NSString *label = [(LabelObject *)[EVCredits objectAtIndex:i] label];
        [bindArray addObject:label];
    }
    NSLog(@"bindArray of EVCredits label strings:%@",bindArray.description);
    // bind yourTextField to DownPicker
    self.downPicker_EVCredit = [[DownPicker alloc] initWithTextField:self.tf_EVCredit withData:bindArray];
}

- (IBAction)EITCCreditCalculate:(id)sender{
    //
    [Snap415Model sharedInstance].eitcCreditObject = [[EITCCreditObject alloc] init];
    [Snap415Model sharedInstance].eitcCreditObject.income = @([self.tf_incomeCategory.text integerValue]);
    [Snap415Model sharedInstance].eitcCreditObject.numberOfChildren = @([self.tf_childrenCategory.text integerValue]);
    //
    if (![[Snap415Model sharedInstance].me isKindOfClass:[NSNull class]])
    {
        [Snap415Model sharedInstance].eitcCreditObject.relationshipStatus = [[Snap415Model sharedInstance].me.profileBase objectForKey:@"simplyRelationshipStatus"];
    }
    //
    [[Snap415API sharedInstance] postEITCCredit];
}
- (void)postEITCCreditCompleteHandler:(NSNotification *) notification{
//    [self.navigationController popToRootViewControllerAnimated:YES];
}

@end
